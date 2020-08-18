package com.example.cms.controller;

import com.example.cms.entity.Attendance;
import com.example.cms.entity.Team;
import com.example.cms.entity.User;
import com.example.cms.exception.AccessDenyException;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.SeminarNotFoundException;
import com.example.cms.service.SeminarDisplayService;
import com.example.cms.service.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/**
 * @author xzy
 * @date 2018-12-25
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private SeminarDisplayService seminarDisplayService;


    @PostMapping(value = "/{seminarId}/class/{classId}/attendance")
    public ResponseEntity enrollPresentation(@PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user") User user, @RequestBody Map<String,Object> map) throws AccessDenyException, NotFoundException, SeminarNotFoundException, InvalidOperationException {
        Team team = new Team();
        team.setId(new BigInteger(map.get("teamId").toString()));
        Integer teamOrder=new Integer(map.get("teamOrder").toString());
        return new ResponseEntity(seminarDisplayService.enrollPresentation(seminarId,klassId,team,teamOrder,user), HttpStatus.ACCEPTED);
    }

    /**
     * 修改报名展示
     * @param userId 用户id
     * @param map
     * @param attendanceId
     * @return
     * @throws AccessDenyException
     * @throws NotFoundException
     */
    @PostMapping(value = "/{attendanceId}")
    public ResponseEntity modifyEnrollAttendance(@RequestAttribute("userId")BigInteger userId, @RequestBody Map<String,Object> map, @PathVariable("attendanceId") BigInteger attendanceId) throws AccessDenyException, NotFoundException {
        Integer teamOrder = new Integer(map.get("teamOrder").toString());
        Attendance attendance = seminarDisplayService.getAttendanceById(attendanceId);
        seminarDisplayService.modifyEnrollAttendance(userId, teamOrder, attendance);
        return new ResponseEntity(attendance.getId(), HttpStatus.valueOf(201));
    }


    /**
     * 取消报名展示
     * @param userId
     * @param attendanceId
     * @return
     * @throws AccessDenyException
     * @throws NotFoundException
     */
    @RequestMapping(value = "/{attendanceId}",method = RequestMethod.DELETE)
    public ResponseEntity cancelAttendance(@RequestAttribute("userId")BigInteger userId, @PathVariable("attendanceId") BigInteger attendanceId) throws AccessDenyException, NotFoundException, InvalidOperationException, SeminarNotFoundException {
        Attendance attendance ;
        if(seminarDisplayService.getAttendanceById(attendanceId)==null){
            throw new NotFoundException("不存在该报名");
        }
        attendance = seminarDisplayService.getAttendanceById(attendanceId);
        seminarDisplayService.cancelAttendance(userId, attendance);
        return new ResponseEntity("成功删除",HttpStatus.valueOf(201));
    }


    /**
     * 上传讨论课报告
     * @param attendanceId
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value="/{attendanceId}/report")
    public ResponseEntity postReport(@PathVariable("attendanceId")BigInteger attendanceId, @RequestParam("fileUpload") MultipartFile file) throws IOException, NotFoundException, InvalidOperationException {
        seminarDisplayService.timeOut(attendanceId);
        try{String path = FileUtils.uploadFile(file,"report");
            seminarDisplayService.postReport(attendanceId, path, file.getOriginalFilename());
            return new ResponseEntity(path, HttpStatus.valueOf(201));
        }catch (Exception e) {
            e.printStackTrace();;
            return new ResponseEntity("file Invalid", HttpStatus.valueOf(400));
        }
    }


    /**
     * 重传讨论课报告
     * @param attendanceId
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/{attendanceId}/updateReport",method = RequestMethod.PUT)
    public ResponseEntity updateReport(@PathVariable("attendanceId")BigInteger attendanceId, @RequestParam("fileUpload") MultipartFile file) throws IOException, NotFoundException, InvalidOperationException {
        seminarDisplayService.timeOut(attendanceId);
        try{String path = FileUtils.uploadFile(file,"report");
            seminarDisplayService.postReport(attendanceId, path, file.getOriginalFilename());
            return new ResponseEntity(path, HttpStatus.valueOf(201));
        }catch (Exception e) {
            e.printStackTrace();;
            return new ResponseEntity("file Invalid", HttpStatus.valueOf(400));
        }
    }


    /**
     * 上传讨论课ppt
     * @param attendanceId
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value="/{attendanceId}/powerpoint")
    public ResponseEntity postPowerpoint(@PathVariable("attendanceId")BigInteger attendanceId, @RequestParam("fileUpload") MultipartFile file) throws IOException {
        try{String path = FileUtils.uploadFile(file,"ppt");
            seminarDisplayService.postPpt(attendanceId, path, file.getOriginalFilename());
            return new ResponseEntity(path, HttpStatus.valueOf(201));
        }catch (Exception e) {
            e.printStackTrace();;
            return new ResponseEntity("file Invalid", HttpStatus.valueOf(400));
        }
    }


    /**
     * 重传讨论课ppt
     * @param attendanceId
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/{attendanceId}/updatePowerpoint",method = RequestMethod.PUT)
    public ResponseEntity updatePowerpoint(@PathVariable("attendanceId")BigInteger attendanceId, @RequestParam("fileUpload") MultipartFile file) throws IOException {
        try{String path = FileUtils.uploadFile(file,"ppt");
            seminarDisplayService.postPpt(attendanceId, path, file.getOriginalFilename());
            return new ResponseEntity(path, HttpStatus.valueOf(201));
        }catch (Exception e) {
            e.printStackTrace();;
            return new ResponseEntity("file Invalid", HttpStatus.valueOf(400));
        }
    }


    /**
     * 下载讨论课ppt
     * @param attendanceId
     * @param request
     * @param response
     * @throws NotFoundException
     * @throws IOException
     */
    @GetMapping(value="/{attendanceId}/ppt")
    public void downloadPowerpoint(@PathVariable("attendanceId")BigInteger attendanceId, HttpServletRequest request,HttpServletResponse response) throws NotFoundException, IOException {

        Attendance attendance=seminarDisplayService.getAttendanceById(attendanceId);
        if (attendance.getPptUrl()==null){
            throw new NotFoundException("文件不存在");
        }
        String path = attendance.getPptUrl();
        String name = attendance.getPptName();
        FileUtils.downloadFile(response,path,name);
    }


    /**
     *下载讨论课报告
     * @param attendanceId
     * @param request
     * @param response
     * @throws NotFoundException
     * @throws IOException
     */
    @GetMapping(value="/{attendanceId}/report")
    public void downloadReport(@PathVariable("attendanceId")BigInteger attendanceId, HttpServletRequest request,HttpServletResponse response) throws NotFoundException, IOException {

        Attendance attendance=seminarDisplayService.getAttendanceById(attendanceId);
        if (attendance.getReportUrl()==null){
            throw new NotFoundException("文件不存在");
        }
        String path = attendance.getReportUrl();
        String name = attendance.getReportName();
        FileUtils.downloadFile(response,path,name);
    }


}
