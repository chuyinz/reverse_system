package com.example.cms.dao;

import com.example.cms.entity.*;
import com.example.cms.exception.CourseNotFoundException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.mapper.*;
import com.example.cms.vo.Team1VO;
import com.example.cms.vo.TeamValidRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zyy szw
 * @date 2018-12-25
 */
@Component
public class TeamDAO {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private ShareTeamMapper shareTeamMapper;
    @Autowired
    private TeamValidApplicationMapper teamValidMapper;
    @Autowired
    private MemberLimitStrategyMapper memberLimitMapper;
    @Autowired
    private TeamAndStrategyMapper teamAndStrategyMapper;
    @Autowired
    private TeamOrStrategyMapper teamOrStrategyMapper;
    @Autowired
    private TeamStrategyMapper teamStrategyMapper;
    @Autowired
    private TeamStudentMapper teamStudentMapper;
    @Autowired
    private KlassStudentMapper klassStudentMapper;
    @Autowired
    private KlassTeamMapper klassTeamMapper;

    public List<BigInteger> getTeamIdByStuId(BigInteger studentId)
    {
        return teamMapper.getTeamIdByStuId(studentId);
    }


    public BigInteger getTeamIdByStuIdAndCourseId(BigInteger studentId,BigInteger courseId)
    {
        return teamMapper.getTeamIdByStuIdAndCourseId(studentId,courseId);
    }


    public List<BigInteger>  getStuIdByTeamId(BigInteger teamId)
    {
        return teamMapper.getStuIdByTeamId(teamId);
    }


    public Team getTeamById(BigInteger teamId) throws NotFoundException {
        Team team=teamMapper.getTeamById(teamId);
        if(team==null)
        {
            throw new NotFoundException("未找到小组");
        }
         return team;
    }


    public  List<BigInteger> getStudentIdByTeamId(BigInteger teamId){
        return teamStudentMapper.getStudentIdByTeamId(teamId);
    }



    public List<BigInteger> getMainIdBySubIdAndStatus(BigInteger courseId,Integer status) throws CourseNotFoundException {
        List<BigInteger> mainIdList=shareTeamMapper.getMainIdBySubIdAndStatus(courseId,status);
        if(mainIdList==null)
        {
            throw new CourseNotFoundException();
        }
        return mainIdList;
    }


    public List<BigInteger> getSubIdByMainIdAndStatus(BigInteger courseId,Integer status) throws CourseNotFoundException {
        List<BigInteger>subIdList=shareTeamMapper.getSubIdByMainIdAndStatus(courseId,status);
        if(subIdList==null)
        {
            throw new CourseNotFoundException();
        }
        return subIdList;
    }

   public  List<BigInteger> getIdByMainIdAndStatus(BigInteger courseId,Integer status)
    {
        return shareTeamMapper.getIdByMainIdAndStatus(courseId,status);
    }


    public List<BigInteger> getIdBySubIdAndStatus(BigInteger courseId,Integer status)
    {
        return shareTeamMapper.getIdBySubIdAndStatus(courseId,status);
    }




    public void  deleteTeamShareById( BigInteger teamshareId)
    {
        shareTeamMapper.deleteTeamShareById(teamshareId);
    }


   public void createTeamShareRequest(BigInteger mainCourseId,BigInteger subCourseId,BigInteger teacherId,Integer status)
   {
       shareTeamMapper.createTeamShareRequest(mainCourseId,subCourseId,teacherId,status);
   }


    public List<TeamValidRequestVO>  getTeamValidByTeacherIdAndStatus(BigInteger teacherId,Integer status) throws Exception {
        List<TeamValidRequestVO>teamValidRequestVOList=teamValidMapper.getTeamValidByTeacherIdAndStatus(teacherId,status);
        if(teamValidRequestVOList==null)
        {
            throw new Exception();
        }
        else {
            return teamValidRequestVOList;
        }
    }

    public List<BigInteger> getTeamIdByTeacherIdAndStatus(BigInteger teacherId,Integer status) throws Exception {
        List<BigInteger>teamIdList=teamValidMapper.getTeamIdByTeacherIdAndStatus(teacherId,status);
        if(teamIdList==null)
        {
            throw new Exception();
        }
        else {
            return teamIdList;
        }
    }


    public void setStatusById(BigInteger teamshareId,Integer status)
    {
        shareTeamMapper.setStatusById(teamshareId,status);
    }

    public BigInteger getSubIdById(BigInteger teamshareId) throws Exception {
        BigInteger subId=shareTeamMapper.getSubIdById(teamshareId);
        if(subId==null)
        {
            throw new Exception();
        }
        return subId;
    }

    public BigInteger getMainIdById(BigInteger teamshareId) throws Exception {
        BigInteger mainId=shareTeamMapper.getMainIdById(teamshareId);
        if(mainId==null)
        {
            throw new Exception();
        }
        return mainId;
    }

    public void deleteTeamByCourseId(BigInteger courseId)
    {
        teamMapper.deleteTeamByCourseId(courseId);
    }

    public void deleteTeam(BigInteger teamId)
    {
        teamMapper.deleteTeam(teamId);
    }


    public void deleteTeamStudent(BigInteger teamId)
    {
        teamStudentMapper.deleteTeamStudent(teamId);
    }

    public void deleteKlassStudentTeam(BigInteger teamId)
    {
        klassStudentMapper.deleteklassStudentTeam(teamId);
    }

    public void deleteKlassTeam(BigInteger teamId)
    {
        klassTeamMapper.deleteKlassTeam(teamId);
    }
    public void createRequest(BigInteger teamId,BigInteger teacherId,String reason)
    {
        teamValidMapper.createRequest(teamId,teacherId,reason);
    }


    public void setTeamValid( BigInteger teamvalidId,Integer status)
    {
        teamValidMapper.setStatusById(teamvalidId,status);
    }

    public BigInteger getIdByMemberLimit(int memberMin,int memberMax)
    {
        return memberLimitMapper.getIdByMemberLimit(memberMin,memberMax);
    }



    public void createMemberLimit(int memberMin,int memberMax)
    {
        memberLimitMapper.createMemberLimit(memberMin,memberMax);
    }


    public BigInteger getMaxIdOfTeamAndStrategy()
    {
        return teamAndStrategyMapper.getMaxIdOfTeamAndStrategy();
    }

    public BigInteger getMaxIdOfTeamOrStrategy()
    {
        return teamOrStrategyMapper.getMaxIdOfTeamOrStrategy();
    }


    public void createTeamAndStrategy(BigInteger teamAndStrategyId,String name,BigInteger id)
    {
        teamAndStrategyMapper.createTeamAndStrategy(teamAndStrategyId,name,id);
    }

    public void createTeamOrStrategy(BigInteger teamAndStrategyId,String name,BigInteger id)
    {
        teamOrStrategyMapper.createTeamOrStrategy(teamAndStrategyId,name,id);
    }

   public Integer getMaxIdByCourseId(BigInteger courseId)
   {
       return teamStrategyMapper.getMaxIdByCourseId(courseId);
   }

   public void createTeamStrategy(BigInteger courseId,Integer strategySerial,String name,BigInteger strategyId)
   {
       teamStrategyMapper.createTeamStrategy(courseId,strategySerial,name,strategyId);
   }



    public void createTeam(BigInteger klassId,BigInteger courseId,BigInteger leaderId,String teamName,
                           int teamSerial,int klassSerial)
    {
        teamMapper.createTeam(klassId, courseId, leaderId, teamName, teamSerial, klassSerial);

    }

    public Integer getTeamSerialByKlassId(BigInteger klassId)
    {
        return teamMapper.getTeamSerialByKlassId(klassId);
    }

    public void createTeamStudent(BigInteger teamId,BigInteger studentId)
    {
        teamStudentMapper.createTeamStudent(teamId,studentId);
    }


    public BigInteger getTeamId(int klassSerial,int teamSerial)
    {
        return teamMapper.getTeamId(klassSerial, teamSerial);
    }

    public void createKlassTeam(BigInteger klassId,BigInteger teamId)
    {
        teamMapper.createKlassTeam(klassId,teamId);
    }

    public List<BigInteger> getKlassIds(BigInteger teamId)
    {
        return  klassTeamMapper.getKlassIds(teamId);
    }


    public void addTeamMember(BigInteger teamId,BigInteger studentId,BigInteger klassId)
    {
        klassStudentMapper.addTeamMember(teamId,studentId,klassId);
        teamStudentMapper.addTeamMember(teamId,studentId);
    }


    public void deleteTeamMember(BigInteger teamId,BigInteger studentId)
    {
        klassStudentMapper.deleteTeamMember(teamId,studentId);
        teamStudentMapper.deleteTeamMember(teamId,studentId);
    }


    public void changeTeamStatus(BigInteger teamId,int status)
    {
        teamMapper.changeTeamStatus(teamId,status);
    }


    public List<TeamStrategy> getStrategyByCourseId(BigInteger courseId)
    {
        return teamMapper.getStrategyByCourseId(courseId);
    }


    public BigInteger getTeamIdByLeaderId(BigInteger leaderId)
    {
        return  teamMapper.getTeamIdByLeaderId(leaderId);
    }


    public boolean isValidByMemberLimit(Team1VO team1VO, BigInteger strategyId)
    {
        MemberLimitStrategy memberLimitStrategy=teamMapper.getMemberLimitStrategy(strategyId);
        return memberLimitStrategy.isValid(team1VO);
    }


    public BigInteger getTeamIdByStudentId(BigInteger studentId)
    {
        return teamStudentMapper.getTeamIdByStudentId(studentId);
    }




    public boolean isValidByConflictCourse(Team1VO team1VO, BigInteger strategyId)
    {
        List<BigInteger> courseIds=teamMapper.getConflictCourseId(strategyId);
        BigInteger teamId=getTeamIdByLeaderId(team1VO.getLeader().getId());
        int sum=0;
        for(BigInteger courseId:courseIds)
        {
            List<BigInteger> ids=teamMapper.getMemberIdByCourseId(courseId,teamId);
            int count=ids.size();
            if(count!=0)
            {
                sum++;
            }
            if(sum>1) {
                return false;
            }
        }
        return true;
    }




    public boolean isValidByCourseMemberLimit(Team1VO team1VO, BigInteger strategyId)
    {
        CourseMemberLimitStrategy courseMemberLimitStrategy=teamMapper.getCourseMemberLimitStrategy(strategyId);
        BigInteger teamId=getTeamIdByLeaderId(team1VO.getLeader().getId());
        List<BigInteger> numbers=teamMapper.getMemberIdByCourseId(courseMemberLimitStrategy.getCourseId(),team1VO.getId());
        int count=numbers.size();

        return courseMemberLimitStrategy.isValid(count);
    }




    public void createKlassStudent(BigInteger klassId,BigInteger studentId,BigInteger teamId) {
        klassStudentMapper.updateKlassStudent(klassId, studentId, teamId);
    }
    public boolean isValidByAndStrategy(Team1VO team1VO, BigInteger strategyId)
    {
        String str1="MemberLimitStrategy";
        String str2="CourseMemberStrategy";
        String str3="ConflictCourseStrategy";
        String str4="TeamOrStrategy";
        String str5="TeamAndStrategy";
        List<TeamAndOrStrategy> andStrategyList=teamMapper.getAndStrategy(strategyId);
        for(TeamAndOrStrategy andStrategy:andStrategyList)
        {
            if(andStrategy.getStrategyName().equals(str1))
            {
                boolean valid=isValidByMemberLimit(team1VO,andStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
            if(andStrategy.getStrategyName().equals(str2))
            {
                boolean valid=isValidByCourseMemberLimit(team1VO,andStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
            if(andStrategy.getStrategyName().equals(str3))
            {
                boolean valid=isValidByConflictCourse(team1VO,andStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
            if(andStrategy.getStrategyName().equals(str4))
            {
                boolean valid=isValidByOrStrategy(team1VO,andStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
            if(andStrategy.getStrategyName().equals(str5))
            {
                boolean valid=isValidByAndStrategy(team1VO,andStrategy.getStrategyId());
                if(!valid)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidByOrStrategy(Team1VO team1VO, BigInteger strategyId)
    {
        String str1="MemberLimitStrategy";
        String str2="CourseMemberLimitStrategy";
        String str3="ConflictCourseStrategy";
        String str4="TeamOrStrategy";
        String str5="TeamAndStrategy";
        List<TeamAndOrStrategy> orStrategyList=teamMapper.getOrStrategy(strategyId);
        for(TeamAndOrStrategy orStrategy:orStrategyList)
        {
            if(orStrategy.getStrategyName().equals(str1))
            {
                boolean valid=isValidByMemberLimit(team1VO,orStrategy.getStrategyId());
                if(valid)
                {
                    return true;
                }
            }
            else if(orStrategy.getStrategyName().equals(str2))
            {
                boolean valid=isValidByCourseMemberLimit(team1VO,orStrategy.getStrategyId());
                if(valid)
                {
                    return true;
                }
            }
            else if(orStrategy.getStrategyName().equals(str3))
            {
                boolean valid=isValidByConflictCourse(team1VO,orStrategy.getStrategyId());
                if(valid)
                {
                    return true;
                }
            }
            else if(orStrategy.getStrategyName().equals(str4))
            {
                boolean valid=isValidByOrStrategy(team1VO,orStrategy.getStrategyId());
                if(valid)
                {
                    return true;
                }
            }
            else if(orStrategy.getStrategyName().equals(str5))
            {
                boolean valid=isValidByAndStrategy(team1VO,orStrategy.getStrategyId());
                if(valid)
                {
                    return true;
                }
            }
        }
        return false;
    }



    public Team1VO getTeam1VOById(BigInteger teamId)
    {
        return teamMapper.getTeam1VOById(teamId);
    }


    public List<BigInteger> getTeamIdByCourseId(BigInteger mainCourseId) throws Exception {
        List<BigInteger> teamIdList=teamMapper.getTeamIdByCourseId(mainCourseId);
        if(teamIdList.size()==0)
        {
            throw new Exception("Team not found.");
        }
        return teamIdList;
    }


    public List<BigInteger> getIdByCourseId(BigInteger courseId)
    {
        return teamMapper.getTeamIdByCourseId(courseId);
    }

   public List<BigInteger> getCourseIdByStuId(BigInteger studentId) throws Exception {
       List<BigInteger>studentIdList=klassStudentMapper.getCourseIdByStuId(studentId);
       if(studentIdList.size()==0)
       {
           throw new Exception("error");
       }
       return studentIdList;
   }
   public Integer getMaxMemberById(BigInteger id)
   {
       return memberLimitMapper.getMaxMemberById(id);
   }


    public Integer getMinMemberById(BigInteger id)
    {
        return memberLimitMapper.getMinMemberById(id);
    }



    public List<BigInteger> getIdBySubIdAndNull(BigInteger courseId)
    {
        return shareTeamMapper.getIdBySubIdAndNull(courseId);
    }

    public List<BigInteger>getMainIdBySubIdAndNull(BigInteger courseId)
    {
        return shareTeamMapper.getMainIdBySubIdAndNull(courseId);
    }



    public List<BigInteger> getTeamIdByTeacherIdAndNull(BigInteger teacherId) throws Exception {
        List<BigInteger>teamIdList=teamValidMapper.getTeamIdByTeacherIdAndNull(teacherId);
        if(teamIdList==null)
        {
            throw new Exception();
        }
        else {
            return teamIdList;
        }
    }



    public List<TeamValidRequestVO>  getTeamValidByTeacherIdAndNull(BigInteger teacherId) throws Exception {
        List<TeamValidRequestVO>teamValidRequestVOList=teamValidMapper.getTeamValidByTeacherIdAndNull(teacherId);
        if(teamValidRequestVOList==null)
        {
            throw new Exception();
        }
        else {
            return teamValidRequestVOList;
        }
    }

}
