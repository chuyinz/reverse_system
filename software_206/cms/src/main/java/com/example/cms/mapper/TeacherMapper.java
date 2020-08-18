package com.example.cms.mapper;

import com.example.cms.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

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
     * 通过account查询教师用户
     * @param account 教师account
     * @return 教师实体
     */
    @Select({"SELECT * FROM teacher WHERE account=#{account}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "teacher_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    User getByAccount(@Param("account") String account);


    /**
     *创建教师用户
     * @param user 教师对象
     *
     */
    @Insert({"INSERT INTO teacher(account,password,email,is_active,teacher_name) VALUES(#{account},#{password},#{email},#{isActive},#{name})"})
    void createAccount(User user);

    /**
     *查看个人信息
     * @param id 序号
     * @return 信息
     */
    @Select({"SELECT * FROM teacher WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "name",column = "teacher_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
    })
    User getInformation(@Param("id")BigInteger id);


    /**
     *获取
     * @param id
     * @return
     */
    @Select({"SELECT * FROM teacher WHERE id=#{id} "})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "teacher_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    User getById(@Param("id")BigInteger id);


    /**
     * 通过教师姓名查询教师id
     * @param teacherName  教师姓名
     * @return 教师实体
     */
    @Select({"SELECT id FROM teacher WHERE teacher_name=#{teacherName}"})
    BigInteger getTeacherIdByName(@Param("teacherName")String teacherName);

    /**
     *修改邮箱
     *
     * @param id
     * @param email 新邮箱
     * @return
     */
    @Update({"UPDATE teacher SET email=#{email} WHERE id=#{id}"})
    void setEmail(@Param("id")BigInteger id,@Param("email") String email);

    /**
     *修改密码
     *
     * @param password 旧密码
     * @param passwordNew 新密码
     * @param id 序号
     * @return status=1
     */
    @Update({"UPDATE teacher SET password = #{passwordNew},is_active=1 WHERE id = #{id} AND password=#{password}"})
    void setPassword(@Param("id")BigInteger id,@Param("password")String password,@Param("passwordNew")String passwordNew);


    /**
     *激活账户
     *
     * @param passwordNew 新密码
     * @param id 序号
     * @return status=1
     */
    @Update({"UPDATE teacher SET password = #{passwordNew},is_active=1 WHERE id = #{id}"})
    void activateTeacher(@Param("id")BigInteger id,@Param("passwordNew")String passwordNew);



}