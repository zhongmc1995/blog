package com.zhongmc.blog.dao;

import com.zhongmc.blog.domain.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ZMC on 2017/1/16.
 */

@Mapper
public interface BlogMapper {

    @Select("select * from tbl_blog order by tbl_blog.createtime DESC")
    List<Blog> findAllBlog();

    @Select("select * from tbl_blog where id = #{id}")
    Blog findOneById(Integer id);

    @Select("SELECT b.id,b.title,b.content,b.keywords,b.viewnum,b.coverpic,b.createtime,b.createby,b.updatetime,b.updateby from " +
            " tbl_blog as b,tbl_blog_tag where b.id=tbl_blog_tag.blogid and tbl_blog_tag.tagid = #{tagid}")
    List<Blog> findBlogsByTagId(Integer tagid);

    //按年月筛选博客
    //多个参数是一定要写@param，要不然无法绑定
    @Select("SELECT * FROM tbl_blog WHERE createtime >= #{start} and createtime < #{end}")
    List<Blog> findBlogByYM(@Param("start") String start, @Param("end") String end);
}
