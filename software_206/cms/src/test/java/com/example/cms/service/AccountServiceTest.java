package com.example.cms.service;

import com.example.cms.controller.UserController;
import com.example.cms.dao.StudentDAO;
import com.example.cms.entity.User;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.mapper.StudentMapper;
import com.example.cms.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private StudentDAO studentDao;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserController userController;
}