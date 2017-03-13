package com.zhongmc.blog.service;

import com.zhongmc.blog.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhongmc on 2017/3/13.
 * 用户服务接口
 */
public interface IUserService {

    //通过username查询用户
    User findOneByUserName(String username);
}
