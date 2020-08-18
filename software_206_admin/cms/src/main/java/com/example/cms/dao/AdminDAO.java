package com.example.cms.dao;

import com.example.cms.entity.Admin;
import com.example.cms.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminDAO {
    @Autowired
    AdminMapper adminMapper;

    public Admin getByAccount(String account) {
        return adminMapper.getAdmin(account);
    }
}
