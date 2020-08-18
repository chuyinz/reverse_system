package com.example.cms.mapper;

import com.example.cms.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @author XIE
 * @date 2018/12/14
 */
public interface StudentMapper {


    /**
     *创建学生用户
     * @param user 学生对象
     *
     */
    @Insert({"INSERT INTO student(account,password,email,is_active,student_name) VALUES(#{account},#{password},#{email},#{isActive},#{name})"})
    void createAccount(User user);

    /**
     * 通过account查询学生用户
     * @param account 学生account
     * @return 学生实体
     */
    @Select({"SELECT * FROM student WHERE account=#{account} "})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "student_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    public User getByAccount(@Param("account") String account);


    /**
     *修改密码，激活账户
     *
     * @param password 旧密码
     * @param passwordNew 新密码
     * @param id 序号
     * @return status=1
     */
    @Update({"UPDATE student SET password = #{passwordNew},is_active=1 WHERE id = #{id} AND password=#{password}"})
    void setPassword(@Param("id")BigInteger id,@Param("password")String password,@Param("passwordNew")String passwordNew);

    /**
     *查看个人信息
     * @param id 序号
     * @return 信息
     */
    @Select({"SELECT * FROM student WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "name",column = "student_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "password",column="null")
    })
    User getInformation(@Param("id")BigInteger id);


    /**
     * a
     * @param id
     * @return
     */
    @Select({"SELECT * FROM student WHERE id=#{id} "})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "account", column = "account",javaType = String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "isActive",column = "is_active",javaType = Integer.class),
            @Result(property = "name",column = "student_name",javaType = String.class ),
            @Result(property = "email",column = "email",javaType = String.class)
    })
    User getById(@Param("id")BigInteger id);


    /**
     * 更新学生邮箱
     * @param email 邮箱
     * @param id 学生id
     */
    @Update({"UPDATE student SET email=#{email} WHERE id=#{id}"})
    public void setEmail(@Param("id") BigInteger id, @Param("email") String email);

    /**
     * 更新学生姓名
     * @param name 姓名
     * @param id 学生id
     */
    @Update({"UPDATE student SET student_name=#{name} WHERE id=#{id}"})
    public void setName(@Param("id") BigInteger id, @Param("name") String name);


    /**
     *获取
     * @param account
     * @return
     */
    @Select({"SELECT password FROM student WHERE account=#{account}"})
    public String get(@Param("account") String account);


    /**
     *删除
     */
    @Delete({"DELETE FROM student"})
    public void deleteStuDate();

    /**
     *激活账户

     * @param passwordNew 新密码
     * @param id 序号
     * @return status=1
     */
    @Update({"UPDATE student SET password = #{passwordNew},is_active=1 WHERE id = #{id}"})
    void activateStudent(@Param("id")BigInteger id, @Param("passwordNew")String passwordNew);

}
