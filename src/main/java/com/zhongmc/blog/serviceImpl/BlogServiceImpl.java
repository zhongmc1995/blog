package com.zhongmc.blog.serviceImpl;

import com.zhongmc.blog.dao.BlogMapper;
import com.zhongmc.blog.dao.TagMapper;
import com.zhongmc.blog.domain.Blog;
import com.zhongmc.blog.domain.Tag;
import com.zhongmc.blog.service.IBlogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongmc on 2017/3/13.
 * 博客服务的实现类
 */
@Service
public class BlogServiceImpl implements IBlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Blog> findAllBlog() {
        return blogMapper.findAllBlog();
    }

    @Override
    public Blog findOneById(Integer id) {
        return blogMapper.findOneById(id);
    }

    @Override
    public List<Blog> findBlogsByTagId(Map<String, Integer> map) {
        return blogMapper.findBlogsByTagId(map);
    }

    @Override
    public List<Blog> findBlogByYM(@Param("start") String start, @Param("end") String end) {
        return blogMapper.findBlogByYM(start,end);
    }

    @Override
    public List<Blog> findBlogsByPage(Map<String, Integer> map) {
        return blogMapper.findBlogsByPage(map);
    }

    @Override
    public int Count() {
        return blogMapper.Count();
    }

    @Override
    public int CountTagBlogs(Integer tagid) {
        return blogMapper.CountTagBlogs(tagid);
    }

    @Override
    public List<Blog> findAllBlogsSortByTime() {
        return blogMapper.findAllBlogsSortByTime();
    }

    @Override
    public List<Blog> getLastestBlogs(int num) {
        return blogMapper.getLastestBlogs(num);
    }

    @Override
    public void addABlog(Blog blog) {
        blogMapper.addABlog(blog);
        List<Tag> tagList = tagMapper.findAllTags();
        int tagid = -1;
        for (Tag t:tagList) {
            if (t.getTagname().trim().equals(blog.getTags().trim())){
                tagid = t.getId();
                break;
            }
        }
        blogMapper.addBlogTag(blog.getId(),tagid);
    }

    @Override
    public int findMaxId() {
        return  blogMapper.findMaxId();
    }

    @Override
    public void addBlogTag(int blogid, int tagid) {
        blogMapper.addBlogTag(blogid,tagid);
    }

    @Override
    public void delBlog(int id) {
        blogMapper.delBlogById(id);
        blogMapper.delBlogTagRecord(id);
    }

    @Override
    public void updateBlog(Blog blog) {
        List<Tag> tagList = tagMapper.findAllTags();
        int tagid = -1;
        for (Tag t:tagList) {
            if (t.getTagname().trim().equals(blog.getTags().trim())){
                tagid = t.getId();
                break;
            }
        }
        blogMapper.updateBlog(blog);
        blogMapper.updateBlogTagRecord(blog.getId(),tagid);
    }
}
