package com.wf.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description TODO
 * @Author gyhdx
 * @Date 2020/7/10 23:25
 */
@Controller
public class TestController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello spring";
    }
}
