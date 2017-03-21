package com.zhongmc.blog.controller.admin;

import com.zhongmc.blog.domain.SessionData;
import com.zhongmc.blog.domain.User;
import com.zhongmc.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhongmc on 2017/3/19.
 */
@Controller
public class LoginController {
    @Autowired
    IUserService userService;
    //管理员登陆
    @RequestMapping(value = "/login")
    public String toLoginPage(){
        return "/admin/login";
    }
    @RequestMapping(value = "/dologin")
    @ResponseBody
    public Message doLogin(HttpServletRequest request, User user){
        Message msg = new Message();
        if (null!=user){

            User u = userService.findOneByUserName(user.getUsername());
            if (null==u){
                //用户名不存在
                msg.setResult(false);
                msg.setMsgInfo("用户名不存在");

            }else if (!u.getUsername().equals(user.getPassword())){
                //密码错误
                msg.setResult(false);
                msg.setMsgInfo("密码错误");
            }else {
                //验证通过
                msg.setResult(true);
                msg.setMsgInfo("登陆成功");
                request.getSession().setAttribute(SessionData.SESSION_KEY,user);
            }
            return msg;
        }else{
            msg.setResult(false);
            msg.setMsgInfo("登陆异常");
            return  msg;
        }

    }
    static class Message{
        private boolean result;

        public boolean getResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getMsgInfo() {
            return msgInfo;
        }

        public void setMsgInfo(String msgInfo) {
            this.msgInfo = msgInfo;
        }

        private String msgInfo;
    }
}
