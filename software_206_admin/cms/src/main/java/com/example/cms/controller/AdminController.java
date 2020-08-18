package com.example.cms.controller;

import com.example.cms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author XIE
 * @date 2018/12/13
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "/login")
    public void userLogin() {

    }

    @GetMapping("/login")
    public ModelAndView userLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/login");
        return modelAndView;
    }
}
