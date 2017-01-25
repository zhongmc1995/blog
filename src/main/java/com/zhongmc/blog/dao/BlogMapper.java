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
}
