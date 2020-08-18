package com.example.cms.dao;

import com.example.cms.entity.User;
import com.example.cms.exception.InfoIllegalException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.mapper.TeacherMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
/**
 * @author xzy szw
 * @date 2018-12-25
 */
@Component
public class TeacherDAO {
    @Autowired
    private TeacherMapper teacherMapper;

    public void createTeacher(User user)
    {
        teacherMapper.createAccount(user);
    }
    public User getByAccount(String account) {
        /**/

        return teacherMapper.getByAccount(account);
    }

    public User getInformation(BigInteger id) throws UserNotFoundException
    {
        User user= teacherMapper.getInformation(id);
        user.setRole(1);
        return user;
    }

    public void setPassword(BigInteger id, String password,String passwordNew) throws InfoIllegalException
    {
        teacherMapper.setPassword(id,password,passwordNew);
    }


    public  BigInteger getTeacherIdByName(String teacherName) throws UserNotFoundException {

        if(teacherMapper.getTeacherIdByName(teacherName)==null)
        {
            throw new UserNotFoundException();
        }
        return teacherMapper.getTeacherIdByName(teacherName);
    }


    /**
     *xzy
     */
    public void activateTeacher(User user,String passwordNew)
    {
        teacherMapper.activateTeacher(user.getId(),passwordNew);
    }
}
