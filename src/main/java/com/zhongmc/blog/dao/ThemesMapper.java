package com.zhongmc.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhongmc on 2017/3/20.
 */
@Mapper
public interface ThemesMapper {
    //查询当前主题
    @Select("select themename from tbl_themes where status = 1")
    String getCurrentTheme();
}
