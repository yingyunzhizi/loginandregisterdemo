package com.qf.controller;

import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.utils.CheckCodeUtil;
import com.qf.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @data 5/16/2019 21:53
 * @user yingyunzhizi
 */
@Controller
@RequestMapping(value = "/userController")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 发送邮件
     * @param email
     * @param req
     * @return
     */
    @RequestMapping(value="/sendEmailToCheck")
    @ResponseBody
    public String sendEmailToCheck(String email, HttpServletRequest req){
        String randomCode = CheckCodeUtil.getRandomCode();
        try {
            SendEmail.SendEmailInfoUser(email, "注册的验证码", "注册的验证码是:"+randomCode, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomCode;
    }

    /**
     * index跳到注册
     * @return
     */
    @RequestMapping(value = "/toRegister")
    public String toRegister(){
        return "register";
    }

    /**
     * index跳到登录
     * @return
     */
    @RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 跳转到输入账号发送邮件去修改密码页面
     * @return
     */
    @RequestMapping(value = "/toForgetPw")
    public String toForgetPw(){
        return "forgetPw";
    }

    /**
     * 转发到更新密码的页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/toUpdatePw")
    public String toUpdatePw(Model model,int id){
        model.addAttribute("id",id);
        return "updatePw";
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Transactional
    @RequestMapping(value = "/registerUser")
    public String registerUser(User user){
        userService.registerUser(user);
        System.out.println(user);
        return "login";
    }

    /**
     * 通过用户name到数据库找到邮箱,并且发送改密的邮件
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/findUserByName")
    public String findUserByName(String name, Model model){
        User user = userService.findUserEmailByName(name);
        String content = "<html><head><meta charset=UTF-8></head><body><a href='http://localhost:8080/userController/toUpdatePw?id="+user.getId()+"'>找回密码</a></body></html>";
        //开始发送邮件,找到邮箱后就
        try {
            SendEmail.SendEmailInfoUser(user.getEmail(),"找回密码的链接",content,"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将邮箱名字放入model中,在success页面进行展示,暂时先不考虑隐藏部分字段,加密
        String emailHide = user.getEmail().substring(0,2)+"*******"+user.getEmail().split("@")[1];
        model.addAttribute("email",emailHide);
        return "successSendEmail";
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping(value = "/updatePwById")
    public String updatePwById(User user){
        Integer num = userService.updatePwById(user);
        System.out.println(num);
        return "successUpdatePw";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(User user){
        User userCheck = userService.findUserByName(user.getUsername());
        if (userCheck.getPassword().equals(user.getPassword())){
            return "用户名和密码正确,成功登陆";
        }
        return "用户名或密码不正确,请重新登录";
    }
}
