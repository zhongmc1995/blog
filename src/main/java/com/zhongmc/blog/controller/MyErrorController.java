package com.zhongmc.blog.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZMC on 2017/1/26.
 */
@Controller
public class MyErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "";
    }
    @RequestMapping("/error")
    public String toErrorPage(HttpServletResponse response){
        int status = response.getStatus();
        if (status == 404){
            return "404";
        }else if (status==500){
            return "500";
        }else if(status==400){
            return "400";
        }
        return "unknown";
    }
}
