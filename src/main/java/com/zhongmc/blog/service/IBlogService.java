package com.zhongmc.blog.service;

import com.zhongmc.blog.domain.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongmc on 2017/3/13.
 * 博客服务接口
 */
public interface IBlogService {

    //查找所有博客
    List<Blog> findAllBlog();

    //按id查找一篇博客
    Blog findOneById(Integer id);

    //按标签id查找博客
    List<Blog> findBlogsByTagId(Map<String,Integer> map);

    //按年月查找博客
    List<Blog> findBlogByYM(@Param("start") String start, @Param("end") String end);

    //分页查找博客
    List<Blog> findBlogsByPage(Map<String,Integer> map);

    //统计所有博客数量
    int Count();

    //统计指定标签的博客数量
    int CountTagBlogs(Integer tagid);

    //按时间查找出博客
    List<Blog> findAllBlogsSortByTime();

    //查找出最新的博客num篇
    List<Blog> getLastestBlogs(int num);

    //添加博客
    void addABlog(Blog blog);

    //查询最大的id
    int findMaxId();

    void addBlogTag(int blogid,int tagid);
}
