package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class BlogListController {
    @Autowired
    BlogMapper blogMapper;

    @RequestMapping("/blog-list")
    public String blogList(Model model){
        List<Blog> blogList = blogMapper.findAllBlog();
        model.addAttribute("blogList",blogList);
        return "blog_list";
    }
}
