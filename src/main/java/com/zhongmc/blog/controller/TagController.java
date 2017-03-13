package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.dao.TagMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.domain.Tag;
import com.zhongmc.blog.service.IBlogService;
import com.zhongmc.blog.service.ITagServive;
import com.zhongmc.blog.utils.Page;
import com.zhongmc.blog.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZMC on 2017/1/25.
 */
@Controller
public class TagController {
    @Autowired
    IBlogService blogService;
    @Autowired
    ITagServive tagService;
    //通过标签id查找所有的博客
    @RequestMapping(value = "/tag-blogs/{tagid}",method = RequestMethod.GET)
    public String tagBlogs(@PathVariable("tagid") int id, Model model, HttpServletRequest request){
        Tag tag = tagService.findOneById(id);
        model.addAttribute("tag",tag);

        int totalSize = blogService.CountTagBlogs(id);
        Page<Blog> blogPage = new Page<>();
        blogPage.setPageSize(6);

        blogPage.setTotalRecord(totalSize);
        String page = request.getParameter("page");
        int index = PagingUtil.fixIndex(page,blogPage.getTotalPage());
        blogPage.setPageNo(index);
        int startIndex = blogPage.getPageSize()*(blogPage.getPageNo()-1);
        Map<String,Integer> tmp = new HashMap<>();
        tmp.put("tagid",id);
        tmp.put("startIndex",startIndex);
        tmp.put("pageSize",blogPage.getPageSize());
        List<Blog> blogList = blogService.findBlogsByTagId(tmp);
        model.addAttribute("blogList",blogList);
        String pageStr = PagingUtil.getPagelink(index,(blogPage.getTotalRecord()/blogPage.getPageSize())+1,"","/tag-blogs/"+id);
        model.addAttribute("pageStr",pageStr);

        return "themes/default/category_blogs";
    }

    //定向到标签页
    @RequestMapping("/category-list")
    public String toCategoryList(Model model){
        List<Tag> tagList = tagService.findAllTags();
        model.addAttribute("tagList",tagList);
        return "themes/default/category";
    }
}
