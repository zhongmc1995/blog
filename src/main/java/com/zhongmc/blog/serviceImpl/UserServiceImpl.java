package com.zhongmc.blog.serviceImpl;

import com.zhongmc.blog.dao.UserMapper;
import com.zhongmc.blog.domain.User;
import com.zhongmc.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongmc on 2017/3/13.
 * 用户服务接口实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findOneByUserName(String username) {
        return userMapper.findOneByUserName(username);
    }
}
