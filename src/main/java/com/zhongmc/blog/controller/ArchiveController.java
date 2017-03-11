package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhongmc on 2017/3/10.
 */
@Controller
public class ArchiveController {
    @Autowired
    private BlogMapper blogMapper;
    //博客归档
    @RequestMapping("/archive-list")
    public String archiveList(Model model){
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
        // 2016/12 2017/1
        Map<String,List<Blog>> newMap = new HashMap<String,List<Blog>>();
        for (Map.Entry<String,Integer> en: tmp.entrySet()) {
            String date = en.getKey();
            List<Blog> blogList = new ArrayList<>();
            String [] strings = date.split("/");
            if (strings.length==2){
                int year = Integer.valueOf(strings[0]);
                int month = Integer.valueOf(strings[1]);
                String ym_start = year+"-"+month+"-01";
                String ym_end = "";
                if (month==12){
                    ym_end = year+1+"-01-01";
                }else {
                    ym_end = year+"-"+(month+1)+"-01";
                }
                blogList = blogMapper.findBlogByYM(ym_start, ym_end);
            }
            newMap.put(date,blogList);
        }
        model.addAttribute("dateBlogs",newMap);
        return "themes/default/archives";
    }
}
