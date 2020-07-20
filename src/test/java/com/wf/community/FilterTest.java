package com.wf.community;

import com.wf.community.dao.DiscussPostMapper;
import com.wf.community.dao.UserMapper;
import com.wf.community.entity.DiscussPost;
import com.wf.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/11 0:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class FilterTest {

    @Resource
    private SensitiveFilter selectDiscussPosts;

    @Test
    public void testSelectPosts(){
        String filter = selectDiscussPosts.filter("我要吸毒，我要嫖娼，我是傻逼");
        System.out.println(filter);
    }
}
