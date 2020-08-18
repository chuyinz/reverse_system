package com.example.cms.mapper;

import com.example.cms.entity.User;
import com.example.cms.exception.InfoIllegalException;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.UserNotFoundException;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author XIE
 * @date 2018/12/14
 */
public interface StudentMapper {

    /**
     * 得到所有学生用户
     * @return 学生实体
     */
    @Select({"SELECT * FROM student"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "student_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    List<User> getStudentList();

    /**
     * 查找学生
     * @param searchKey
     * @return
     */
    @Select({"SELECT * FROM student WHERE account=#{searchKey} OR student_name=#{searchKey}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "student_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    User getBySearchKey(String searchKey);

    /**
     * 获取学生
     * @param id
     * @return
     */
    @Select({"SELECT * FROM student WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "student_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    User getById(BigInteger id);

    /**
     * 修改信息
     * @param user
     * @return
     */
    @Update({"UPDATE student SET account=#{account},student_name=#{name},email=#{email} WHERE id=#{id}"})
    void update(User user);

    /**
     * 重置密码
     * @param user
     */
    @Update({"UPDATE student SET password='123456' WHERE id=#{id}"})
    void resetPassword(User user);

    /**
     * 删除账户
     * @param id
     */
    @Delete({"DELETE FROM student WHERE id=#{id}"})
    void delete(BigInteger id);
}
