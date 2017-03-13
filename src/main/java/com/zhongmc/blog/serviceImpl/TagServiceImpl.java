package com.zhongmc.blog.serviceImpl;

import com.zhongmc.blog.dao.TagMapper;
import com.zhongmc.blog.domain.Tag;
import com.zhongmc.blog.service.ITagServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongmc on 2017/3/13.
 * 标签分类实现类
 */
@Service
public class TagServiceImpl implements ITagServive {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> findTagsByBlogId(int blogid) {
        return tagMapper.findTagsByBlogId(blogid);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagMapper.findAllTags();
    }

    @Override
    public int calBlogNumByTagId(int tagid) {
        return tagMapper.calBlogNumByTagId(tagid);
    }

    @Override
    public Tag findOneById(Integer tagid) {
        return tagMapper.findOneById(tagid);
    }
}
