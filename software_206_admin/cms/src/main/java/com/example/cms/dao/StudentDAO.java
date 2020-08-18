package com.example.cms.dao;

import com.example.cms.entity.User;
import com.example.cms.exception.InfoIllegalException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author XIE
 * @date 2018/12/17
 */
@Component
public class StudentDAO {

    @Autowired
    private StudentMapper studentMapper;

    public List<User> getStudentList() throws NotFoundException {
        List<User> userList=studentMapper.getStudentList();
        if (userList.size()==0){
            throw new NotFoundException("暂无学生导入");
        }
        return userList;
    }
    public User getBySearchKey(String searchKey) throws NotFoundException {
        User user = studentMapper.getBySearchKey(searchKey);
        if (user==null){
            throw new NotFoundException("学生未找到");
        }
        return  user;
    }

    public User getById(BigInteger id) throws NotFoundException {
        User user = studentMapper.getById(id);
        if (user==null){
            throw new NotFoundException("学生未找到");
        }
        return  user;
    }

    public void update(User user){
        studentMapper.update(user);
    }

    public void resetPassword(User user){
        studentMapper.resetPassword(user);
    }

    public void delete(BigInteger id){
        studentMapper.delete(id);
    }

}
