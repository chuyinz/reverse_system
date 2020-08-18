package com.example.cms.service;

import com.example.cms.controller.AdminController;
import com.example.cms.dao.StudentDAO;
import com.example.cms.mapper.StudentMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private StudentDAO studentDao;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminController adminController;
}