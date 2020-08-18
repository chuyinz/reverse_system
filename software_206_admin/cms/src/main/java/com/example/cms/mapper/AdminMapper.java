package com.example.cms.mapper;


import com.example.cms.entity.Admin;
import com.example.cms.entity.User;
import com.example.cms.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Mapper
@Component
public interface AdminMapper {

    /**
     * 管理员登录
     * @param account  管理员对象
     * @return 返回管理员对象
     */
    @Select({"SELECT * FROM admin WHERE account= #{account}"})
    Admin getAdmin(String account);






}
