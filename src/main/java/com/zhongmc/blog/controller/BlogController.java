package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;
/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class BlogController {

    @Autowired
    BlogMapper blogMapper;

    @RequestMapping("/blog/detail/{id}")
    public String blogView(@PathVariable("id") Integer id, Model model) {
        Blog blog = blogMapper.findOneById(id);
        model.addAttribute("blog", blog);
        return "blog_view";
    }
    //筛选月博客
    @RequestMapping("/blogs/{year}/{month}")
    public String sortBlogByYM(Model model,@PathVariable(value = "year") int year, @PathVariable(value = "month") int month) throws ParseException {
        String ym_start = year+"-"+month+"-01";
        String ym_end = "";
        if (month==12){
            ym_end = year+1+"-01-01";
        }else {
            ym_end = year+"-"+(month+1)+"-01";
        }
        List<Blog> blogList = blogMapper.findBlogByYM(ym_start, ym_end);
        model.addAttribute("blogList",blogList);

        return "themes/default/index";
    }

    //筛选年博客
    @RequestMapping("/blogs/{year}")
    public String sortBlogByY(@PathVariable(value = "year") int year,Model model) {
        if (year < 2000) {
            year = 2000;
        }
        String y_start = year + "";
        String y_end = (year + 1) + "";
        List<Blog> blogList = blogMapper.findBlogByYM(y_start, y_end);
        model.addAttribute("blogList", blogList);
        return "themes/default/index";
    }
    //博客列表
    @RequestMapping("/blogs")
    public String sortAllBlog() {
        return "redirect:/blog-list";
    }

}
