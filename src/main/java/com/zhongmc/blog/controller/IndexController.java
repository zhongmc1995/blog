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
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class IndexController {
    List<Blog> ll;

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagMapper tagMapper;


    @RequestMapping("/")
    public String index(Model model){
        List<Blog> blogList = blogMapper.findAllBlog();
        this.ll = blogList;
        model.addAttribute("blogList",blogList);

        //初始化侧边栏的标签
        /*List<Tag> tagList = InitTagBlogNum(tagMapper.findAllTags());
        model.addAttribute("tagList",tagList); 变为ajax请求数据了*/

        /*Map<String,Integer> tmp = new HashMap<>();*/
        //将博客归档 sql数据库层归档SELECT DATE_FORMAT(tbl_blog.createtime,'%Y-%m') AS t,COUNT(*) AS n FROM tbl_blog GROUP BY t ORDER BY t DESC
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");*/

       /* for (Blog b:blogList) {
            Date bdate = b.getCreateTime();
            String dateStr = simpleDateFormat.format(bdate);
            Integer num = tmp.get(dateStr);
            if (num==null){
                //说明之前没有
                tmp.put(dateStr,1);
            }else {
                tmp.put(dateStr,num+1);
            }
        }*/

        /*model.addAttribute("monthblogs",tmp);*/
        return "index";
    }

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
        Map<String,Integer> tmp = new HashMap<>();
        //将博客归档 sql数据库层归档SELECT DATE_FORMAT(tbl_blog.createtime,'%Y-%m') AS t,COUNT(*) AS n FROM tbl_blog GROUP BY t ORDER BY t DESC
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");

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

}
