package com.zhongmc.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhongmc on 2017/2/26.
 */
@Controller
public class AdminController {
    @RequestMapping(value = "to-blog-edit")
    public String toEditPage(){
        return "admin/blog_edit";
    }
}
