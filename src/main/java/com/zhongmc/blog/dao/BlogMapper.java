package com.zhongmc.blog.dao;

import com.zhongmc.blog.domain.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ZMC on 2017/1/16.
 */

@Mapper
public interface BlogMapper {

    @Select("select * from tbl_blog order by tbl_blog.createtime DESC")
    List<Blog> findAllBlog();

    @Select("select * from tbl_blog where id = #{id}")
    Blog findOneById(Integer id);

    //按博客类别分页查询
    @Select("SELECT b.id,b.title,b.content,b.keywords,b.viewnum,b.coverpic,b.createtime,b.createby,b.updatetime,b.updateby from " +
            " tbl_blog as b,tbl_blog_tag where b.id=tbl_blog_tag.blogid and tbl_blog_tag.tagid = #{tagid} limit #{startIndex},#{pageSize}")
    List<Blog> findBlogsByTagId(Map<String,Integer> map);

    //按年月筛选博客
    //多个参数是一定要写@param，要不然无法绑定
    @Select("SELECT * FROM tbl_blog WHERE createtime >= #{start} and createtime < #{end}")
    List<Blog> findBlogByYM(@Param("start") String start, @Param("end") String end);
    //分页查询记录
    @Select("select * from tbl_blog order by tbl_blog.createtime DESC limit #{startIndex},#{pageSize}")
    List<Blog> findBlogsByPage(Map<String,Integer> map);

    @Select("select count(*) from tbl_blog")
    int Count();

    @Select("SELECT count(*) from " +
            " tbl_blog as b,tbl_blog_tag where b.id=tbl_blog_tag.blogid and tbl_blog_tag.tagid = #{tagid}")
    int CountTagBlogs(Integer tagid);

    //博客按日期归档
    @Select("select * from tbl_blog ORDER BY createtime DESC;")
    List<Blog> findAllBlogsSortByTime();

    //筛选最新的blogs
    @Select("select id,title from tbl_blog order by createtime desc limit 0,#{num}")
    List<Blog> getLastestBlogs(int num);

    //添加博客
    @Select("insert into tbl_blog(id,title,content,keywords,createtime,createby) values(#{id},#{title},#{content},#{keywords},#{createTime},#{createBy})")
    void addABlog(Blog blog);

    //查询最大的id
    @Select("select MAX(id) from tbl_blog")
    int findMaxId();

    //加入博客和分类的关系表
    @Insert("insert into tbl_blog_tag(blogid,tagid) values(#{blogid},#{tagid})")
    void addBlogTag(@Param("blogid")int blogid,@Param("tagid") int tagid);

    //删除单条博客
    @Delete("delete from tbl_blog where id=#{id}")
    void delBlogById(int id);

    //删除博客和分类的关联记录
    @Delete("delete from tbl_blog_tag where blogid=#{blogid}")
    void delBlogTagRecord(int blogid);

    //更新博客
    @Update("update tbl_blog set title=#{title},keywords=#{keywords},content=#{content},updatetime=#{updateTime},updateby=#{updateBy} where id=#{id}")
    void updateBlog(Blog blog);

    //更新博客和分类的关联
    @Update("update tbl_blog_tag set tagid=#{tagid} where blogid=#{blogid}")
    void updateBlogTagRecord(@Param("blogid") int blogid,@Param("tagid") int tagid);
}
