package com.zhongmc.blog.controller;

import com.sun.org.apache.xml.internal.security.Init;
import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.dao.TagMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class IndexController {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagMapper tagMapper;


    @RequestMapping("/")
    public String index(Model model){
        List<Blog> blogList = blogMapper.findAllBlog();
        model.addAttribute("blogList",blogList);
        //初始化侧边栏的标签
        List<Tag> tagList = InitTagBlogNum(tagMapper.findAllTags());
        model.addAttribute("tagList",tagList);
        return "index";
    }

    private List<Tag> InitTagBlogNum(List<Tag> tagList){

        for (Tag tag:tagList) {
            tag.setBlognum(tagMapper.calBlogNumByTagId(tag.getId()));
        }
        return tagList;
    }

}
