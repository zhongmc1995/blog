package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.dao.TagMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.domain.Tag;
import com.zhongmc.blog.utils.Page;
import com.zhongmc.blog.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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
    public String index(Model model, HttpServletRequest request){
        /*List<Blog> blogList = blogMapper.findAllBlog();
        model.addAttribute("blogList",blogList);*/
       /* int totalSize = blogMapper.Count();
        Page<Blog> blogPage = new Page<>();
        blogPage.setPageSize(1);

        blogPage.setTotalRecord(totalSize);
        int index = 1;
        String page = request.getParameter("page");
        if (page!=null){
            index = Integer.parseInt(page);
            if (index<1){
                index = 1;
            }else if (index>blogPage.getTotalPage()){
                index = blogPage.getTotalPage();
            }
        }
        blogPage.setPageNo(index);
        int startIndex = blogPage.getPageSize()*(blogPage.getPageNo()-1);
        Map<String,Integer> tmp = new HashMap<>();
        tmp.put("startIndex",startIndex);
        tmp.put("pageSize",blogPage.getPageSize());
        List<Blog> blogList = blogMapper.findBlogsByPage(tmp);
        model.addAttribute("blogList",blogList);
        String pageStr = PagingUtil.getPagelink(index,blogPage.getTotalRecord()/blogPage.getPageSize(),"","/blog-list");
        model.addAttribute("pageStr",pageStr);*/

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
    @RequestMapping("/getMonthBlogs")
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
    }

}
