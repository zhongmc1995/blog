package com.zhongmc.blog.controller.admin;

import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.domain.Tag;
import com.zhongmc.blog.service.IBlogService;
import com.zhongmc.blog.service.ITagServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhongmc on 2017/2/26.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ITagServive tagServive;
    @Autowired
    IBlogService blogService;
    //管理首页
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toEditPage(){
        return "admin/index";
    }
    //跳转到文章编辑页面
    @RequestMapping(value = "/article/publish")
    public String toArticlePubPage(Model model){
        List<Tag>tagList = tagServive.findAllTags();
        model.addAttribute("tagList",tagList);
        return "admin/article_edit";
    }

    @RequestMapping(value = "add-article")
    @ResponseBody
    public int addArticle(Blog blog){
        if (blog!=null){
            try{
                Date date = new Date();
                int id = blogService.findMaxId()+1;
                blog.setId(id);
                blog.setCreateTime(date);
                blog.setCreateBy("zhongmc");
                blogService.addABlog(blog);
            }catch (Exception e){
                return -1;
            }
        }
        return 1;
    }

    /*public static void main(String[] args) {
        long t  = System.currentTimeMillis();
        System.out.print(t);
    }*/
}
