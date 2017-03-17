package com.zhongmc.blog.controller;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.service.IBlogService;
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
public class ArchiveController extends BaseController {
    @Autowired
    private IBlogService blogService;
    //博客归档
    @RequestMapping("/archive-list")
    public String archiveList(Model model){
        Map<String,Integer> tmp = new HashMap<>();
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
        // 2016/12 2017/1
        Map<String,List<Blog>> newMap = new HashMap<>();
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
                blogList = blogService.findBlogByYM(ym_start, ym_end);
            }
            newMap.put(date,blogList);
        }
        //对map进行排序
        List<Map.Entry> archiveList = new ArrayList(newMap.entrySet());
        Collections.sort(archiveList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Map.Entry<String,List<Blog>> entry1 = (Map.Entry)o1;
                Map.Entry<String,List<Blog>> entry2 = (Map.Entry)o2;
                return (entry2.getKey()).compareTo(entry1.getKey());
            }
        });

        model.addAttribute("archiveList",archiveList);
        /*return "themes/default/archives";*/
        return THEME+"/archives";
    }
}
