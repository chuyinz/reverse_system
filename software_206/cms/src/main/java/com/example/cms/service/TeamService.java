package com.example.cms.service;

import com.example.cms.dao.*;
import com.example.cms.entity.*;
import com.example.cms.exception.CourseNotFoundException;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.mapper.KlassTeamMapper;
import com.example.cms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shaoziwei
 * @date 2018/11/30
 */

@Service
public class TeamService {
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private KlassDAO klassDAO;


    public BigInteger createTeam(BigInteger klassId, BigInteger courseId, String teamName,
                                 User leader, List<User> members) throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, NotFoundException,InvalidOperationException {
        Integer klassSerial = klassDAO.getKlassSerial(klassId, courseId);
        Integer teamSerial = teamDAO.getTeamSerialByKlassId(klassId) + 1;

        if(teamDAO.getIdBySubIdAndStatus(courseId,1)!=null)
        {
            if (teamDAO.getTeamIdByStudentId(leader.getId()) == null)
            {
                return post(klassId,courseId,teamName,leader,members,teamSerial,klassSerial);
            }
            else
            {
                int a=getStudentIfTeam(leader,klassId);
                if(a==0)
                {
                    return post(klassId,courseId,teamName,leader,members,teamSerial,klassSerial);
                }
            }
        }
        throw new InvalidOperationException("创建失败");
    }


    public int getStudentIfTeam(User user,BigInteger klassId)
    {
        BigInteger teamId=teamDAO.getTeamIdByStudentId(user.getId());
        List<BigInteger>klassIds=teamDAO.getKlassIds(teamId);
        int a=0;
        for(BigInteger klassId1:klassIds)
        {
            if(klassId.equals(klassId1)) {
                a++;
            }
        }
        return a;
    }

    public BigInteger post(BigInteger klassId, BigInteger courseId, String teamName,
                           User leader, List<User> members,Integer teamSerial,Integer klassSerial) throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, NotFoundException ,InvalidOperationException{
        int b=0;
        for(User member:members)
        {
            if(teamDAO.getTeamIdByStudentId(member.getId())!=null)
            {
                int a=getStudentIfTeam(member,klassId);
                if(a!=0) {
                    b++;
                }
            }
        }
        if(b==0) {
            teamDAO.createTeam(klassId, courseId, leader.getId(), teamName, teamSerial, klassSerial);
            BigInteger teamId = teamDAO.getTeamId(klassSerial, teamSerial);
            teamDAO.createKlassTeam(klassId, teamId);
            for (User member : members) {
                teamDAO.createTeamStudent(teamId, member.getId());
                teamDAO.createKlassStudent(klassId,member.getId(),teamId);
            }
            teamDAO.createTeamStudent(teamId, leader.getId());
            teamDAO.createKlassStudent(klassId,leader.getId(),teamId);
            boolean valid = isValid(teamId, klassId, courseId, teamName, leader, members);
            if (!valid) {
                teamDAO.changeTeamStatus(teamId, 0);
            }
            return teamId;
        }
        else
        {
            throw new InvalidOperationException("创建失败");
        }
    }


    public boolean isValid(BigInteger teamId,BigInteger klassId, BigInteger courseId, String teamName,
                           User leader, List<User> members) throws ClassNotFoundException, CourseNotFoundException, NotFoundException {
        Team team=teamDAO.getTeamById(teamId);
        Team1VO team1VO=new Team1VO(teamId,teamName,studentDAO.getById(team.getLeaderId()),team.getTeamSerial(),
                team.getKlassSerial(),klassDAO.getKlassById(klassId),courseDAO.getCourseById(courseId),
                members,team.getStatus());
        List<TeamStrategy> teamStrategyList=teamDAO.getStrategyByCourseId(courseId);
        String str1="MemberLimitStrategy";
        String str2="CourseMemberLimitStrategy";
        String str3="ConflictCourseStrategy";
        String str4="TeamOrStrategy";
        String str5="TeamAndStrategy";
        for(TeamStrategy teamStrategy:teamStrategyList)
        {
            if(teamStrategy.getStrategyName().equals(str1))
            {
                boolean valid=teamDAO.isValidByMemberLimit(team1VO,teamStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
            else if(teamStrategy.getStrategyName().equals(str2))
            {
                boolean valid=teamDAO.isValidByCourseMemberLimit(team1VO,teamStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
            else if(teamStrategy.getStrategyName().equals(str3))
            {
                boolean valid=teamDAO.isValidByConflictCourse(team1VO,teamStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
            else if(teamStrategy.getStrategyName().equals(str4))
            {
                boolean valid=teamDAO.isValidByOrStrategy(team1VO,teamStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
            else if(teamStrategy.getStrategyName().equals(str5))
            {
                boolean valid=teamDAO.isValidByAndStrategy(team1VO,teamStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     *获得队伍共享/合法申请信息列表
     */
    public RequestVO getAllRequest(User user) throws Exception {
        List<TeamShareRequestVO>teamShareRequestVOList=new ArrayList<TeamShareRequestVO>();
        List<Course>courseList=courseDAO.getCourseByteacherId(user.getId());
        for(int i=0;i<courseList.size();i++)
        {
            List<BigInteger>mainIdList=teamDAO.getMainIdBySubIdAndNull(courseList.get(i).getId());
            List<BigInteger>teamShareIdList=teamDAO.getIdBySubIdAndNull(courseList.get(i).getId());
            System.out.println(mainIdList);
            for(int j=0;j<mainIdList.size();j++)
            {
                Course course=courseDAO.getCourseById(mainIdList.get(j));
                String courseName=course.getCourseName();
                User teacher=teacherDAO.getInformation(course.getTeacherId());
                String teacherName=teacher.getName();
                BigInteger teamShareId=teamShareIdList.get(j);
                TeamShareRequestVO teamShareRequestVO=new TeamShareRequestVO();
                teamShareRequestVO.setMainCourseName(courseName);
                teamShareRequestVO.setMainTeacherName(teacherName);
                teamShareRequestVO.setShareTeamId(teamShareId);
                teamShareRequestVOList.add(teamShareRequestVO);
            }
        }


        List<TeamValidRequestVO>teamValidRequestVOList=teamDAO.getTeamValidByTeacherIdAndNull(user.getId());
        List<BigInteger> teamIdList=teamDAO.getTeamIdByTeacherIdAndNull(user.getId());
        for(int i=0;i<teamValidRequestVOList.size();i++)
        {
            Team team=teamDAO.getTeamById(teamIdList.get(i));
            User leader=studentDAO.getInformation(team.getLeaderId());
            Course course=courseDAO.getCourseById(team.getCourseId());
            Klass klass=klassDAO.getKlassById(team.getKlassId());
            teamValidRequestVOList.get(i).setKlassName(String.valueOf(klass.getGrade())+String.valueOf(klass.getKlassSerial()));
            teamValidRequestVOList.get(i).setLeaderName(leader.getName());
            teamValidRequestVOList.get(i).setCourseName(course.getCourseName());
        }
        RequestVO requestVO=new RequestVO();
        requestVO.setTeamShareRequestVOList(teamShareRequestVOList);
        requestVO.setTeamValidRequestVOList(teamValidRequestVOList);

        return requestVO;
    }


    /**
     *按id修改共享组队请求状态
     */
    public void setTeamShareRequest( BigInteger teamshareId,String handletype) throws Exception {
        /**
         *不同意
         */
        String refuse="refuse";
        if(handletype.equals(refuse))
        {

            teamDAO.setStatusById(teamshareId,0);
        }
        /**
         *同意
         */
        else {
            teamDAO.setStatusById(teamshareId, 1);
            BigInteger subCourseId = teamDAO.getSubIdById(teamshareId);
            BigInteger mainCourseId=teamDAO.getMainIdById(teamshareId);
            courseDAO.setTeamMainCourseIdById(mainCourseId,subCourseId);
            List<BigInteger>teamIdList=teamDAO.getIdByCourseId(mainCourseId);

            for(int i=0;i<teamIdList.size();i++)
            {
                List<BigInteger>studentIdList=teamDAO.getStuIdByTeamId(teamIdList.get(i));

                for(int j=0;j<studentIdList.size();j++)
                {
                    List<BigInteger>courseIdList=teamDAO.getCourseIdByStuId(studentIdList.get(j));
                    if(courseIdList.contains(subCourseId))
                    {

                        klassDAO.setTeamIdByCourseIdAndStudentId(teamIdList.get(i),subCourseId,studentIdList.get(j));
                        BigInteger klassId=klassDAO.getKlassIdByStuIdAndCourseId(studentIdList.get(j),subCourseId);

                        // klassDAO.setTeamIdByklassId(teamIdList.get(i),klassId);
                    }

                }

            }
        }
    }


    /**
     * 按id修改组队合法请求状态
     */
    public void setTeamValidRequest( BigInteger teamvalidId,String handletype)
    {
        /**
         *不同意
         */
        String refuse="refuse";
        if(handletype.equals(refuse)) {
            teamDAO.setTeamValid(teamvalidId, 0);
        }

        /**
         *同意
         */
        else {
            teamDAO.setTeamValid(teamvalidId, 1);
        }
    }

    /**
     * 根据id查看小组
     */
    public Team getTeamById(BigInteger teamId) throws NotFoundException, UserNotFoundException {
        return teamDAO.getTeamById(teamId);
    }

    public Team1VO getTeam1VOById(BigInteger teamId) throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, NotFoundException {

        Team team=teamDAO.getTeamById(teamId);
        User leader=studentDAO.getById(team.getLeaderId());
        Klass klass=klassDAO.getKlassById(team.getKlassId());
        Course course=courseDAO.getCourseById(team.getCourseId());
        List<User>members=new ArrayList<>();
        List<BigInteger> studentIds=teamDAO.getStudentIdByTeamId(teamId);
        for(BigInteger studentId:studentIds)
        {
            members.add(studentDAO.getById(studentId));
        }
        Team1VO team1VO=new Team1VO(teamId,team.getTeamName(),leader,team.getTeamSerial(),team.getKlassSerial(),
                klass,course,members,team.getStatus());
        return team1VO;
    }


    /**
     * 根据课程Id获取小组信息
     */
    public List<Team2VO> getAllTeam(User user,BigInteger courseId) throws ClassNotFoundException, CourseNotFoundException, IllegalAccessException, UserNotFoundException, NotFoundException {
        Course course=courseDAO.getCourseById(courseId);
        

        List<Team2VO>team2VOList=new ArrayList<Team2VO>();
        List<Klass>klassList=klassDAO.getKlassByCourseId(courseId);
        List<BigInteger>teamIdList=new ArrayList<BigInteger>();
        for(int i=0;i<klassList.size();i++)
        {
            List<BigInteger>subTeamIdList=klassDAO.getTeamIdByKlassId(klassList.get(i).getId());
            teamIdList.addAll(subTeamIdList);
        }
        for(int i=0;i<teamIdList.size();i++)
        {
            Team team=teamDAO.getTeamById(teamIdList.get(i));
            Team2VO team2VO=new Team2VO();
            team2VO.setTeamId(team.getId());
            team2VO.setTeamName(team.getTeamName());
            team2VO.setValid(team.getStatus());
            StudentInTeamVO leader=new StudentInTeamVO();
            leader.setId(team.getLeaderId());

            User student=studentDAO.getInformation(team.getLeaderId());
            leader.setAccount(student.getAccount());
            leader.setName(student.getName());
            team2VO.setLeader(leader);

            List<BigInteger>studentIdList=teamDAO.getStuIdByTeamId(team.getId());
            List<StudentInTeamVO>members=new ArrayList<StudentInTeamVO>();
            for(int j=0;j<studentIdList.size();j++)
            {
                if(!studentIdList.get(j).equals(leader.getId()))
                {
                    User memberStudent= studentDAO.getInformation(studentIdList.get(j));
                    StudentInTeamVO member=new StudentInTeamVO();
                    member.setId(memberStudent.getId());
                    member.setName(memberStudent.getName());
                    member.setAccount(memberStudent.getAccount());
                    members.add(member);
                }
            }
            team2VO.setMembers(members);
            team2VOList.add(team2VO);
        }
        return team2VOList;
    }




    public Team getTeamByKlassIdAndStudentId(BigInteger klassId, BigInteger studentId) throws ClassNotFoundException, NotFoundException {
        Klass klass = klassDAO.getKlassById(klassId);
        BigInteger teamId = teamDAO.getTeamIdByStuIdAndCourseId(studentId, klass.getCourseId());
        return  teamDAO.getTeamById(teamId);
    }




    public void deleteTeam(BigInteger teamId)
    {
        teamDAO.deleteTeam(teamId);
        teamDAO.deleteKlassStudentTeam(teamId);
        teamDAO.deleteKlassTeam(teamId);
        teamDAO.deleteTeamStudent(teamId);
    }

    public void addTeamMember(BigInteger teamId,BigInteger studentId) throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, NotFoundException {
        BigInteger klassId=teamDAO.getTeamById(teamId).getKlassId();
        teamDAO.addTeamMember(teamId,studentId,klassId);
        Team team=teamDAO.getTeamById(teamId);
        List<BigInteger>memberIds=teamDAO.getStudentIdByTeamId(teamId);
        List<User>members=new ArrayList<>();
        for(BigInteger memberId:memberIds)
        {
            User user=studentDAO.getById(memberId);
            members.add(user);
        }
        Team1VO team1VO=new Team1VO(teamId,team.getTeamName(),studentDAO.getById(team.getLeaderId()),team.getTeamSerial(),
                team.getKlassSerial(),klassDAO.getKlassById(team.getKlassId()),courseDAO.getCourseById(team.getCourseId()),
                members,team.getStatus());
        boolean valid=isValid(teamId,team.getKlassId(),team.getCourseId(),team.getTeamName(),team1VO.getLeader(),members);
        if (!valid) {
            teamDAO.changeTeamStatus(teamId, 0);
        }
    }

    public void deleteTeamMember(BigInteger teamId,BigInteger studentId) throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, NotFoundException {
        teamDAO.deleteTeamMember(teamId,studentId);
        Team team=teamDAO.getTeamById(teamId);
        List<BigInteger>memberIds=teamDAO.getStudentIdByTeamId(teamId);
        List<User>members=new ArrayList<>();
        for(BigInteger memberId:memberIds)
        {
            User user=studentDAO.getById(memberId);
            members.add(user);
        }
        Team1VO team1VO=new Team1VO(teamId,team.getTeamName(),studentDAO.getById(team.getLeaderId()),team.getTeamSerial(),
                team.getKlassSerial(),klassDAO.getKlassById(team.getKlassId()),courseDAO.getCourseById(team.getCourseId()),
                members,team.getStatus());
        boolean valid=isValid(teamId,team.getKlassId(),team.getCourseId(),team.getTeamName(),team1VO.getLeader(),members);
        if (!valid) {
            teamDAO.changeTeamStatus(teamId, 0);
        }
        if(studentId.equals(team.getLeaderId()))
        {
            deleteTeam(teamId);
        }
    }

    public void teamValidRequest(BigInteger teamId,BigInteger courseId,String reason) throws CourseNotFoundException, UserNotFoundException, NotFoundException {
        if(teamDAO.getTeamById(teamId).getStatus()==0)
        {
            teamDAO.createRequest(teamId,courseDAO.getCourseById(courseId).getTeacherId(),reason);
            teamDAO.changeTeamStatus(teamId,2);
        }
    }

}
