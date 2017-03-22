package com.zhongmc.blog.service;

import com.zhongmc.blog.domain.Theme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhongmc on 2017/3/20.
 * 主题
 */
public interface IThemeService {
    String getCurrentTheme();
    List<Theme> getAllThemes();
    void updataThemeStatus(String status, String name);
}
