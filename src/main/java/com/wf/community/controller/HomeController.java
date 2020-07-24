package com.wf.community.controller;

import com.wf.community.entity.DiscussPost;
import com.wf.community.entity.Page;
import com.wf.community.entity.User;
import com.wf.community.service.DiscussPostService;
import com.wf.community.service.UserService;
import com.wf.community.service.impl.LikeService;
import com.wf.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/11 0:36
 */
@Controller
public class HomeController implements CommunityConstant {

    @Resource
    private DiscussPostService discussPostService;

    @Resource
    private UserService userService;

    @Resource
    private LikeService likeService;

    @GetMapping("/index")
    public String getIndexPage(Model model , Page page,
                               @RequestParam(name = "orderMode", defaultValue = "0") int orderMode){
        // springMVC 会自动生成model和page实例，并且会把page自动注入进model。
        page.setRows(discussPostService.getDiscussPostRows(0));
        page.setPath("/index?orderMode=" + orderMode);

        List<DiscussPost> lists = discussPostService.getDiscussPosts(0, page.getOffset(), page.getLimit(), orderMode);
        List<Map<String,Object>> discussPosts = new ArrayList<>();

        if (lists != null){
            for (DiscussPost post : lists) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);

                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
                map.put("likeCount", likeCount);

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        model.addAttribute("orderMode", orderMode);
        return "/index";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "/error/500";
    }

    // 拒绝访问时的提示页面
    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return "/error/404";
    }
}
