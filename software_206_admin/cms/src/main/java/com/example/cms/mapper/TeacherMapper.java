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
 * @author shaoziwei
 * @date 2018/11/30
 */
/**
 * @author shaoziwei
 * @date 2018/12/13
 */

public interface TeacherMapper {

    /**
     *创建教师用户
     * @param user 教师对象
     *
     */
    @Insert({"INSERT INTO teacher(account,password,email,is_active,teacher_name) VALUES(#{account},#{password},#{email},#{isActive},#{name})"})
    void createAccount(User user);

    /**
     * 得到所有教师用户
     * @return 教师实体
     */
    @Select({"SELECT * FROM teacher"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "teacher_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    List<User> getTeacherList();

    /**
     * 查找教师
     * @param searchKey
     * @return
     */
    @Select({"SELECT * FROM teacher WHERE account=#{searchKey} OR teacher_name=#{searchKey}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "teacher_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    User getBySearchKey(String searchKey);

    /**
     * 获取教师
     * @param id
     * @return
     */
    @Select({"SELECT * FROM teacher WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "teacher_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    User getById(BigInteger id);

    /**
     * 修改信息
     * @param user
     * @return
     */
    @Update({"UPDATE teacher SET account=#{account},teacher_name=#{name},email=#{email} WHERE id=#{id}"})
    void update(User user);

    /**
     * 重置密码
     * @param user
     */
    @Update({"UPDATE teacher SET password='123456' WHERE id=#{id}"})
    void resetPassword(User user);

    /**
     * 删除账户
     * @param id
     */
    @Delete({"DELETE FROM teacher WHERE id=#{id}"})
    void delete(BigInteger id);
}