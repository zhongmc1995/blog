package com.zhongmc.blog.dao;

import com.zhongmc.blog.domain.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ZMC on 2017/1/16.
 */

@Mapper
public interface BlogMapper {

    @Select("select * from tbl_blog")
    List<Blog> findAllBlog();

    @Select("select * from tbl_blog where id = #{id}")
    Blog findOneById(Integer id);

    @Select("SELECT b.id,b.title,b.content,b.keywords,b.viewnum,b.coverpic,b.createtime,b.createby,b.updatetime,b.updateby from " +
            " tbl_blog as b,tbl_blog_tag where b.id=tbl_blog_tag.blogid and tbl_blog_tag.tagid = #{tagid}")
    List<Blog> findBlogsByTagId(Integer tagid);
}
