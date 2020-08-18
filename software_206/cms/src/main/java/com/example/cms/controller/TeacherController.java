package com.example.cms.controller;

import com.example.cms.entity.User;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * @author xzy,szw
 * @date 2018-12-25
 */
@RestController
@ControllerAdvice
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/active",method = RequestMethod.PUT)
    public void activateTeacher(@RequestBody Map<String,Object> map, @RequestAttribute("user")User user) throws InvalidOperationException {
        String passwordNew=map.get("passwordNew").toString();
        accountService.activateTeacher(user, passwordNew);
    }
}
