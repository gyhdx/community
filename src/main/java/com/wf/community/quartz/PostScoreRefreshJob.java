package com.wf.community.quartz;

import com.wf.community.controller.UserController;
import com.wf.community.entity.DiscussPost;
import com.wf.community.service.DiscussPostService;
import com.wf.community.service.impl.LikeService;
import com.wf.community.util.CommunityConstant;
import com.wf.community.util.RedisKeyUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/23 22:36
 */
@Configuration
public class PostScoreRefreshJob implements Job, CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(PostScoreRefreshJob.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private DiscussPostService discussPostService;

    @Resource
    private LikeService likeService;

    private static final Date epoch;

    static {
        try {
            epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-09-01 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException("时间初始化失败！", e);
        }
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String postScoreKey = RedisKeyUtil.getPostScoreKey();
        BoundSetOperations operation = redisTemplate.boundSetOps(postScoreKey);

        if (operation.size() == 0){
            logger.info("【mession end】，没有要刷新的帖子！");
            return;
        }

        logger.info("[任务开始]，正在刷新帖子个数：" + operation.size());

        while (operation.size() > 0 ){
            refresh((int) operation.pop());
        }

        logger.info("[任务结束]，刷新帖子结束");
    }

    private void refresh(int postId) {
        DiscussPost post = discussPostService.findDiscussPostById(postId);

        if (post == null){
            logger.info("帖子不存在，id为：" + postId);
            return;
        }

        // 是否精华
        boolean wonderful = post.getStatus() == 1;
        // 评论数量
        int commentCount = post.getCommentCount();
        // 点赞数量
        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, postId);

        // 计算权重
        double w = (wonderful ? 75 : 0) + commentCount * 10 + likeCount * 2;
        // 分数 = 帖子权重 + 距离天数
        double score = Math.log10(Math.max(w, 1))
                + (post.getCreateTime().getTime() - epoch.getTime()) / (1000 * 3600 * 24);
        // 更新帖子分数
        discussPostService.updateScore(postId, score);
    }
}
