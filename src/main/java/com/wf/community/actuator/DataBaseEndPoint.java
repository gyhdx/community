package com.wf.community.actuator;

import com.wf.community.controller.UserController;
import com.wf.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description TODO 自定义端点
 * @Author gyhdx
 * @Date 2020/7/24 23:06
 */
@Component
@Endpoint(id = "database")
public class DataBaseEndPoint {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseEndPoint.class);

    @Resource
    private DataSource dataSource;

    //ReadOperation表示这个方法只能get访问
    @ReadOperation
    public String checkConnection() {
        try (
                Connection connection = dataSource.getConnection();
        ) {
            return CommunityUtil.getJSONString(0, "获取连接成功");
        } catch (SQLException e) {
            logger.info("获取连接失败", e.getMessage());
            return CommunityUtil.getJSONString(1, "获取连接失败");
        }
    }

}
