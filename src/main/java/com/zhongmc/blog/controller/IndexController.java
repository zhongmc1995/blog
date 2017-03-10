package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.dao.TagMapper;
import com.zhongmc.blog.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;


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
    public String index(){
        return "redirect:/blog-list";
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
    /*@RequestMapping("/getMonthBlogs")
    @ResponseBody
    public Map<String,Integer> getMonthBlogs(){
        Map<String,Integer> tmp = new HashMap<>();
        //将博客归档 sql数据库层归档SELECT DATE_FORMAT(tbl_blog.createtime,'%Y-%m') AS t,COUNT(*) AS n FROM tbl_blog GROUP BY t ORDER BY t DESC
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        List<Blog> ll = blogMapper.findAllBlog();
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
    }*/

}
