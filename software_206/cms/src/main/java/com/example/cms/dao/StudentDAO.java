package com.example.cms.dao;

import com.example.cms.entity.User;
import com.example.cms.exception.InfoIllegalException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.mapper.KlassStudentMapper;
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
    @Autowired
    private KlassStudentMapper klassStudentMapper;


    public void createAccount(User user)
    {
        studentMapper.createAccount(user);
    }

    public User getByAccount(String account) {
        /**/

        return studentMapper.getByAccount(account);
    }

    public String get(String account){
        /**/
        return studentMapper.get(account);
    }
    public void setPassword(BigInteger id, String password, String passwordNew) throws InfoIllegalException
    {
        studentMapper.setPassword(id,password,passwordNew);
    }
    public User getInformation(BigInteger id) throws UserNotFoundException
    {
        User user= studentMapper.getInformation(id);
        if(user==null)
        {
            throw new UserNotFoundException();
        }
        user.setRole(0);
        return user;
    }

    public void createKlassStudent(BigInteger klassId,BigInteger courseId,BigInteger studentId,BigInteger teamId)
    {
       klassStudentMapper.createKlassStudent(studentId,klassId,courseId,teamId);
    }

    public void deleteStuDate()
    {
        studentMapper.deleteStuDate();
    }


    public List<BigInteger> getStuIdByCourseId(BigInteger courseId)
    {
        return klassStudentMapper.getStuIdByCourseId(courseId);
    }

    public List<BigInteger> getStuIdByCourseIdAndTeamId(BigInteger courseId,BigInteger teamId)
    {
        return klassStudentMapper.getStuIdByCourseIdAndTeamId(courseId,teamId);
    }


    /**
     *xzy
     */
    public void activateStudent(User user, String passwordNew)
    {
        studentMapper.activateStudent(user.getId(), passwordNew);
    }

    public User getById(BigInteger id)
    {
        return studentMapper.getById(id);
    }

}
