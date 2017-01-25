package com.zhongmc.blog.dao;

import com.zhongmc.blog.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ZMC on 2017/1/16.
 */
@Mapper
public interface UserMapper {

    @Select("select * from tbl_user where username = #{username}")
    User findOneByUserName(String username);
}
