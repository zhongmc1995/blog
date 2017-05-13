package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.service.IBlogService;
import com.zhongmc.blog.service.IThemeService;
import com.zhongmc.blog.utils.Page;
import com.zhongmc.blog.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class BlogListController extends BaseController {
    @Autowired
    IBlogService blogService;
    @Autowired
    IThemeService themeService;

    //@RequestMapping("/blog-list")
    @RequestMapping("/")
    public String blogList(Model model, HttpServletRequest request){
        String theme = themeService.getCurrentTheme();
        THEME = "themes/" + theme;
        int totalSize = blogService.Count();
        Page<Blog> blogPage = new Page<>();
        blogPage.setTotalRecord(totalSize);
        String page = request.getParameter("page");
        int index = PagingUtil.fixIndex(page,blogPage.getTotalPage());
        blogPage.setPageNo(index);
        int startIndex = blogPage.getPageSize()*(blogPage.getPageNo()-1);
        Map<String,Integer> tmp = new HashMap<>();
        tmp.put("startIndex",startIndex);
        tmp.put("pageSize",blogPage.getPageSize());
        List<Blog> blogList = blogService.findBlogsByPage(tmp);
        model.addAttribute("blogList",blogList);
        int maxMode = (blogPage.getTotalRecord()%blogPage.getPageSize());
        int maxNo = maxMode==0 ? (blogPage.getTotalRecord()/blogPage.getPageSize()):(blogPage.getTotalRecord()/blogPage.getPageSize())+1;
        String pageStr = PagingUtil.getPagelink(index,maxNo,"","/");
        model.addAttribute("pageStr",pageStr);
        return THEME+"/index";
    }
}
