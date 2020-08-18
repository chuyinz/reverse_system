package com.example.cms.service;

import com.example.cms.dao.*;
import com.example.cms.entity.*;
import com.example.cms.exception.AccessDenyException;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.SeminarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.sql.Timestamp;

/**
 * @author xzy
 * @date 2018-12-25
 */
@Service
public class SeminarDisplayService {
    @Autowired
    AttendanceDAO attendanceDAO;
    @Autowired
    KlassStudentDAO klassStudentDAO;
    @Autowired
    KlassSeminarDAO klassSeminarDAO;
    @Autowired
    QuestionDAO questionDAO;
    @Autowired
    SeminarDAO seminarDAO;
    @Autowired
    SeminarScoreDAO seminarScoreDAO;
    @Autowired
    RoundScoreDAO roundScoreDAO;
    @Autowired
    RoundDAO roundDAO;
    @Autowired
    KlassRoundDAO klassRoundDAO;

    public BigInteger enrollPresentation(BigInteger seminarId, BigInteger klassId, Team team, Integer teamOrder, User user) throws AccessDenyException, SeminarNotFoundException, InvalidOperationException, NotFoundException {
        if (user.getRole()==1){
            throw new AccessDenyException("教师无法报名");
        }
        BigInteger kSId = klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId, seminarId);
        List<Attendance>attendanceList = getAttendanceList(kSId);
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (seminar.getEnrollStartTime().after(now)||seminar.getEnrollEndTime().before(now)){
            throw new InvalidOperationException("不在报名时间段");
        }
        if(teamOrder>seminar.getMaxTeam()){
            throw new InvalidOperationException("超出顺序上限");
        }

        for (Attendance attendance: attendanceList){
            if (attendance.getTeamOrder()==teamOrder){
                throw new InvalidOperationException("该顺序被占用");
            }
        }
        Round round = roundDAO.getRoundById(seminar.getRoundId());
        List<BigInteger>kSIdList = klassSeminarDAO.getIdBySeminarId(seminarId);
        if (kSIdList.size()>=klassRoundDAO.getByKlassIdAndRoundId(klassId, round.getId()).getEnrollNumber()){
            throw new InvalidOperationException("您的报名已达上限");
        }
        SeminarScore seminarScore = new SeminarScore(kSId, team.getId());
        seminarScoreDAO.creat(seminarScore);

        Attendance attendance = new Attendance(kSId, team.getId(),teamOrder,0);
        return attendanceDAO.create(attendance);
    }


    public void modifyEnrollAttendance(BigInteger userId, Integer teamOrder, Attendance attendance) throws AccessDenyException {
        BigInteger klassId = klassSeminarDAO.getKlassIdById(attendance.getKlassSeminarId());
        try {
            klassStudentDAO.getTeamIdBySK(userId, klassId);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AccessDenyException("只能修改自己的报名");
        }
        BigInteger teamId = klassStudentDAO.getTeamIdBySK(userId, klassId);
        if (teamId==null||!teamId.equals(attendance.getTeamId())){
            throw new AccessDenyException("只能修改自己的报名");
        }
        attendanceDAO.setTeamOrder(teamOrder,attendance);
    }

    public Attendance getAttendanceById(BigInteger attendanceId) throws NotFoundException {
        return attendanceDAO.getAttendanceById(attendanceId);
    }

    public void cancelAttendance(BigInteger userId, Attendance attendance) throws AccessDenyException, SeminarNotFoundException, InvalidOperationException {
        BigInteger klassId = klassSeminarDAO.getKlassIdById(attendance.getKlassSeminarId());
        Seminar seminar = seminarDAO.getSeminarById(klassSeminarDAO.getSeminarIdById(attendance.getKlassSeminarId()));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (seminar.getEnrollStartTime().after(now)||seminar.getEnrollEndTime().before(now)){
            throw new InvalidOperationException("不在退出报名时间段");
        }

        if (!attendance.getTeamId().equals(klassStudentDAO.getTeamIdBySK(userId, klassId))) {
            throw new AccessDenyException("只能取消自己的报名");
        }
        BigInteger teamId = klassStudentDAO.getTeamIdBySK(userId, klassId);
        if (teamId==null||!teamId.equals(attendance.getTeamId())){
            throw new AccessDenyException("只能取消自己的报名");
        }
        SeminarScore seminarScore = new SeminarScore(attendance.getKlassSeminarId(),attendance.getTeamId());
        seminarScoreDAO.delete(seminarScore);

        attendanceDAO.delete(attendance);
    }


    public void postReport(BigInteger attendanceId, String url, String fileName){
        attendanceDAO.updateReportById(attendanceId, url, fileName);
    }

    public void postPpt(BigInteger attendanceId, String url, String fileName){
        attendanceDAO.updatePptById(attendanceId, url, fileName);
    }

    public List<Attendance> getAttendanceList(BigInteger klassSeminarId){
        return attendanceDAO.getAttendanceList(klassSeminarId);
    }

    public BigInteger askQuestion(BigInteger seminarId,BigInteger klassId, Question question) throws NotFoundException, AccessDenyException {
        BigInteger kSId = klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId,seminarId);
        Attendance attendance = attendanceDAO.getPresentAttendance(kSId);
        if (attendance==null){
            throw new NotFoundException("无人展示");
        }
        question.setKlassSeminarId(kSId);
        question.setAttendanceId(attendance.getId());
        BigInteger teamId = klassStudentDAO.getTeamIdBySK(question.getStudentId(), klassId);
        if (teamId.equals(attendance.getTeamId())){
            throw new AccessDenyException("不能提问本组");
        }
        question.setTeamId(teamId);
        question.setIsSelected(0);
        return questionDAO.create(question);
    }


    public void  attendanceStart(BigInteger klassSeminarId,BigInteger teamId, User user) throws AccessDenyException, InvalidOperationException, NotFoundException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法开始讨论课");
        }
        Attendance attendance = attendanceDAO.getPresentAttendance(klassSeminarId);
        if (attendance!=null){
            throw new InvalidOperationException("讨论课已开始");
        }
        attendance=attendanceDAO.getTeamAttendance(klassSeminarId, teamId);
        attendance.setIsPresent(1);
        attendanceDAO.update(attendance);
    }

    public void nextAttendance(BigInteger klassSeminarId,BigInteger teamId, User user) throws AccessDenyException, InvalidOperationException, NotFoundException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法选择下一组");
        }
        Attendance attendance = attendanceDAO.getPresentAttendance(klassSeminarId);
        if (attendance==null){
            throw new InvalidOperationException("讨论课未开始");
        }
        attendance.setIsPresent(2);
        attendanceDAO.update(attendance);
        attendance=attendanceDAO.getTeamAttendance(klassSeminarId, teamId);
        attendance.setIsPresent(1);
        attendanceDAO.update(attendance);
    }

    public void timeOut(BigInteger attendanceId) throws NotFoundException, InvalidOperationException {
        Attendance attendance = attendanceDAO.getAttendanceById(attendanceId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (klassSeminarDAO.getReportDdlById(attendance.getKlassSeminarId()).before(now)){
            throw new InvalidOperationException("已过截止时间");
        }
    }

}
