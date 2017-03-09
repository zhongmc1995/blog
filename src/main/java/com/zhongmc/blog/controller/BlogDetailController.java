package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.dao.TagMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by ZMC on 2017/1/25.
 */
@Controller
public class BlogDetailController {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagMapper tagMapper;

    @RequestMapping("/blog/{id}")
    public String blogDetail(@PathVariable("id")int id, Model model){
        Blog blog = blogMapper.findOneById(id);
        List<Tag>tagList = tagMapper.findTagsByBlogId(id);
        String tagStr = "";
        for (Tag tag: tagList) {
            tagStr+=tag.getTagname()+"|";
        }
        blog.setTags(tagStr.substring(0,tagStr.length()-1));
        blog.setTagList(tagList);
        model.addAttribute("blog",blog);
        return "themes/default/page" ;
    }
}
