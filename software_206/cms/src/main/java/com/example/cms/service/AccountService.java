package com.example.cms.service;

import com.example.cms.dao.StudentDAO;
import com.example.cms.dao.TeacherDAO;
import com.example.cms.dao.UserDAO;
import com.example.cms.entity.User;
import com.example.cms.exception.InfoIllegalException;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.service.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

/**
 * @author xzy
 * @date 2018/12/17
 */
@Service
@Component
public class AccountService {

    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private UserDAO userDAO;


    /**
     *  public void setPassword(User user, String password,String passwordNew) throws InfoIllegalException
     *     {
     *             userDAO.setPassword(user, password, passwordNew);
     *     }
     */

    public void setPassword(User user, String password,String passwordNew) throws InvalidOperationException {
        User rUser=userDAO.getInformation(user);
        if(rUser.getPassword().equals(password)) {
            int passwordLen=6;
            if (passwordNew.length() >= passwordLen) {
                userDAO.setPassword(user, password, passwordNew);
            }
            else
            {
                throw new InvalidOperationException("信息不合法");
            }
        }
        else
        {
            throw new InvalidOperationException("信息不合法");
        }
    }




    /**
     *
     * public User getInformation(User user)throws UserNotFoundException
     *     {
     *                 User rUser;
     *                 rUser=userDAO.getInformation(user);
     *                 rUser.setRole(user.getRole());
     *                 return rUser;
     *     }
     */

    public User getInformation(User user)throws UserNotFoundException
    {
        if(user.getRole()==1) {
            return teacherDAO.getInformation(user.getId());
        }
        else{
            return studentDAO.getInformation(user.getId());
        }
    }

    public void setEmail(User user, String emailNew) throws InfoIllegalException, InvalidOperationException {
        String rule="[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
        if(emailNew.matches(rule)) {
            userDAO.setEmail(user, emailNew);
        }
        else
        {
            throw new InvalidOperationException("信息不合法");
        }
    }


    public User getByAccount(String account)throws UserNotFoundException
    {
        return userDAO.getByAccount(account);
    }


    public void activateStudent(User user, String passwordNew)throws InvalidOperationException
    {
        int passwordLen=6;
        if (passwordNew.length()>=passwordLen) {
            studentDAO.activateStudent(user,passwordNew);
        }
        else {
            throw new InvalidOperationException("信息不合法");
        }
    }


    public void activateTeacher(User user,String passwordNew)throws InvalidOperationException
    {
        int passwordLen=6;
        if (passwordNew.length() >= passwordLen) {
            teacherDAO.activateTeacher(user, passwordNew);
        }
        else
        {
            throw new InvalidOperationException("信息不合法");
        }
    }

    public  BigInteger getTeacherIdByName(String teacherName) throws UserNotFoundException
    {
        return  teacherDAO.getTeacherIdByName(teacherName);
    }


    /**
     *导入学生名单
     */
    public void ajaxUploadExcel(MultipartFile file,BigInteger classId,BigInteger courseId) throws Exception {

        if(file.isEmpty()){
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Object>> listob = null;
        try {
            listob = new ExcelUtils().getBankListByExcel(in,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }


        //调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 1; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            User student = new User();
            String account=String.valueOf(lo.get(0));
            System.out.println(account);
            account.replaceAll("[\\p{Cntrl}\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}^\\x00-\\x7F]\\p{C}","");
            account.trim();
            student.setAccount(account);
            String name=(String.valueOf(lo.get(1)));
            name=new String((name).getBytes("Unicode"), "UTF8");
            System.out.println(name);
            student.setName(name);
            student.setRole(0);
            student.setActive(0);
            student.setPassword("123456");
            User studentInlist=studentDAO.getByAccount(student.getAccount());
            if(studentInlist==null)
            {
                studentDAO.createAccount(student);
                student=studentDAO.getByAccount(student.getAccount());
                studentDAO.createKlassStudent(classId,courseId,student.getId(),null);
            }
            else
            {
                studentDAO.createKlassStudent(classId,courseId,studentInlist.getId(),null);
            }

        }

        in.close();


    }

}
