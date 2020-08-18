package com.example.cms.service;

import com.example.cms.dao.*;
import com.example.cms.entity.*;
import com.example.cms.exception.*;
import com.example.cms.mapper.KlassRoundMapper;
import com.example.cms.mapper.KlassTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shaoziwei
 * @date 2018/11/30
 */
@Service
public class SeminarManaService {
    @Autowired
    private SeminarDAO seminarDAO;
    @Autowired
    private RoundDAO roundDAO;
    @Autowired
    private KlassSeminarDAO klassSeminarDAO;
    @Autowired
    private KlassDAO klassDAO;
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    AttendanceDAO attendanceDAO;
    @Autowired
    KlassRoundDAO klassRoundDAO;
    @Autowired
    QuestionDAO questionDAO;
    @Autowired
    SeminarScoreDAO seminarScoreDAO;
    @Autowired
    RoundScoreDAO roundScoreDAO;
    @Autowired
    TeamDAO teamDAO;
    @Autowired
    TeamService teamService;

    public BigInteger createSeminar(Seminar seminar, BigInteger userId) throws CourseNotFoundException, AccessDenyException, ClassNotFoundException {
        Course course = courseDAO.getCourseById(seminar.getCourseId());
        if(!course.getTeacherId().equals(userId)){
            throw new AccessDenyException("用户权限不足");
        }
        seminar.setId(seminarDAO.createSeminar(seminar));
        List<Klass>klassList = klassDAO.getKlassByCourseId(seminar.getCourseId());
        for (Klass klass:klassList){
            klassSeminarDAO.createKlassSeminar(klass.getId(), seminar.getId(),0);
        }
        return seminar.getId();
    }

    public void modifySeminar(Seminar seminarNew, BigInteger userId) throws CourseNotFoundException, AccessDenyException, SeminarNotFoundException {
        accessVerify(seminarNew.getCourseId(), userId);
        seminarDAO.updateSeminar(seminarNew);
    }

    public void deleteSeminar(BigInteger seminarId, BigInteger userId) throws CourseNotFoundException, AccessDenyException, SeminarNotFoundException {
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        accessVerify(seminar.getCourseId(), userId);
        List<BigInteger> kSIds = klassSeminarDAO.getIdBySeminarId(seminarId);
        for (BigInteger kSId : kSIds) {
            attendanceDAO.deleteByKSId(kSId);
        }
        for (BigInteger id : kSIds) {
            klassSeminarDAO.deleteById(id);
        }
        seminarDAO.deleteSeminar(seminarId);

    }

    public void modifySeminarKlassDDL(BigInteger seminarId, BigInteger userId, BigInteger klassId, Timestamp reportDDL) throws CourseNotFoundException, AccessDenyException, SeminarNotFoundException {
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        accessVerify(seminar.getCourseId(), userId);
        BigInteger kSId = klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId, seminarId);
        klassSeminarDAO.updateDDLById(kSId, reportDDL);

    }

    public void modifySeminarRound(BigInteger seminarId, BigInteger userId, BigInteger roundId) throws CourseNotFoundException, AccessDenyException, SeminarNotFoundException {
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        accessVerify(seminar.getCourseId(), userId);
        seminar.setRoundId(roundId);
        seminarDAO.updateSeminar(seminar);
        List<BigInteger> klassIds = klassSeminarDAO.getKlassIdBySeminarId(seminarId);
        for (BigInteger klassId : klassIds) {
            klassRoundDAO.createKlassRound(klassId, roundId,6);
        }
    }

    public Seminar getSeminar(BigInteger seminarId) throws SeminarNotFoundException {
        return seminarDAO.getSeminarById(seminarId);
    }

    public Seminar getSeminarByKSId(BigInteger kSIId) throws SeminarNotFoundException {
        BigInteger seminarId = klassSeminarDAO.getSeminarIdById(kSIId);
        return seminarDAO.getSeminarById(seminarId);
    }


    public void modifySeminarStatus(BigInteger seminarId, BigInteger userId, BigInteger klassId,Integer status) throws CourseNotFoundException, AccessDenyException, SeminarNotFoundException {
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        accessVerify(seminar.getCourseId(), userId);
        BigInteger kSId = klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId,seminarId);
        klassSeminarDAO.updateStatusById(kSId, status);
    }

    public Round getRoundByCourseIdAndSerial(BigInteger courseId, Integer serial) throws NotFoundException {
        return roundDAO.getRoundByCourseIdAndSerial(courseId, serial);
    }

    public BigInteger createRound(Round round, User user) throws ClassNotFoundException, AccessDenyException, CourseNotFoundException, SeminarNotFoundException {
        if (user.getRole()==0){
            throw new AccessDenyException("学生无法创建轮次");
        }
        accessVerify(round.getCourseId(),user.getId());
        BigInteger roundId = roundDAO.createRound(round);
        List<Klass>klassList = klassDAO.getKlassByCourseId(round.getCourseId());
        List<BigInteger>teamIdList;
        RoundScore roundScore = new RoundScore();
        for (Klass klass:klassList){
            klassRoundDAO.createKlassRound(klass.getId(), roundId, 1);
            teamIdList=klassDAO.getTeamIdByKlassId(klass.getId());
            for (BigInteger teamId:teamIdList){
                roundScore.setRoundId(roundId);
                roundScore.setTeamId(teamId);
                roundScoreDAO.creat(roundScore);
            }
        }

        return roundId;
    }

    public BigInteger getIdByKlassIdAndSeminarId(BigInteger klassId, BigInteger seminarId){
        return klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId,seminarId);
    }

    public List<Klass> getKlass(BigInteger seminarId, User user) throws ClassNotFoundException, SeminarNotFoundException {
        List<Klass> klassList = new ArrayList<Klass>();
        if (user.getRole()==1) {
            List<BigInteger> klassIds = klassSeminarDAO.getKlassIdBySeminarId(seminarId);
            for (BigInteger klassId : klassIds) {
                klassList.add(klassDAO.getKlassById(klassId));
            }
        }
        else {
            Seminar seminar = seminarDAO.getSeminarById(seminarId);
            Klass klass = klassDAO.getKlassById(klassDAO.getKlassIdByStuIdAndCourseId(user.getId(), seminar.getCourseId()));
            klassList.add(klass);
        }
        return klassList;
    }

    public Integer getKlassSerial(BigInteger roundId, BigInteger team) throws ClassNotFoundException, NotFoundException {
        BigInteger courseId = roundDAO.getRoundById(roundId).getCourseId();
        List<Klass> klassList = klassDAO.getKlassByCourseId(courseId);
        BigInteger klassId = null;
        for (Klass klass : klassList) {
            List<BigInteger> teamIdList = klassDAO.getTeamIdByKlassId(klass.getId());
            for (BigInteger teamId : teamIdList) {
                if (teamId.equals(team)) {
                    klassId = klass.getId();
                    break;
                }
            }
            if (klassId != null) {
                break;
            }
        }
        return klassDAO.getKlassById(klassId).getKlassSerial();
    }

    public Integer getKlassSerialByKSId(BigInteger kSId) throws ClassNotFoundException {
        BigInteger klassId = klassSeminarDAO.getKlassIdById(kSId);
        return klassDAO.getKlassById(klassId).getKlassSerial();
    }

    public Integer getKlassSerial(BigInteger klassId) throws ClassNotFoundException {
        return klassDAO.getKlassById(klassId).getKlassSerial();
    }

    public List<Question> getQuestionList(BigInteger seminarId, BigInteger klassId, BigInteger userId) throws NotFoundException {
        BigInteger kSId = klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId,seminarId);
        return questionDAO.getQuestionList(kSId);
    }

    public Round getRound(BigInteger roundId, User user) throws NotFoundException, CourseNotFoundException, SeminarNotFoundException, AccessDenyException {
        Round round=roundDAO.getRoundById(roundId);
        accessVerify(round.getCourseId(), user.getId());
        return round;
    }

    public List<KlassRound> getByRoundId(BigInteger roundId) throws NotFoundException {
        return klassRoundDAO.getByRoundId(roundId);
    }

    public void modifyRound(Round round, List<KlassRound> klassRoundList, User user) throws NotFoundException, CourseNotFoundException, SeminarNotFoundException, AccessDenyException {
        BigInteger courseId = roundDAO.getRoundById(round.getId()).getCourseId();
        accessVerify(courseId,user.getId());
        roundDAO.update(round);
        for (KlassRound klassRound:klassRoundList){
            klassRoundDAO.update(klassRound);
        }
    }

    private void accessVerify(BigInteger courseId, BigInteger userId) throws SeminarNotFoundException, CourseNotFoundException, AccessDenyException {
        Course course = courseDAO.getCourseById(courseId);
        if(!course.getTeacherId().equals(userId)){
            throw new AccessDenyException("用户权限不足:您不是本课程教师");
        }
    }


    public List<Seminar> getAllSeminar(BigInteger roundId) throws NotFoundException {
        return seminarDAO.getAllSeminar(roundId);
    }


    public Timestamp getReportDdlById(BigInteger id){
        return klassSeminarDAO.getReportDdlById(id);
    }

    public Integer getStatusById(BigInteger id, User user) throws UserNotFoundException, ClassNotFoundException, InvalidOperationException, NotFoundException {
        Integer kSStatus = klassSeminarDAO.getStatusById(id);
        List<Attendance> attendanceList = attendanceDAO.getAttendanceList(id);
        BigInteger klassId = klassSeminarDAO.getKlassIdById(id);
        Integer isEnroll = 0 ;
        if(user.getRole()==1){
            isEnroll = 0;
        }else {
            Team userTeam = teamService.getTeamByKlassIdAndStudentId(klassId, user.getId());
            for (Attendance attendance : attendanceList) {
                if (userTeam != null && userTeam.getId().equals(attendance.getTeamId())) {
                    isEnroll = 1;
                }
            }
        }
        if (kSStatus == 0){
            if (isEnroll == 0){
                return 1;
            }
            else{
                return 4;
            }
        }
        if (kSStatus == 1){
            if (isEnroll == 0){
                return 2;
            }
            else {
                return 3;
            }
        }
        Integer a=2;
        if (kSStatus.equals(a)){
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if (isEnroll == 1){
                if (klassSeminarDAO.getReportDdlById(id).after(now)){
                    return 5;
                }
                else{
                    return 6;
                }
            }
            else {
                if (klassSeminarDAO.getReportDdlById(id).before(now)){
                    return 7;
                }
            }
        }
        return 0;
    }
}
