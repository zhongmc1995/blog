package com.zhongmc.blog.controller;

import com.zhongmc.blog.domain.User;
import com.zhongmc.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ZMC on 2017/1/16.
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    IUserService userService;

    @RequestMapping("/user/{username}")
    @ResponseBody
    public String user(@PathVariable("username") String username){
        User user = userService.findOneByUserName(username);
        return user.toString();
    }
}
