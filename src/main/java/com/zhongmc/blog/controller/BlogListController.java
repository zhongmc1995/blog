package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.utils.Page;
import com.zhongmc.blog.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class BlogListController {
    @Autowired
    BlogMapper blogMapper;

    @RequestMapping("/blog-list")
    public String blogList(Model model, HttpServletRequest request){
        int totalSize = blogMapper.Count();
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
        model.addAttribute("pageStr",pageStr);
        return "blog_list";
    }
}
