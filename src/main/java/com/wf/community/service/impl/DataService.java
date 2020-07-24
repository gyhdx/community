package com.wf.community.service.impl;

import com.wf.community.util.RedisKeyUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/22 23:19
 */
@Service
public class DataService {

    @Resource
    private RedisTemplate redisTemplate;

    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    // 将指定ip存入UV
    public void recordUV(String ip) {
        String uvKey = RedisKeyUtil.getUVKey(format.format(new Date()));
        redisTemplate.opsForHyperLogLog().add(uvKey, ip);
    }

    // 获取指定区间的UV
    public long calculateUV(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        String key = RedisKeyUtil.getUVKey(format.format(start), format.format(end));

        // 整理区间内的key
        List<String> keyList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        while (!calendar.getTime().after(end)) {
            String uvKey = RedisKeyUtil.getUVKey(format.format(calendar.getTime()));
            keyList.add(uvKey);
            calendar.add(Calendar.DATE, 1);
        }

        // 合并key
        redisTemplate.opsForHyperLogLog().union(key, keyList.toArray());

        //返回统计数量
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    // 统计DAU
    public void recordDAU(int userId){
        String dauKey = RedisKeyUtil.getDAUKey(format.format(new Date()));
        redisTemplate.opsForValue().setBit(dauKey, userId, true);
    }

    // 统计指定区间内的DAU
    public long calculateDAU(Date start, Date end){
        if (start == null || end == null) {
            throw new IllegalArgumentException("参数不能为空");
        }



        // 整理区间内的key
        List<byte[]> keyList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        while (!calendar.getTime().after(end)) {
            String uvKey = RedisKeyUtil.getDAUKey(format.format(calendar.getTime()));
            keyList.add(uvKey.getBytes());
            calendar.add(Calendar.DATE, 1);
        }

        // 进行or运算
        return (long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                String key = RedisKeyUtil.getUVKey(format.format(start), format.format(end));
                connection.bitOp(RedisStringCommands.BitOperation.OR,
                        key.getBytes(),
                        keyList.toArray(new byte[0][0])
                        );
                return connection.bitCount(key.getBytes());
            }
        });

//        // 进行OR运算
//        return (long) redisTemplate.execute(new RedisCallback() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                String redisKey = RedisKeyUtil.getDAUKey(format.format(start), format.format(end));
//                connection.bitOp(RedisStringCommands.BitOperation.OR,
//                        redisKey.getBytes(), keyList.toArray(new byte[0][0]));
//                return connection.bitCount(redisKey.getBytes());
//            }
//        });
    }
}
