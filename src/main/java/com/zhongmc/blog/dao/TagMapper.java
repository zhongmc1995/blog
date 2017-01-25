package com.zhongmc.blog.dao;

import com.zhongmc.blog.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ZMC on 2017/1/25.
 */
@Mapper
public interface TagMapper {
    @Select("select tag.id,tag.tagname,tag.createtime,tag.createby,tag.updatetime,tag.updateby " +
            "FROM tbl_tag as tag,tbl_blog_tag " +
            "where tbl_blog_tag.blogid=#{blogid} and tbl_blog_tag.tagid = tag.id ")
    List<Tag> findTagsByBlogId(int blogid);
}
