package com.example.cms.controller;
import com.example.cms.entity.*;
import com.example.cms.exception.*;
import com.example.cms.service.ScoreService;
import com.example.cms.service.SeminarDisplayService;
import com.example.cms.service.SeminarManaService;
import com.example.cms.service.TeamService;
import com.example.cms.vo.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author xzy
 * @date 2018-12-25
 */
@RestController
public class ScoreController {
    @Autowired
    SeminarDisplayService seminarDisplayService;
    @Autowired
    SeminarManaService seminarManaService;
    @Autowired
    TeamService teamService;
    @Autowired
    ScoreService scoreService;

    @RequestMapping(value = "/seminar/{seminarId}/class/{classId}/reportScore",method = RequestMethod.PUT)
    public ResponseEntity scoreReport(@RequestBody List<Map<String, Object>>mapList, @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user")User user) throws AccessDenyException, CourseNotFoundException, NotFoundException, UserNotFoundException, SeminarNotFoundException, InvalidOperationException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法评分");
        }
        for (Map<String, Object> map:mapList){
            BigInteger attendanceId = new BigInteger(map.get("attendanceId").toString());
            BigDecimal score = new BigDecimal(map.get("reportScore").toString());
            scoreService.scoreReport(seminarId, klassId, attendanceId, score, user);
        }
        return new ResponseEntity(HttpStatus.CREATED) ;
    }

    @RequestMapping(value = "/seminar/{seminarId}/class/{classId}/scorePresentation",method = RequestMethod.PUT)
    public ResponseEntity scorePresentation(@RequestBody Map<String,Object> map, @PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user")User user) throws AccessDenyException, CourseNotFoundException, NotFoundException, SeminarNotFoundException, InvalidOperationException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法评分");
        }
        BigInteger teamId = new BigInteger(map.get("teamId").toString());
        BigDecimal score=new BigDecimal(map.get("score").toString());
        scoreService.scorePresentation(seminarId, klassId, teamId, score, user);
        return new ResponseEntity(HttpStatus.CREATED) ;
    }

    @GetMapping(value = "/seminar/{seminarId}/class/{classId}/reportScore")
    public List<ReportScoreVO> getReportScore(@PathVariable("seminarId")BigInteger seminarId, @PathVariable("classId")BigInteger klassId, @RequestAttribute("user")User user) throws AccessDenyException, NotFoundException, UserNotFoundException, SeminarNotFoundException, ClassNotFoundException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法查看所有组评分");
        }
        BigInteger kSId = seminarManaService.getIdByKlassIdAndSeminarId(klassId, seminarId);
        SeminarScore seminarScore;
        List<Attendance>attendanceList = seminarDisplayService.getAttendanceList(kSId);
        List<ReportScoreVO> reportScoreVOList = new ArrayList<ReportScoreVO>();
        for (Attendance attendance : attendanceList){
            seminarScore = scoreService.getTeamSeminarScore(seminarId, attendance.getTeamId(),user);
            reportScoreVOList.add(new ReportScoreVO(attendance,seminarScore.getReportScore()));
        }
        return reportScoreVOList;
    }

    @RequestMapping(value = "/question/{questionId}",method = RequestMethod.PUT)
    public ResponseEntity scoreQuestion(@RequestBody Map<String,Object> map, @PathVariable("questionId")BigInteger questionId, @RequestAttribute("user")User user) throws AccessDenyException, CourseNotFoundException, SeminarNotFoundException, NotFoundException, UserNotFoundException, InvalidOperationException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法评分");
        }
        BigDecimal score=new BigDecimal(map.get("score").toString());
        scoreService.scoreQuestion(questionId, score, user);
        return new ResponseEntity(HttpStatus.CREATED) ;
    }

    @GetMapping("/round/{roundId}/roundScore")
    public List<ScoreVO> getAllScore(@PathVariable("roundId")BigInteger roundId,@RequestAttribute("user") User user) throws AccessDenyException, CourseNotFoundException, SeminarNotFoundException, NotFoundException, UserNotFoundException, ClassNotFoundException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法查看所有人成绩");
        }
        List<RoundScore> roundScoreList = scoreService.getAllScore(roundId, user);
        List<ScoreVO>scoreVOList = new ArrayList<ScoreVO>();
        for (RoundScore roundScore:roundScoreList){
            Team team = teamService.getTeamById(roundScore.getTeamId());
            TeamVO teamVO = new TeamVO(team);
            Integer klassSerial = seminarManaService.getKlassSerial(roundId,team.getId());
            scoreVOList.add(new ScoreVO(teamVO, roundScore, klassSerial));
        }
        return  scoreVOList;
    }

    @GetMapping("round/{roundId}/team/{teamId}/roundScore")
    public ScoreVO getTeamRoundScore(@PathVariable("roundId")BigInteger roundId, @PathVariable("teamId")BigInteger teamId, @RequestAttribute("user") User user) throws NotFoundException, AccessDenyException, UserNotFoundException, ClassNotFoundException {
        RoundScore roundScore = scoreService.getTeamRoundScore(roundId, teamId, user);
        Team team = teamService.getTeamById(roundScore.getTeamId());
        TeamVO teamVO = new TeamVO(team);
        Integer klassSerial = seminarManaService.getKlassSerial(roundId,teamId);
        return new ScoreVO(teamVO,roundScore, klassSerial);
    }

    @GetMapping("round/{roundId}/team/{teamId}/seminarScore")
    public List<SeminarScoreVO> getAllSeminarId(@PathVariable("roundId")BigInteger roundId, @PathVariable("teamId")BigInteger teamId, @RequestAttribute("user") User user) throws NotFoundException, AccessDenyException, UserNotFoundException, ClassNotFoundException, SeminarNotFoundException {
        List<SeminarScore> seminarScoreList = scoreService.getAllSeminarScore(roundId, teamId, user);
        Team team = teamService.getTeamById(teamId);
        List<SeminarScoreVO>seminarScoreVOList = new ArrayList<SeminarScoreVO>();
        for (SeminarScore seminarScore:seminarScoreList){
            Seminar seminar = seminarManaService.getSeminarByKSId(seminarScore.getKlassSeminarId());
            SeminarScoreVO seminarScoreVO = new SeminarScoreVO(new SeminarVO(seminar),seminarScore);
            seminarScoreVOList.add(seminarScoreVO);
        }
        return seminarScoreVOList;
    }

    @GetMapping("/seminar/{seminarId}/team/{teamId}/seminarScore")
    public ScoreVO getTeamSeminarScore(@PathVariable("seminarId")BigInteger seminarId, @PathVariable("teamId")BigInteger teamId, @RequestAttribute("user") User user) throws SeminarNotFoundException, UserNotFoundException, AccessDenyException, NotFoundException, ClassNotFoundException {
        SeminarScore seminarScore = scoreService.getTeamSeminarScore(seminarId, teamId, user);
        TeamVO teamVO = new TeamVO(teamService.getTeamById(teamId));
        Seminar seminar = seminarManaService.getSeminar(seminarId);
        BigInteger kSId = scoreService.getKlassSeminarId(seminar, teamId);
        Integer klassSerial = seminarManaService.getKlassSerialByKSId(kSId);
        return new ScoreVO(teamVO,seminarScore, klassSerial);
    }

    @GetMapping("seminar/{courseId}/export")
    public  void export(@PathVariable("courseId")String courseId, HttpServletResponse response) throws Exception {

        BigInteger id=new BigInteger(courseId);
        if(id!=null){
            response.reset(); //清除buffer缓存
            // 指定下载的文件名，浏览器都会使用本地编码，即GBK，浏览器收到这个文件名后，用ISO-8859-1来解码，然后用GBK来显示
            // 所以我们用GBK解码，ISO-8859-1来编码，在浏览器那边会反过来执行。
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户表.xlsx".getBytes("UTF-8"),"ISO-8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            XSSFWorkbook workbook=null;
            //导出Excel对象
            workbook = scoreService.exportExcelInfo(id);
            OutputStream output;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @RequestMapping(value = "/seminarScore",method = RequestMethod.PUT)
    public ResponseEntity scoreSeminar(@RequestBody Map<String, Object>map,  @RequestAttribute("user")User user) throws AccessDenyException, CourseNotFoundException, NotFoundException, UserNotFoundException, SeminarNotFoundException, InvalidOperationException, ClassNotFoundException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法评分");
        }
        BigInteger seminarId = new BigInteger(map.get("seminarId").toString());
        BigInteger teamId = new BigInteger(map.get("teamId").toString());
        BigDecimal preScore = new BigDecimal(map.get("preScore").toString());
        BigDecimal queScore = new BigDecimal(map.get("queScore").toString());
        BigDecimal repScore = new BigDecimal(map.get("repScore").toString());
        SeminarScore seminarScore = new SeminarScore();
        seminarScore.setTeamId(teamId);
        seminarScore.setPresentationScore(preScore);
        seminarScore.setReportScore(repScore);
        seminarScore.setQuestionScore(queScore);
        scoreService.scoreSeminar(seminarId, seminarScore, user);
        return new ResponseEntity(HttpStatus.CREATED) ;
    }
}
