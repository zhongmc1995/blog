package com.zhongmc.blog.dao;

import com.zhongmc.blog.domain.Theme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhongmc on 2017/3/20.
 */
@Mapper
public interface ThemesMapper {
    //查询当前主题
    @Select("select themename from tbl_themes where status = 1")
    String getCurrentTheme();
    //查询所有的主题
    @Select("select * from tbl_themes")
    List<Theme> getAllThemes();
    //更新主题的状态
    @Update("update tbl_themes set status = #{status} where themename=#{name}")
    void updataThemeStatus(@Param("status") String status,@Param("name") String name);
}
