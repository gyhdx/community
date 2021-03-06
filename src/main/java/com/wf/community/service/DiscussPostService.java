package com.wf.community.service;

import com.wf.community.dao.DiscussPostMapper;
import com.wf.community.entity.DiscussPost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/11 0:24
 */
public interface DiscussPostService  {

    List<DiscussPost> getDiscussPosts(int userId, int offset, int limit, int orderMode);

    int getDiscussPostRows(int userId);

    int addDiscussPost(DiscussPost post);

    DiscussPost findDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

    int updateScore(int id, double score);

    int updateStatus(int id, int status);

    int updateType(int id, int type);

    int deletePost(int id);
}
