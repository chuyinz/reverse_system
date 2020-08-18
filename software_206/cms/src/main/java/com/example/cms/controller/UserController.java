package com.example.cms.controller;

import com.example.cms.entity.User;
import com.example.cms.exception.InfoIllegalException;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.service.AccountService;
import com.example.cms.security.auth.JwtService;

import com.example.cms.service.utils.MailUtils;
import com.example.cms.vo.LoginSuccessVO;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Map;

/**
 * @author XIE
 * @date 2018/12/13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public void userLogin() {

    }

    @GetMapping("/login")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("/user/login");
        return modelAndView;
    }




    @GetMapping("/information")
    public User getInformation(@RequestAttribute("user")User user) throws UserNotFoundException
    {
        return accountService.getInformation(user);
    }

    @GetMapping("/password")
    public void forgetPassword(@RequestBody Map<String,Object> map) throws Exception {
        String account=map.get("account").toString();
        User user=accountService.getByAccount(account);
        MailUtils.sendEmail(user.getEmail(),"原密码",user.getPassword());
    }

    @RequestMapping(value = "/password",method = RequestMethod.PUT)
    public void setPassword(@RequestBody Map<String,Object> map,@RequestAttribute("user")User user) throws InfoIllegalException, InvalidOperationException {
        String password=map.get("password").toString();
        String passwordNew=map.get("passwordNew").toString();
        accountService.setPassword(user,password,passwordNew);
    }

    @RequestMapping(value = "/email",method = RequestMethod.PUT)
    public void modifyEmail(@RequestBody Map<String,Object> map,@RequestAttribute("user")User user) throws InvalidOperationException, InvalidOperationException, InfoIllegalException {
        String email=map.get("email").toString();
        accountService.setEmail(user,email);
    }
}
