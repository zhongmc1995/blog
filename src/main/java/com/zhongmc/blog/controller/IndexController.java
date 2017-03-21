package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.TagMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.domain.Tag;
import com.zhongmc.blog.service.IBlogService;
import com.zhongmc.blog.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class IndexController extends BaseController {


    @Autowired
    IBlogService blogService;
    @Autowired
    TagMapper tagMapper;

    @Autowired
    IThemeService themeService;
    @RequestMapping("/")
    public String index(){
        String theme = themeService.getCurrentTheme();
        THEME = "themes/" + theme;
        return "redirect:/blog-list";
    }

    //初始化标签数量
    private List<Tag> InitTagBlogNum(List<Tag> tagList){
        if (tagList!=null){
            for (Tag tag:tagList) {
                tag.setBlognum(tagMapper.calBlogNumByTagId(tag.getId()));
            }
        }
        return tagList;
    }

    //获取标签 返回json数据
    @RequestMapping("/getTag")
    @ResponseBody
    public List<Tag> getTags(){
        List<Tag> tagList = InitTagBlogNum(tagMapper.findAllTags());
        return tagList;
    }

    //获取每月的博客
    @RequestMapping("/getMonthBlogs")
    @ResponseBody
    public Map<String,Integer> getMonthBlogs(){
        Map<String,Integer> tmp = new TreeMap<>();
        //将博客归档 sql数据库层归档SELECT DATE_FORMAT(tbl_blog.createtime,'%Y-%m') AS t,COUNT(*) AS n FROM tbl_blog GROUP BY t ORDER BY t DESC
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        List<Blog> ll = blogService.findAllBlog();
        for (Blog b:ll) {
            Date bdate = b.getCreateTime();
            String dateStr = simpleDateFormat.format(bdate);
            Integer num = tmp.get(dateStr);
            if (num==null){
                //说明之前没有
                tmp.put(dateStr,1);
            }else {
                tmp.put(dateStr,num+1);
            }
        }
        return tmp;
    }
    //每页底部的最新博客数据
    @RequestMapping("/footer-data")
    @ResponseBody
    public List<Blog> initFooterData(){
        List<Blog> lastersBlogs = blogService.getLastestBlogs(4);//获取四条最新的blog
        return lastersBlogs;
    }

    //友情链接
    @RequestMapping("/links")
    public String linksPages(){
        return THEME+"/links";
    }

}
