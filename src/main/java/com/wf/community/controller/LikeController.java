package com.wf.community.controller;

import com.wf.community.entity.Event;
import com.wf.community.entity.User;
import com.wf.community.event.EventProducer;
import com.wf.community.service.impl.LikeService;
import com.wf.community.util.CommunityConstant;
import com.wf.community.util.CommunityUtil;
import com.wf.community.util.HostHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/20 0:43
 */
@Controller
public class LikeController implements CommunityConstant {

    @Resource
    private EventProducer eventProducer;

    @Resource
    private LikeService likeService;

    @Resource
    private HostHolder hostHolder;

    @PostMapping("/like")
    @ResponseBody
    public String like(int entityType, int entityId,  int entityUserId, int postId){
        User user = hostHolder.getUser();

        //点赞
        likeService.like(user.getId(), entityType, entityId, entityUserId);
        // 数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        // 状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        // 返回的结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        // 触发点赞事件
        if (likeStatus == 1) {
            Event event = new Event()
                    .setTopic(TOPIC_LIKE)
                    .setUserId(hostHolder.getUser().getId())
                    .setEntityType(entityType)
                    .setEntityId(entityId)
                    .setEntityUserId(entityUserId)
                    .setData("postId", postId);
            eventProducer.fireEvent(event);
        }

        return CommunityUtil.getJSONString(0, null, map);
    }
}
