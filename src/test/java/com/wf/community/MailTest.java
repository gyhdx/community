package com.wf.community;

import com.wf.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/13 11:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTest {

    @Resource
    private MailClient mailClient;

    @Test
    public void mailTest() throws Exception {
        mailClient.sendEmail("wangfeng614@163.com","test");
    }
}
