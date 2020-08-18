package com.example.cms.controller;

import com.example.cms.entity.User;
import com.example.cms.exception.NotFoundException;
import com.example.cms.service.AdminService;
import com.example.cms.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author XIE
 * @date 2018/12/25
 */

@RestController
@ControllerAdvice
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public List<UserVO> getStudent() throws NotFoundException {
        List<User> userList = adminService.getStudentList();
        List <UserVO> userVOList = new ArrayList<UserVO>();
        for (User user:userList){
            userVOList.add(new UserVO(user));
        }
        return  userVOList;
    }

    @GetMapping("/searchStudent")
    public UserVO searchStudent(@RequestBody Map<String, Object>map) throws NotFoundException {
        String searchKey = map.get("searchKey").toString();
        UserVO userVO = new UserVO(adminService.searchStudent(searchKey));
        return userVO;
    }

    @RequestMapping(value = "/{studentId}/information",method = RequestMethod.PUT)
    public void modifyStudentInfo(@RequestBody Map<String, Object>map, @PathVariable("studentId")BigInteger studentId) throws NotFoundException {
        User user = new User();
        user.setId(studentId);
        user.setAccount(map.get("account").toString());
        user.setName(map.get("name").toString());
        user.setEmail(map.get("email").toString());
        adminService.modifyStudentInfo(user);
    }

    @RequestMapping(value = "/{studentId}/password",method = RequestMethod.PUT)
    public void resetStudentPassword(@PathVariable("studentId")BigInteger studentId) throws NotFoundException {
        User user = new User();
        user.setId(studentId);
        adminService.resetStudentPassword(user);
    }

    @RequestMapping(value = "/{studentId}",method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("studentId")BigInteger studentId) throws NotFoundException {
        adminService.deleteStudent(studentId);
    }
}
