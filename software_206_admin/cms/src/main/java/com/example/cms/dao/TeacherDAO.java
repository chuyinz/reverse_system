package com.example.cms.dao;

import com.example.cms.entity.User;
import com.example.cms.exception.AccessDenyException;
import com.example.cms.exception.InfoIllegalException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class TeacherDAO {
    @Autowired
    private TeacherMapper teacherMapper;

    public User createTeacher(User user) throws AccessDenyException {
        if (teacherMapper.getBySearchKey(user.getAccount())!=null){
            throw new AccessDenyException("账户已存在");
        }
        teacherMapper.createAccount(user);
        return teacherMapper.getBySearchKey(user.getAccount());
    }

    public List<User> getTeacherList() throws NotFoundException {
        List<User> userList=teacherMapper.getTeacherList();
        if (userList.size()==0){
            throw new NotFoundException("暂无教师导入");
        }
        return userList;
    }

    public User getBySearchKey(String searchKey) throws NotFoundException {
        User user = teacherMapper.getBySearchKey(searchKey);
        if (user==null){
            throw new NotFoundException("教师未找到");
        }
        return  user;
    }

    public User getById(BigInteger id) throws NotFoundException {
        User user = teacherMapper.getById(id);
        if (user==null){
            throw new NotFoundException("教师未找到");
        }
        return  user;
    }

    public void update(User user){
        teacherMapper.update(user);
    }

    public void resetPassword(User user){
        teacherMapper.resetPassword(user);
    }

    public void delete(BigInteger id){
        teacherMapper.delete(id);
    }
}
