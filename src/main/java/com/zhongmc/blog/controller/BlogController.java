package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class BlogController {

    @Autowired
    BlogMapper blogMapper;
    @RequestMapping("/blog/detail/{id}")
    public String blogView(@PathVariable("id") Integer id,Model model){
        Blog blog = blogMapper.findOneById(id);
        model.addAttribute("blog",blog);
        return  "blog_view";
    }



}
