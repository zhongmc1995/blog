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
    //通过博客id搜素该博客所属于的标签
    @Select("select tag.id,tag.tagname,tag.createtime,tag.createby,tag.updatetime,tag.updateby " +
            "FROM tbl_tag as tag,tbl_blog_tag " +
            "where tbl_blog_tag.blogid=#{blogid} and tbl_blog_tag.tagid = tag.id ")
    List<Tag> findTagsByBlogId(int blogid);

    //查找所有的标签
    @Select("select * from tbl_tag order by createtime desc")
    List<Tag> findAllTags();

    //统计标签的博客数
    @Select("select count(*) from tbl_blog_tag where tbl_blog_tag.tagid = #{tagid}")
    int calBlogNumByTagId(int tagid);

    //
    @Select("select * from tbl_tag where id = #{tagid}")
    Tag findOneById(Integer tagid);

}
