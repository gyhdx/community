package com.wf.community.controller;

import com.wf.community.entity.DiscussPost;
import com.wf.community.entity.Page;
import com.wf.community.entity.User;
import com.wf.community.service.DiscussPostService;
import com.wf.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
public class HomeController {

    @Resource
    private DiscussPostService discussPostService;

    @Resource
    private UserService userService;

    @GetMapping("/index")
    public String getIndexPage(Model model , Page page){
        // springMVC 会自动生成model和page实例，并且会把page自动注入进model。
        page.setRows(discussPostService.getDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> lists = discussPostService.getDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> discussPosts = new ArrayList<>();

        if (lists != null){
            for (DiscussPost list : lists) {
                Map<String,Object> map = new HashMap<>();
                map.put("post",list);
                User user = userService.findUserById(list.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
