package com.zhongmc.blog.serviceImpl;

import com.zhongmc.blog.dao.ThemesMapper;
import com.zhongmc.blog.domain.Theme;
import com.zhongmc.blog.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongmc on 2017/3/20.
 */
@Service
public class ThemeServiceImpl implements IThemeService {

    @Autowired
    ThemesMapper themesMapper;
    @Override
    public String getCurrentTheme() {
        return themesMapper.getCurrentTheme();
    }

    @Override
    public List<Theme> getAllThemes() {
        return themesMapper.getAllThemes();
    }

    @Override
    public void updataThemeStatus(String status, String name) {
        themesMapper.updataThemeStatus(status,name);
    }
}
