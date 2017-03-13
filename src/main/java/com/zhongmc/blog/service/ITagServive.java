package com.zhongmc.blog.service;

import com.zhongmc.blog.domain.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhongmc on 2017/3/13.
 * 标签分类服务接口
 */
public interface ITagServive {

    //通过博客id搜素该博客所属于的标签
    List<Tag> findTagsByBlogId(int blogid);

    //查找所有的标签
    List<Tag> findAllTags();

    //统计标签的博客数
    int calBlogNumByTagId(int tagid);

    //查询指定标签
    Tag findOneById(Integer tagid);
}
