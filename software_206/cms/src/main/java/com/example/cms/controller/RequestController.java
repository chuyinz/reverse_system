package com.example.cms.controller;


import com.example.cms.dao.CourseDAO;
import com.example.cms.dao.TeamDAO;
import com.example.cms.entity.Course;
import com.example.cms.entity.User;
import com.example.cms.exception.AccessDenyException;
import com.example.cms.exception.CourseNotFoundException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.service.CourseService;
import com.example.cms.service.TeamService;
import com.example.cms.vo.RequestVO;
import com.example.cms.vo.ShareTeamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author zyy
 * @date 2018-12-30
 */
@RestController
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private TeamDAO teamDAO;

    /**
     *获得队伍共享、合法申请信息列表
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public RequestVO getAllRequest(@RequestAttribute("user")User user) throws Exception {
       return  teamService.getAllRequest(user);
    }


    /**
     *按id修改共享组队请求状态
     */
    @RequestMapping(value = "/teamshare/{teamshareId}", method = RequestMethod.POST)
    public void setTeamShareRequest(@PathVariable("teamshareId") BigInteger teamshareId,@RequestBody Map<String,Object> map, @RequestAttribute("user") User user) throws Exception {
        if(user.getRole()==0)
        {
            throw new AccessDenyException(("学生没有权限处理组队共享请求"));
        }
        else {
            List<Course>courseList=courseDAO.getCourseByteacherId(user.getId());
            List<BigInteger>courseIdList=new ArrayList<BigInteger>();
            for(int i=0;i<courseList.size();i++)
            {
                courseIdList.add(courseList.get(i).getId());
            }

            BigInteger mainCourseId=teamDAO.getMainIdById(teamshareId);
            BigInteger subCourseId=teamDAO.getSubIdById(teamshareId);
            if(!courseIdList.contains(subCourseId))
            {
                throw new AccessDenyException(("您没有权限处理此共享"));
            }
        }
        String handletype=map.get("handletype").toString();
        teamService.setTeamShareRequest(teamshareId,handletype);
    }


    /**
     * 按id修改组队合法请求状态
     */
    @RequestMapping(value = "/teamvalid/{teamvalidId}", method = RequestMethod.POST)
    public void setTeamValidRequest(@PathVariable("teamvalidId") BigInteger teamvalidId,@RequestBody Map<String,Object> map, @RequestAttribute("user") User user) throws AccessDenyException, CourseNotFoundException {

        String handletype=map.get("handletype").toString();
        teamService.setTeamValidRequest(teamvalidId,handletype);
    }


}
