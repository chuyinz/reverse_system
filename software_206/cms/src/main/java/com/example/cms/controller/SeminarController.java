package com.example.cms.controller;

import com.example.cms.entity.*;
import com.example.cms.exception.*;
import com.example.cms.service.TeamService;
import com.example.cms.vo.*;
import com.example.cms.service.CourseService;
import com.example.cms.service.SeminarDisplayService;
import com.example.cms.service.SeminarManaService;
import com.example.cms.service.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.poi.hslf.record.RecordTypes.List;

/**
 * @author XIE
 * @date 2018/12/25
 */
@RestController
@RequestMapping("/seminar")
public class SeminarController {
    @Autowired
    private SeminarManaService seminarManaService;
    @Autowired
    private SeminarDisplayService seminarDisplayService;
    @Autowired
    CourseService courseService;
    @Autowired
    TeamService teamService;

    @PostMapping
    public ResponseEntity createSeminar(@RequestBody Map<String,Object> map,@RequestAttribute("user")User user) throws ParseException, AccessDenyException, CourseNotFoundException, NotFoundException, ClassNotFoundException, SeminarNotFoundException {
        String topic = map.get("topic").toString();
        String intro = map.get("intro").toString();
        Integer order =new Integer(map.get("order").toString());
        Integer roundOrder = new Integer(map.get("roundOrder").toString());
        Integer visible = "true".equals(map.get("visible").toString()) ?1:0;
        Integer teamNumLimit = new Integer(map.get("teamNumLimit").toString());
        Timestamp signUpStartTime = Timestamp.valueOf(map.get("signupStartTime").toString());
        Timestamp signUpEndTime = Timestamp.valueOf(map.get("signupStartTime").toString());
        BigInteger courseId = new BigInteger(map.get("courseId").toString());
        Round round = seminarManaService.getRoundByCourseIdAndSerial(courseId, roundOrder);
        if(round==null){
            round=new Round(courseId, roundOrder);
            round.setId(seminarManaService.createRound(round,user));
        }
        Seminar seminar = new Seminar(courseId, round.getId(), topic, intro, teamNumLimit, order, visible, signUpStartTime, signUpEndTime);
        return new ResponseEntity(seminarManaService.createSeminar(seminar, user.getId()), HttpStatus.CREATED);
    }

    @GetMapping("/{seminarId}/class/{classId}/report")
    public void downloadAllReport(HttpServletResponse response, @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId){
        BigInteger klassSeminarId = seminarManaService.getIdByKlassIdAndSeminarId(klassId,seminarId);
        List<Attendance> attendanceList =seminarDisplayService.getAttendanceList(klassSeminarId);
        Map<String, String> files = new HashMap<String, String>(1);
        for(Attendance attendance : attendanceList) {
            if (attendance.getReportUrl() != null) {
                files.put(attendance.getReportName(), attendance.getReportUrl());
            }
        }
        String zipName ="report.zip";
        if(files.size() > 0){
            FileUtils.zipDownload(response, files, zipName);
        }
    }

    @GetMapping("/{seminarId}/class/{classId}/ppt")
    public void downloadAllPpt(HttpServletResponse response, @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId){
        BigInteger klassSeminarId = seminarManaService.getIdByKlassIdAndSeminarId(klassId,seminarId);
        List<Attendance> attendanceList =seminarDisplayService.getAttendanceList(klassSeminarId);
        Map<String, String> files = new HashMap<String, String>(1);
        for(Attendance attendance : attendanceList) {
            if (attendance.getPptUrl() != null) {
                files.put(attendance.getPptName(), attendance.getPptUrl());
            }
        }
        String zipName ="ppt.zip";
        if(files.size() > 0){
            FileUtils.zipDownload(response, files, zipName);
        }
    }

    @GetMapping("{seminarId}/class")
    public List<KlassVO> getKlassList(@PathVariable("seminarId")BigInteger seminarId, @RequestAttribute("user")User user) throws NotFoundException, ClassNotFoundException, SeminarNotFoundException {
        List<Klass> klassList =seminarManaService.getKlass(seminarId, user);
        List<KlassVO> klassVOList = new ArrayList<KlassVO>();
        for (Klass klass:klassList){
            klassVOList.add(new KlassVO(klass));
        }
        return klassVOList;
    }

    @RequestMapping(value = "/{seminarId}",method = RequestMethod.PUT)
    public ResponseEntity modifySeminar(@RequestBody Map<String,Object> map,@RequestAttribute("user")User user, @PathVariable("seminarId")BigInteger seminarId ) throws ParseException, CourseNotFoundException, AccessDenyException, NotFoundException, SeminarNotFoundException, ClassNotFoundException {
        String topic = map.get("topic").toString();
        String intro = map.get("intro").toString();
        Integer order =new Integer(map.get("order").toString());
        Integer roundOrder = new Integer(map.get("roundOrder").toString());
        Integer visible = "true".equals(map.get("visible").toString()) ?1:0;
        Integer teamNumLimit = new Integer(map.get("teamNumLimit").toString());
        Timestamp signUpStartTime = Timestamp.valueOf(map.get("signupStartTime").toString());
        Timestamp signUpEndTime = Timestamp.valueOf(map.get("signupStartTime").toString());
        BigInteger courseId = new BigInteger(map.get("id").toString());
        Round round = seminarManaService.getRoundByCourseIdAndSerial(courseId, roundOrder);
        if(round==null){
            round=new Round(courseId, roundOrder);
            round.setId(seminarManaService.createRound(round, user));
        }
        Seminar seminar = new Seminar(courseId, round.getId(), topic, intro, teamNumLimit, order, visible, signUpStartTime, signUpEndTime);
        seminar.setId(seminarId);
        seminarManaService.modifySeminar(seminar, user.getId());
        return new ResponseEntity("成功", HttpStatus.valueOf(204));
    }

    @RequestMapping(value = "/{seminarId}",method = RequestMethod.DELETE)
    public ResponseEntity deleteSeminar(@RequestAttribute("userId")BigInteger userId, @PathVariable("seminarId")BigInteger seminarId ) throws CourseNotFoundException, AccessDenyException,SeminarNotFoundException {
        seminarManaService.deleteSeminar(seminarId, userId);
        return new ResponseEntity("成功", HttpStatus.valueOf(204));
    }

    @RequestMapping(value = "/{seminarId}/class/{classId}",method = RequestMethod.PUT)
    public ResponseEntity modifySeminarKlass(@RequestBody Map<String,Object> map, @RequestAttribute("userId")BigInteger userId, @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId ) throws ParseException, CourseNotFoundException, AccessDenyException, NotFoundException, SeminarNotFoundException {
        Timestamp reportDDL = Timestamp.valueOf(map.get("reportDDL").toString());
        seminarManaService.modifySeminarKlassDDL(seminarId, userId, klassId, reportDDL);
        return new ResponseEntity("成功", HttpStatus.valueOf(204));
    }

    @RequestMapping(value = "/{seminarId}/round",method = RequestMethod.PUT)
    public ResponseEntity setSeminarRound(@RequestBody Map<String,Object> map, @RequestAttribute("userId")BigInteger userId, @PathVariable("seminarId")BigInteger seminarId) throws ParseException, CourseNotFoundException, AccessDenyException, NotFoundException, SeminarNotFoundException {
        BigInteger roundId=new BigInteger(map.get("id").toString());
        seminarManaService.modifySeminarRound(seminarId, userId, roundId);
        return new ResponseEntity("成功", HttpStatus.valueOf(204));
    }

    @RequestMapping(value = "/{seminarId}/status",method = RequestMethod.PUT)
    public ResponseEntity setSeminarStatus(@RequestBody Map<String,Object> map, @RequestAttribute("userId")BigInteger userId, @PathVariable("seminarId")BigInteger seminarId) throws CourseNotFoundException, AccessDenyException, NotFoundException, SeminarNotFoundException, InfoIllegalException {
        BigInteger klassId=new BigInteger(map.get("classId").toString());
        String s = map.get("status").toString();
        Integer status ;
        switch (s){
            case "未开始":status=0;break;
            case "正在进行":status=1;break;
            case "已结束":status=2;break;
            default:throw new InfoIllegalException("无状态");
        }
        seminarManaService.modifySeminarStatus(seminarId, userId, klassId, status);
        return new ResponseEntity("成功", HttpStatus.valueOf(204));
    }

    @PostMapping("/{seminarId}/class/{classId}/question")
    public ResponseEntity askQuestion(@PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("userId")BigInteger userId) throws NotFoundException, AccessDenyException {
        Question question = new Question();
        question.setStudentId(userId);
        return new ResponseEntity(seminarDisplayService.askQuestion(seminarId, klassId, question),HttpStatus.CREATED);
    }

    @GetMapping("/{seminarId}/class/{classId}/question")
    public List<QuestionVO> getQuestionList(@PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user")User user) throws NotFoundException, AccessDenyException, UserNotFoundException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法查看问题");
        }
        List<Question> questionList = seminarManaService.getQuestionList(seminarId,klassId,user.getId());
        List<QuestionVO> questionVOList = new ArrayList<QuestionVO>();
        Team team;
        for (Question question:questionList){
            team = teamService.getTeamById(question.getTeamId());
            questionVOList.add(new QuestionVO(question,team));
        }
        return questionVOList;
    }

    @GetMapping("/{seminarId}/class/{classId}/attendanceStart")
    public List<AttendanceVO> attendanceStart(@RequestBody Map<String,Object> map, @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user")User user) throws NotFoundException, AccessDenyException, UserNotFoundException, InvalidOperationException, CourseNotFoundException, SeminarNotFoundException {
        BigInteger teamId = new BigInteger(map.get("teamId").toString());
        BigInteger klassSeminarId = seminarManaService.getIdByKlassIdAndSeminarId(klassId,seminarId);
        seminarManaService.modifySeminarStatus(seminarId,user.getId(),klassId,1);

        seminarDisplayService.attendanceStart(klassSeminarId, teamId, user);
        List<Attendance> attendanceList = seminarDisplayService.getAttendanceList(klassSeminarId);
        List<AttendanceVO>attendanceVOList = new ArrayList<AttendanceVO>();
        for(Attendance attendance:attendanceList){
            Team team = teamService.getTeamById(attendance.getTeamId());
            attendanceVOList.add(new AttendanceVO(attendance, team));
        }
        return attendanceVOList;
    }

    @GetMapping("/{seminarId}/class/{classId}/nextAttendance")
    public void nextAttendance(@RequestBody Map<String,Object> map, @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user")User user) throws AccessDenyException, NotFoundException, InvalidOperationException {
        BigInteger teamId = new BigInteger(map.get("teamId").toString());
        BigInteger klassSeminarId = seminarManaService.getIdByKlassIdAndSeminarId(klassId,seminarId);
        seminarDisplayService.nextAttendance(klassSeminarId, teamId, user);
    }

    @GetMapping("/{seminarId}/class/{classId}/attendance")
    public AttendanceListVO getAttendanceList(@PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user")User user) throws NotFoundException, AccessDenyException, UserNotFoundException, InvalidOperationException, ClassNotFoundException {
        BigInteger klassSeminarId = seminarManaService.getIdByKlassIdAndSeminarId(klassId,seminarId);
        System.out.println(klassSeminarId);
        List<Attendance> attendanceList = seminarDisplayService.getAttendanceList(klassSeminarId);
        List<AttendanceVO>attendanceVOList = new ArrayList<AttendanceVO>();
        KlassVO klassVO = new KlassVO(courseService.getKlassById(klassId));
        Team userTeam = null;
        if(user.getRole()==0){
            userTeam = teamService.getTeamByKlassIdAndStudentId(klassId, user.getId());
        }
        Integer isEnroll = 0 ;
        for(Attendance attendance:attendanceList){
            if (userTeam!=null&&userTeam.getId().equals(attendance.getTeamId())){
                isEnroll=1;
            }
            Team team = teamService.getTeamById(attendance.getTeamId());
            System.out.println(attendance.getTeamId());
            attendanceVOList.add(new AttendanceVO(attendance, team));
        }
        return new AttendanceListVO(klassVO, attendanceVOList, isEnroll);
    }



    @GetMapping(value = "/{seminarId}/class/{classId}")
    public Seminar1VO getSeminar( @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user") User user) throws SeminarNotFoundException, UserNotFoundException, InvalidOperationException, ClassNotFoundException, NotFoundException {
        Seminar seminar = seminarManaService.getSeminar(seminarId);
        BigInteger kSId = seminarManaService.getIdByKlassIdAndSeminarId(klassId, seminarId);
        Timestamp reportDdl = seminarManaService.getReportDdlById(kSId);
        Integer status = seminarManaService.getStatusById(kSId, user);
        return new Seminar1VO(seminar,status,reportDdl);
    }




}
