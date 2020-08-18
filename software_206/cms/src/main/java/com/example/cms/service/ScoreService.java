package com.example.cms.service;

import com.example.cms.dao.*;
import com.example.cms.entity.*;
import com.example.cms.exception.*;
import com.example.cms.service.utils.ExcelUtils;
import com.example.cms.vo.ExportSeminarScoreVO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2018/12/13
 */
@Service
public class ScoreService {
    @Autowired
    private SeminarDAO seminarDAO;
    @Autowired
    private RoundDAO roundDAO;
    @Autowired
    private KlassSeminarDAO klassSeminarDAO;
    @Autowired
    private KlassStudentDAO klassStudentDAO;
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
    KlassDAO klassDAO;

    public void scoreQuestion(BigInteger questionId, BigDecimal score, User user) throws NotFoundException, CourseNotFoundException, AccessDenyException, InvalidOperationException, SeminarNotFoundException {
        Question question=questionDAO.getQuestion(questionId);
        BigInteger seminarId = klassSeminarDAO.getSeminarIdById(question.getKlassSeminarId());
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        accessVerify(seminar.getCourseId(),user.getId());
        questionDAO.updateScore(questionId, score);
        SeminarScore seminarScore = seminarScoreDAO.getSeminarScore(question.getKlassSeminarId(),question.getTeamId());

        List<Question>questionList = questionDAO.getTeamQuestionList(question);
        if (question!=null){
            for (Question pastQuestion : questionList){
                if (pastQuestion.getScore()!=null&&pastQuestion.getScore().compareTo(score)==1){
                    score=pastQuestion.getScore();
                }
            }
        }
        if (seminarScore!=null){
            seminarScore.setQuestionScore(score);
            seminarScoreDAO.update(seminarScore);
        }
    }

    public List<RoundScore> getAllScore(BigInteger roundId, User user) throws NotFoundException, CourseNotFoundException, SeminarNotFoundException, AccessDenyException {
        Round round = roundDAO.getRoundById(roundId);
        accessVerify(round.getCourseId(),user.getId());
        return roundScoreDAO.getRoundScoreList(roundId);
    }

    public RoundScore getTeamRoundScore(BigInteger roundId, BigInteger teamId, User user) throws NotFoundException, AccessDenyException {
        Round round=roundDAO.getRoundById(roundId);
        if(user.getRole()==0&&!klassStudentDAO.getTeamIdBySC(user.getId(), round.getCourseId()).equals(teamId)){
            throw new AccessDenyException("无法查看其他队伍成绩");
        }
        return roundScoreDAO.getRoundScore(roundId, teamId);
    }

    private void accessVerify(BigInteger courseId, BigInteger userId) throws CourseNotFoundException, AccessDenyException {
        Course course = courseDAO.getCourseById(courseId);
        if(!course.getTeacherId().equals(userId)){
            throw new AccessDenyException("用户权限不足:您不是本课程教师");
        }
    }

    public SeminarScore getTeamSeminarScore(BigInteger seminarId, BigInteger teamId, User user) throws NotFoundException, AccessDenyException, SeminarNotFoundException, UserNotFoundException, ClassNotFoundException {
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        BigInteger kSId = getKlassSeminarId(seminar, teamId);
        if(user.getRole()==0&&!klassStudentDAO.getTeamIdBySC(user.getId(), seminar.getCourseId()).equals(teamId)){
            throw new AccessDenyException("无法查看其他队伍成绩");
        }
        return seminarScoreDAO.getSeminarScore(kSId, teamId);
    }

    public XSSFWorkbook exportExcelInfo(BigInteger courseId) throws Exception{

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        List<Round> roundList=roundDAO.getAllRound(courseId);
        for(int i=0;i<roundList.size();i++)
        {
            ExportSeminarScoreVO exportSeminarScoreVO=new ExportSeminarScoreVO();
            List<RoundScore> roundScoreList=roundScoreDAO.getRoundScoreList(roundList.get(i).getId());
            for(int j=0;j<roundScoreList.size();j++)
            {
                exportSeminarScoreVO.setPresentationScore(roundScoreList.get(j).getPresentationScore());
                exportSeminarScoreVO.setQuestionScore(roundScoreList.get(j).getQuestionScore());
                exportSeminarScoreVO.setReportScore(roundScoreList.get(j).getReportScore());
                exportSeminarScoreVO.setTotalScore(roundScoreList.get(j).getTotalScore());
                exportSeminarScoreVO.setRoundSerial(roundList.get(i).getSerial());
                BigInteger teamId=roundScoreList.get(j).getTeamId();
                Team team=teamDAO.getTeamById(teamId);
                int teamSerial=team.getTeamSerial();
                exportSeminarScoreVO.setTeamSerial(teamSerial);
                Map<String,Object>scoreMap=exportSeminarScoreVO.convertThis2Map();
                list.add(scoreMap);
            }
        }

        List<ExcelBean> excel = new ArrayList<>();
        Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();


        //设置标题栏
        excel.add(new ExcelBean("轮次序号","roundSerial",0));
        excel.add(new ExcelBean("小组序号","teamSerial",0));
        excel.add(new ExcelBean("展示分数","presentationScore",0));
        excel.add(new ExcelBean("提问分数","questionScore",0));
        excel.add(new ExcelBean("报告分数","reportScore", 0));
        excel.add(new ExcelBean("总分","totalScore",0));
        map.put(0,excel);
        String sheetName = "讨论课成绩表";
        //调用ExcelUtil方法
        XSSFWorkbook xssfWorkbook = ExcelUtils.createExcelFile(ExportSeminarScoreVO.class, list, map, sheetName);
        System.out.println(xssfWorkbook);
        return xssfWorkbook;
    }

    public void scorePresentation(BigInteger seminarId, BigInteger klassId, BigInteger teamId, BigDecimal score, User user) throws NotFoundException, CourseNotFoundException, AccessDenyException, SeminarNotFoundException, InvalidOperationException {
        BigInteger kSId = klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId, seminarId);
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        accessVerify(seminar.getCourseId(),user.getId());
        SeminarScore seminarScore = seminarScoreDAO.getSeminarScore(kSId, teamId);
        seminarScore.setPresentationScore(score);
        seminarScoreDAO.update(seminarScore);
    }

    public void scoreReport(BigInteger seminarId, BigInteger klassId, BigInteger attendanceId, BigDecimal score, User user) throws NotFoundException, CourseNotFoundException, AccessDenyException, UserNotFoundException, SeminarNotFoundException, InvalidOperationException {
        Attendance attendance = attendanceDAO.getAttendanceById(attendanceId);
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        accessVerify(seminar.getCourseId(),user.getId());
        SeminarScore seminarScore = seminarScoreDAO.getSeminarScore(attendance.getKlassSeminarId(),attendance.getTeamId());
        seminarScore.setReportScore(score);
        seminarScoreDAO.update(seminarScore);
        reckonRoundScore(seminar.getRoundId(), klassId, attendance.getTeamId());
    }

    public void scoreSeminar(BigInteger seminarId, SeminarScore seminarScore, User user) throws SeminarNotFoundException, CourseNotFoundException, AccessDenyException, ClassNotFoundException, InvalidOperationException, NotFoundException {
        Seminar seminar = seminarDAO.getSeminarById(seminarId);
        accessVerify(seminar.getCourseId(),user.getId());
        BigInteger kSId = getKlassSeminarId(seminar, seminarScore.getTeamId());
        seminarScore.setKlassSeminarId(kSId);
        seminarScoreDAO.update(seminarScore);
        reckonRoundScore(seminar.getRoundId(),klassSeminarDAO.getKlassIdById(kSId),seminarScore.getTeamId());
    }

    private void reckonRoundScore(BigInteger roundId, BigInteger klassId, BigInteger teamId) throws NotFoundException, CourseNotFoundException, InvalidOperationException {
        Round round = roundDAO.getRoundById(roundId);
        Course course = courseDAO.getCourseById(round.getCourseId());
        List<SeminarScore> seminarScoreList = new ArrayList<SeminarScore>();
        List<Seminar>seminarList = seminarDAO.getAllSeminar(roundId);
        BigInteger kSId;
        for (Seminar seminar:seminarList){
            kSId = klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId, seminar.getId());
            SeminarScore seminarScore = seminarScoreDAO.getSeminarScore(kSId, teamId);
            seminarScoreList.add(seminarScore);
        }
        Integer pMethod=round.getPresentationScoreMethod();
        Integer qMethod=round.getQuestionScoreMethod();
        Integer rMethod=round.getReportScoreMethod();
        BigDecimal zero = new BigDecimal("0");
        BigDecimal pSum = zero;BigDecimal pMax = zero;
        BigDecimal rSum = zero;BigDecimal rMax = zero;
        BigDecimal qSum = zero;BigDecimal qMax = zero;
        for (SeminarScore seminarScore:seminarScoreList){
            BigDecimal pScore = seminarScore.getPresentationScore()!=null?seminarScore.getPresentationScore():zero;
            BigDecimal rScore = seminarScore.getReportScore()!=null?seminarScore.getReportScore():zero;
            BigDecimal qScore = seminarScore.getQuestionScore()!=null?seminarScore.getQuestionScore():zero;
            pSum.add(pScore);
            if (pScore.compareTo(pMax)==1){pMax=seminarScore.getPresentationScore();}
            rSum.add(rScore);
            if (rScore.compareTo(rMax)==1){rMax=seminarScore.getReportScore();}
            qSum.add(qScore);
            if (qScore.compareTo(qMax)==1){qMax=seminarScore.getQuestionScore();}
        }
        RoundScore roundScore = new RoundScore(roundId, teamId);
        KlassRound klassRound = klassRoundDAO.getByKlassIdAndRoundId(klassId,roundId);
        BigDecimal divisor = new BigDecimal(klassRound.getEnrollNumber().toString());
        if(pMethod==0){
            roundScore.setPresentationScore(pSum.divide(divisor));
        }
        else {
            roundScore.setPresentationScore(pMax);
        }
        if(rMethod==0){
            roundScore.setReportScore(rSum.divide(divisor));
        }
        else {
            roundScore.setReportScore(rMax);
        }
        if(qMethod==0){
            roundScore.setQuestionScore(qSum.divide(divisor));
        }
        else {
            roundScore.setQuestionScore(qMax);
        }
        BigDecimal pPercentage=new BigDecimal(course.getPresentationPercentage());
        BigDecimal rPercentage=new BigDecimal(course.getReportPercentage());
        BigDecimal qPercentage=new BigDecimal(course.getQuestionPercentage());
        BigDecimal total = new BigDecimal("0");
        total.add(roundScore.getPresentationScore().multiply(pPercentage));
        total.add(roundScore.getReportScore().multiply(rPercentage));
        total.add(roundScore.getQuestionScore().multiply(qPercentage));
        roundScore.setTotalScore(total);
        roundScoreDAO.update(roundScore);
    }

    public BigInteger getKlassSeminarId(Seminar seminar, BigInteger team) throws ClassNotFoundException {
        List<Klass> klassList = klassDAO.getKlassByCourseId(seminar.getCourseId());
        BigInteger klassId = null;
        for (Klass klass:klassList){
            List<BigInteger> teamIdList = klassDAO.getTeamIdByKlassId(klass.getId());
            for (BigInteger teamId:teamIdList){
                if (teamId.equals(team)){
                    klassId = klass.getId();
                    break;
                }
            }
            if (klassId!=null){
                break;
            }
        }
        return klassSeminarDAO.getIdByKlassIdAndSeminarId(klassId, seminar.getId());
    }

    public List<SeminarScore>getAllSeminarScore(BigInteger roundId, BigInteger teamId, User user) throws AccessDenyException, NotFoundException {
        Round round=roundDAO.getRoundById(roundId);
        if(user.getRole()==0&&!klassStudentDAO.getTeamIdBySC(user.getId(), round.getCourseId()).equals(teamId)){
            throw new AccessDenyException("无法查看其他队伍成绩");
        }
        List<BigInteger>seminarList = seminarDAO.getAllSeminarId(roundId);
        List<BigInteger>kSIdList = seminarScoreDAO.getKlassSeminarList(teamId);
        List<BigInteger>kSIdList1 = new ArrayList<BigInteger>();
        for (BigInteger kSId : kSIdList){
            BigInteger seminarId = klassSeminarDAO.getSeminarIdById(kSId);
            if(seminarList.contains(seminarId)){
                kSIdList1.add(kSId);
            }
        }
        List<SeminarScore>seminarScoreList = new ArrayList<SeminarScore>();
        for (BigInteger kSId:kSIdList1){
            SeminarScore seminarScore = seminarScoreDAO.getSeminarScore(kSId, teamId);
            seminarScoreList.add(seminarScore);
        }
        return  seminarScoreList;
    }
}















