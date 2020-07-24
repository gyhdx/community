package com.wf.community;

import com.wf.community.dao.DiscussPostMapper;
import com.wf.community.dao.MessageMapper;
import com.wf.community.dao.UserMapper;
import com.wf.community.entity.DiscussPost;
import com.wf.community.entity.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/11 0:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DiscussPostMapper discussPostMapper;

    @Resource
    private MessageMapper messageMapper;

    @Test
    public void testSelectById(){
        System.out.println(userMapper.selectById(101));
    }

    @Test
    public void testSelectByName(){
        System.out.println(userMapper.selectByName("wf"));
    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10, 0);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
    }

    @Test
    public void tet2(){
        List<Message> messages = messageMapper.selectConversations(111, 0, 20);
        for (Message message : messages) {
            System.out.println(message);
        }
        System.out.println();
    }

}
