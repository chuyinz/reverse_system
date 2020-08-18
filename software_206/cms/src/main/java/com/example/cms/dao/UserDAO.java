package com.example.cms.dao;

import com.example.cms.entity.User;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.mapper.StudentMapper;
import com.example.cms.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author XIE
 * @date 2018/12/17
 */
@Component
public class UserDAO {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    public User getByAccount(String account)throws UsernameNotFoundException {
        User user = null;
        try{
            user = teacherMapper.getByAccount(account);
            if (user!=null){
                user.setRole(1);
            }

            else {
                user = studentMapper.getByAccount(account);
                if(user!=null) {
                    user.setRole(0);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }



    /**
     *xzy
     */
    public void setEmail(User user,String newEmail) throws InvalidOperationException {

        try
        {
            if (user.getRole()==1){
                teacherMapper.setEmail(user.getId(),newEmail);
            }

            else {
                studentMapper.setEmail(user.getId(),newEmail);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new InvalidOperationException("信息不合法");
        }
    }



    public void setPassword(User user, String password,String passwordNew){

        if (user.getRole()==1){
            teacherMapper.setPassword(user.getId(),password,passwordNew);
        }

        else {
            studentMapper.setPassword(user.getId(),password,passwordNew);
        }
    }


    public User getInformation(User user){

        if (user.getRole()==1){
            return teacherMapper.getInformation(user.getId());
        }

        else {
            return studentMapper.getInformation(user.getId());
        }
    }
}