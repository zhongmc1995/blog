package com.zhongmc.blog.controller.admin;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.domain.Tag;
import com.zhongmc.blog.service.IBlogService;
import com.zhongmc.blog.service.ITagServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

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
    public String toEditPage(Model model){
        //总文章数
        int blognum = blogService.Count();
        model.addAttribute("blognum",blognum);
        //最新文章
        List<Blog> lastestBlogs = blogService.getLastestBlogs(4);
        model.addAttribute("lastestBlogs",lastestBlogs);
        return "admin/index";
    }

    //跳转到文章编辑页面
    @RequestMapping(value = "/article/publish")
    public String toArticlePubPage(Model model){
        List<Tag>tagList = tagServive.findAllTags();
        model.addAttribute("tagList",tagList);
        return "admin/article_edit";
    }

    //博客添加操作
    @RequestMapping(value = "/add-article")
    public String addArticle(Blog blog){
        if (blog!=null){

                Date date = new Date();
                int id = blogService.findMaxId()+1;
                blog.setId(id);
                blog.setCreateTime(date);
                blog.setCreateBy("zhongmc");
                blogService.addABlog(blog);

        }
        return "redirect:/admin/article";
    }

    //博客列表
    @RequestMapping(value = "/article")
    public String articleListPage(Model model){
        List<Blog> blogList = blogService.findAllBlog();
        model.addAttribute("blogList",blogList);
        return "/admin/article_list";
    }

    //博客删除
    @RequestMapping(value = "/article-del/{id}")
    public String articleDel(@PathVariable int id){
        blogService.delBlog(id);
        return "redirect:/admin/article";
    }
    //跳转到文章更新编辑页面
    @RequestMapping(value = "/article/publish/{id}")
    public String toArticlePubPage(Model model,@PathVariable(required = true) Integer id){
        Blog blog = blogService.findOneById(id);

        List<Tag>tags = tagServive.findTagsByBlogId(id);
        String tagStr = "";
        for (Tag tag: tags) {
            tagStr+=tag.getTagname()+"|";
        }
        blog.setTags(tagStr.substring(0,tagStr.length()-1));
        blog.setTagList(tags);
        model.addAttribute("blog",blog);

        List<Tag>tagList = tagServive.findAllTags();
        model.addAttribute("tagList",tagList);
        return "/admin/article_edit";
    }

    //博客更新
    @RequestMapping(value = "/article/update")
    public String articleUpdate(Blog blog){
        blogService.updateBlog(blog);

        return "redirect:/admin/article";
    }
}
