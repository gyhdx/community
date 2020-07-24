//package com.wf.community;
//
//import com.wf.community.dao.DiscussPostMapper;
//import com.wf.community.dao.elasticsearch.DiscussPostRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//
///**
// * @Description TODO
// * @Author gyhdx
// * @Date 2020/7/13 11:49
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes = CommunityApplication.class)
//public class ESTest {
//
//    @Resource
//    private DiscussPostMapper discussPostMapper;
//
//    @Autowired
//    private DiscussPostRepository repository;
//
//    @Resource
//    private ElasticsearchRestTemplate template;
//
//    @Test
//    public void mailTest() throws Exception {
//        System.out.println(discussPostMapper.selectDiscussPostById(242));
//        repository.save(discussPostMapper.selectDiscussPostById(242));
////        repository.save(discussPostMapper.selectDiscussPostById(243));
////        repository.save(discussPostMapper.selectDiscussPostById(244));
//    }
//}
