package com.zhongmc.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhongmc on 2017/2/26.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(value = "/add-article-page")
    public String toEditPage(){
        return "admin/blog_edit";
    }
}
