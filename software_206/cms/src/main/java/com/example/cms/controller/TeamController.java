package com.example.cms.controller;

import com.example.cms.entity.Klass;
import com.example.cms.entity.User;
import com.example.cms.exception.CourseNotFoundException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.service.TeamService;
import com.example.cms.vo.*;
import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author szw
 * @date 2018-12-25
 */
@RestController
@ControllerAdvice
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;


    @RequestMapping(value = "/{teamId}",method = RequestMethod.DELETE)
    public void deleteTeam(@PathVariable("teamId")BigInteger teamId)
    {
        teamService.deleteTeam(teamId);
    }

    @RequestMapping(value = "/{teamId}/add",method = RequestMethod.PUT)
    public void addTeamMember(@PathVariable("teamId")BigInteger teamId, @RequestBody Map<String,Object> map) throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, NotFoundException {
        BigInteger id=new BigInteger(map.get("id").toString());
        teamService.addTeamMember(teamId,id);
    }

    @RequestMapping(value="/{teamId}/remove",method = RequestMethod.PUT)
    public void removeTeam(@PathVariable("teamId")BigInteger teamId, @RequestBody Map<String,Object> map ) throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, NotFoundException {
        BigInteger id=new BigInteger(map.get("id").toString());
        teamService.deleteTeamMember(teamId,id);
    }
    @RequestMapping(value="/{teamId}/teamvalidrequest",method = RequestMethod.POST)
    public void teamRequest(@PathVariable("teamId")BigInteger teamId, @RequestBody TeamRequestVO teamRequest) throws CourseNotFoundException, UserNotFoundException, NotFoundException {
        teamService.teamValidRequest(teamId,teamRequest.getCourseId(),teamRequest.getReason());
    }


}
