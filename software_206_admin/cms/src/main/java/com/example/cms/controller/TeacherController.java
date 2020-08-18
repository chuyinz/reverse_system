package com.example.cms.controller;

import com.example.cms.entity.User;
import com.example.cms.exception.AccessDenyException;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private AdminService adminService;

    @PostMapping("")
    public UserVO createTeacher(@RequestBody Map<String, Object>map) throws AccessDenyException {
        User user = new User();
        user.setAccount(map.get("account").toString());
        user.setName(map.get("name").toString());
        user.setPassword(map.get("password").toString());
        user.setEmail(map.get("email").toString());
        user.setActive(new Integer("0"));
        user = adminService.createTeacher(user);
        return new UserVO(user);
    }

    @GetMapping("")
    public List<UserVO> getTeacherList() throws NotFoundException {
        List<User> userList = adminService.getTeacherList();
        List <UserVO> userVOList = new ArrayList<UserVO>();
        for (User user:userList){
            userVOList.add(new UserVO(user));
        }
        return  userVOList;
    }

    @GetMapping("/searchTeacher")
    public UserVO searchTeacher(@RequestBody Map<String, Object>map) throws NotFoundException {
        String searchKey = map.get("searchKey").toString();
        UserVO userVO = new UserVO(adminService.searchTeacher(searchKey));
        return userVO;
    }

    @RequestMapping(value = "/{teacherId}/information",method = RequestMethod.PUT)
    public void modifyTeacherInfo(@RequestBody Map<String, Object>map, @PathVariable("teacherId") BigInteger teacherId) throws NotFoundException {
        User user = new User();
        user.setId(teacherId);
        user.setAccount(map.get("account").toString());
        user.setName(map.get("name").toString());
        user.setEmail(map.get("email").toString());
        adminService.modifyTeacherInfo(user);
    }

    @RequestMapping(value = "/{teacherId}/password",method = RequestMethod.PUT)
    public void resetStudentPassword(@RequestBody Map<String, Object>map, @PathVariable("teacherId")BigInteger teacherId) throws NotFoundException {
        User user = new User();
        user.setId(teacherId);
        adminService.resetTeacherPassword(user);
    }

    @RequestMapping(value = "/{teacherId}",method = RequestMethod.DELETE)
    public void deleteTeacher(@PathVariable("teacherId")BigInteger teacherId) throws NotFoundException {
        adminService.deleteTeacher(teacherId);
    }
}
