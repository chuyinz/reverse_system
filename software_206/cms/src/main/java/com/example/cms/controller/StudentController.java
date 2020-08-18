package com.example.cms.controller;

import com.example.cms.entity.User;
import com.example.cms.exception.InfoIllegalException;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * @author xzy,zyy,szw
 * @date 2018-12-25
 */
@RestController
@ControllerAdvice
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/active",method = RequestMethod.PUT)
    public void activateStudent(@RequestBody Map<String,Object> map, @RequestAttribute("user")User user) throws InvalidOperationException, InfoIllegalException {
        String password=map.get("password").toString();
        String email=map.get("email").toString();
        accountService.setEmail(user, email);
        accountService.activateStudent(user, password);
    }



}
