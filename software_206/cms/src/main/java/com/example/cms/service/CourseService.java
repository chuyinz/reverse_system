package com.example.cms.service;

import com.example.cms.dao.*;
import com.example.cms.entity.*;
import com.example.cms.exception.*;
import com.example.cms.vo.*;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ZYY
 * @date 2018/12/30
 */
@Service
public class CourseService {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private KlassDAO klassDAO;
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private RoundDAO roundDAO;
    @Autowired
    private TeamAndStrategyDAO teamAndStrategyDAO;
    @Autowired
    private TeamOrStrategyDAO teamOrStrategyDAO;
    @Autowired
    private ConflictCourseDAO conflictCourseDAO;
    @Autowired
    private CourseMemberLimitStrategyDAO courseMemberLimitStrategyDAO;

    /**
     *创建课程
     *
     * @param course 课程信息
     * @return status=1
     */
    public void createCourse(Course course,List<ConflictCourseVO>conflictCourseList,List<OptionCourseVO>optionCourseVOList,Integer condition) throws InfoIllegalException {
        int a=100;
      if(course.getPresentationPercentage()+course.getQuestionPercentage()+course.getReportPercentage()!=a)
      {
          throw new InfoIllegalException("percentage error.");
      }
       courseDAO.createCourse(course);
      BigInteger memberLimitId=teamDAO.getIdByMemberLimit(course.getTeamNumberMin(),course.getTeamNumberMax());
      if(memberLimitId==null)
      {
          teamDAO.createMemberLimit(course.getTeamNumberMin(),course.getTeamNumberMax());
          memberLimitId=teamDAO.getIdByMemberLimit(course.getTeamNumberMin(),course.getTeamNumberMax());
      }
      BigInteger teamAndStrategyId=teamDAO.getMaxIdOfTeamAndStrategy();
      int bi=teamAndStrategyId.intValue();
      bi++;
      teamAndStrategyId=BigInteger.valueOf(bi);
      teamDAO.createTeamAndStrategy(teamAndStrategyId,"MemberLimitStrategy",memberLimitId);

      for(int i=0;i<conflictCourseList.size();i++) {
        BigInteger courseId1=conflictCourseList.get(i).getCourseId1();
        BigInteger courseId2=conflictCourseList.get(i).getCourseId2();
        List<BigInteger> id1List=courseDAO.getIdByConflictCourseId(courseId1);
        List<BigInteger> id2List=courseDAO.getIdByConflictCourseId(courseId2);
        BigInteger conflictCourseId=new BigInteger("0");
        for(BigInteger id:id1List)
        {
            if(id2List.contains(id)) {
                conflictCourseId=id;
            }
        }
        if(conflictCourseId.equals(new BigInteger("0")))
        {
            conflictCourseId=courseDAO.getMaxIdOfConflictCourse();
            int ai=conflictCourseId.intValue();
            ai++;
            conflictCourseId=BigInteger.valueOf(ai);

            courseDAO.createConflictCourse(conflictCourseId,courseId1,courseId2);
        }
        Integer strategySerial=teamDAO.getMaxIdByCourseId(course.getId());
       if(strategySerial==null) {
           teamDAO.createTeamStrategy(course.getId(),  1, "ConflictCourseStrategy", conflictCourseId);
       }
       else{
           teamDAO.createTeamStrategy(course.getId(),  strategySerial + 1, "ConflictCourseStrategy", conflictCourseId);
       }
       }
        teamDAO.createTeamStrategy(course.getId(),  teamDAO.getMaxIdByCourseId(course.getId())+1, "TeamAndStrategy", teamAndStrategyId);
        for(int i=0;i<optionCourseVOList.size();i++)
        {
              BigInteger courseMemberLimitId=courseDAO.getIdByCourseMemberLimit(optionCourseVOList.get(i).getOptionCourseId(),
                      optionCourseVOList.get(i).getOptionCourseTeamNumberMin(),optionCourseVOList.get(i).getOptionCourseTeamNumberMax());
              BigInteger teamOrStrategyId=teamDAO.getMaxIdOfTeamOrStrategy();
            int ai=teamOrStrategyId.intValue();
            ai++;
            teamOrStrategyId=BigInteger.valueOf(ai);
              if(courseMemberLimitId==null)
              {
                  courseDAO.createCourseMemberLimit(optionCourseVOList.get(i).getOptionCourseId(),
                          optionCourseVOList.get(i).getOptionCourseTeamNumberMin(),optionCourseVOList.get(i).getOptionCourseTeamNumberMax());
                  courseMemberLimitId=courseDAO.getIdByCourseMemberLimit(optionCourseVOList.get(i).getOptionCourseId(),
                          optionCourseVOList.get(i).getOptionCourseTeamNumberMin(),optionCourseVOList.get(i).getOptionCourseTeamNumberMax());
              }

            /**
             *选修课组队条件满足其一
             */
              if(condition==1) {
                  teamDAO.createTeamOrStrategy(teamOrStrategyId, "CourseMemberLimitStrategy", courseMemberLimitId);
                  teamDAO.createTeamAndStrategy(teamAndStrategyId,"TeamOrStrategy",teamOrStrategyId);
              }
              /**
               *选修课组队条件均满足
               */
              else{
                  teamDAO.createTeamAndStrategy(teamAndStrategyId,"CourseMemberLimitStrategy",courseMemberLimitId);
              }
        }
    }

    public Course getCourseByteacherIdAndcourseName(BigInteger teacherId, String courseName) throws  CourseNotFoundException {
        Course course=  courseDAO.getCourseByteacherIdAndcourseName(teacherId,courseName);
        return course;
    }

    /**
     *根据课程id删除某课程
     */
    public void deleteCourse(BigInteger courseId,User user) throws UserNotFoundException, CourseNotFoundException, ClassNotFoundException, AccessDenyException {
       Course course=courseDAO.getCourseById(courseId);
        if(course==null||!course.getTeacherId().equals(user.getId()))
        {
            throw new AccessDenyException("您没有权限删除本课程");
        }
        courseDAO.deleteCourseById(courseId);
        List<Klass>klassList=klassDAO.getKlassByCourseId(courseId);
        for(int i=0;i<klassList.size();i++)
        {
            klassDAO.deleteKlassById(klassList.get(i).getId());
        }
        teamDAO.deleteTeamByCourseId(courseId);
        klassDAO.deleteKlassByCourseId(courseId);
    }

    /**
     *查看所有课程
     */
    public List<CourseVO> getAllCourse(User user) throws CourseNotFoundException, ClassNotFoundException, UserNotFoundException {
        List<CourseVO> courseVoList=new ArrayList<CourseVO>();
        List<BigInteger>klassIdList=new ArrayList<BigInteger>();
        List<Course>courseList=new ArrayList<Course>();
        if(user.getRole()==0)
        {
            klassIdList=klassDAO.getKlassIdByStuId(user.getId());
            for(int i=0;i<klassIdList.size();i++)
            {
                Klass klass=new Klass();
                klass=klassDAO.getKlassById(klassIdList.get(i));
                Course course=courseDAO.getCourseById(klass.getCourseId());
                CourseVO courseVo=new CourseVO();
                courseVo.setId(course.getId());
                courseVo.setKlassId(klass.getId());
                courseVo.setName(course.getCourseName());
                courseVo.setKlassName(String.valueOf(klass.getGrade())+"-"+String.valueOf(klass.getKlassSerial()));
                User teacher = teacherDAO.getInformation(course.getTeacherId());
                courseVo.setTeacherName(teacher.getName());
                List<BigInteger> idList=teamDAO.getIdBySubIdAndStatus(course.getId(),1);
                List<BigInteger>id2List=teamDAO.getIdByMainIdAndStatus(course.getId(),1);
                if(idList.size()!=0)
                {
                    courseVo.setStatus(2);
                }
                else{
                    if(id2List.size()!=0)
                    {
                        courseVo.setStatus(1);
                    }
                    else {
                        courseVo.setStatus(0);
                    }
                }
                courseVoList.add(courseVo);
            }
            return courseVoList;
        }
        else {
            courseList=courseDAO.getCourseByteacherId(user.getId());
            for(int i=0;i<courseList.size();i++) {
                CourseVO courseVo = new CourseVO();
                courseVo.setId(courseList.get(i).getId());
                courseVo.setName(courseList.get(i).getCourseName());
                User teacher = teacherDAO.getInformation(courseList.get(i).getTeacherId());
                courseVo.setTeacherName(teacher.getName());
                List<BigInteger> idList=teamDAO.getIdBySubIdAndStatus(courseList.get(i).getId(),1);
                List<BigInteger>id2List=teamDAO.getIdByMainIdAndStatus(courseList.get(i).getId(),1);
                if(idList.size()!=0)
                {
                    courseVo.setStatus(2);
                }
                else{
                    if(id2List.size()!=0)
                    {
                        courseVo.setStatus(1);
                    }
                    else {
                        courseVo.setStatus(0);
                    }
                }
                courseVo.setKlassName(null);
                courseVo.setKlassId(null);
                courseVoList.add(courseVo);
            }

            return courseVoList;
        }
    }

    /**
     * 根据课程id查看课程信息
     */
    public Course2VO getCourseById(BigInteger courseId) throws CourseNotFoundException, UserNotFoundException {
        Course2VO course2VO=new Course2VO();
        Course course=courseDAO.getCourseById(courseId);
        List<TeamStrategy> teamStrategyList=teamDAO.getStrategyByCourseId(courseId);
        if(teamStrategyList.size()!=0)
        {
            BigInteger teamAndStrategyId = new BigInteger("0");
            List<BigInteger> conflictCourseStrategyList = new ArrayList<BigInteger>();
            for (int i = 0; i < teamStrategyList.size(); i++) {
                if ("TeamAndStrategy".equals(teamStrategyList.get(i).getStrategyName())) {
                    teamAndStrategyId = teamStrategyList.get(i).getStrategyId();
                } else if ("ConflictCourseStrategy".equals(teamStrategyList.get(i).getStrategyName())) {
                    BigInteger conflictCourseStrategyId = teamStrategyList.get(i).getStrategyId();
                    conflictCourseStrategyList.add(conflictCourseStrategyId);
                }
            }
            BigInteger memberLimitStrategyId = teamAndStrategyDAO.getStrategyIdByIdAndName(teamAndStrategyId, "MemberLimitStrategy");
            BigInteger teamOrStrategyId = teamAndStrategyDAO.getStrategyIdByIdAndName(teamAndStrategyId, "TeamOrStrategy");
            List<BigInteger> courseMemberLimitIdList = teamOrStrategyDAO.getStrategyIdByIdAndName(teamOrStrategyId, "CourseMemberLimitStrategy");
            Integer teamNumberMax = teamDAO.getMaxMemberById(memberLimitStrategyId);
            Integer teamNumberMin = teamDAO.getMinMemberById(memberLimitStrategyId);
            course.setTeamNumberMin(teamNumberMin);
            course.setTeamNumberMax(teamNumberMax);
            course2VO.setTeamMainCourseId(course.getTeamMainCourseId());
            course2VO.setSeminarMainCourseId(course.getSeminarMainCourseId());
            List<SelectCourseVO> conflictCourse2VOList = new ArrayList<SelectCourseVO>();
            List<TeamCourseRequestVO> teamCourseRequestVOList = new ArrayList<TeamCourseRequestVO>();
            for (int i = 0; i < conflictCourseStrategyList.size(); i++) {
                List<BigInteger> conflictCourseId = conflictCourseDAO.getCourseIdById(conflictCourseStrategyList.get(i));
                Integer a=2;
                for (int j = 0; j < a; j++) {
                    SelectCourseVO conflictCourse2VO = new SelectCourseVO();
                    conflictCourse2VO.setCourseId(conflictCourseId.get(j));
                    Course course1 = courseDAO.getCourseById(conflictCourseId.get(j));
                    conflictCourse2VO.setCourseName(course1.getCourseName());
                    User teacher = teacherDAO.getInformation(course1.getTeacherId());
                    conflictCourse2VO.setTeacherName(teacher.getName());
                    conflictCourse2VOList.add(conflictCourse2VO);
                }
            }
            course2VO.setConflictCourseList(conflictCourse2VOList);
            for (int i = 0; i < courseMemberLimitIdList.size(); i++) {
                CourseMemberLimitStrategy courseMemberLimitStrategy = courseMemberLimitStrategyDAO.getById(courseMemberLimitIdList.get(i));
                TeamCourseRequestVO teamCourseRequestVO = new TeamCourseRequestVO();
                teamCourseRequestVO.setCourseId(courseMemberLimitStrategy.getCourseId());
                Course course1 = courseDAO.getCourseById(courseMemberLimitStrategy.getCourseId());
                teamCourseRequestVO.setCourseName(course1.getCourseName());
                teamCourseRequestVO.setTeamNumberMax(courseMemberLimitStrategy.getMaxMember());
                teamCourseRequestVO.setTeamNumberMin(courseMemberLimitStrategy.getMinMember());
                teamCourseRequestVOList.add(teamCourseRequestVO);
            }

            course2VO.setTeamCourseRequestList(teamCourseRequestVOList);
        }
        else {
            Integer teamNumberMax=courseDAO.getMaxMember(courseId);
            Integer teamNumberMin=courseDAO.getMinMember(courseId);
            if(teamNumberMax!=null&&teamNumberMin!=null) {
                course.setTeamNumberMin(teamNumberMin);
                course.setTeamNumberMax(teamNumberMax);
            }
            course2VO.setTeamCourseRequestList(null);
            course2VO.setConflictCourseList(null);
        }
        course2VO.setId(course.getId());
        course2VO.setCourseName(course.getCourseName());
        course2VO.setIntroduction(course.getIntroduction());
        course2VO.setPresentationPercentage(course.getPresentationPercentage());
        course2VO.setQuestionPercentage(course.getQuestionPercentage());
        course2VO.setReportPercentage(course.getReportPercentage());
        course2VO.setTeacherId(course.getTeacherId());
        course2VO.setTeamStartTime(course.getTeamStartTime());
        course2VO.setTeamEndTime(course.getTeamEndTime());
        course2VO.setTeamMainCourseId(course.getTeamMainCourseId());
        course2VO.setSeminarMainCourseId(course.getSeminarMainCourseId());
        course2VO.setTeamNumberMax(course.getTeamNumberMax());
        course2VO.setTeamNumberMin(course.getTeamNumberMin());
        return  course2VO;
    }


    /**
     *创建班级
     */
    public void createKlass(Klass klass,User user,BigInteger courseId) throws UserNotFoundException, CourseNotFoundException, AccessDenyException {
        Course course=courseDAO.getCourseById(courseId);
        if(course==null||!course.getTeacherId().equals(user.getId()))
        {
            throw new AccessDenyException("您不可以在本课程下创建班级");
        }
        else {
            klassDAO.createKlass(klass);
        }
    }

    /**
     * 根据班级id查看班级信息
     */
    public Klass getKlassById(BigInteger klassId) throws  ClassNotFoundException {
        return klassDAO.getKlassById(klassId);
    }


    /**
     * 按课程ID查看该课程下的所有班级
     */
    public  List<Klass> getAllKlassByCourseId(BigInteger courseId) throws  ClassNotFoundException {
       return klassDAO.getKlassByCourseId(courseId);
    }


    /**
     * 根据班级id删除某班级
     */
    public void deleteKlass(BigInteger klassId,User user) throws UserNotFoundException, CourseNotFoundException, ClassNotFoundException, AccessDenyException {
        Klass klass=klassDAO.getKlassById(klassId);
        Course course=courseDAO.getCourseById(klass.getCourseId());
        if(course==null||!course.getTeacherId().equals(user.getId()))
        {
            throw new AccessDenyException(("您没有权限删除本班级"));
        }

        else {
            klassDAO.deleteKlassById(klassId);
        }
    }


    /**
     *  根据课程Id以及学生id获取我的小组信息
     */
    public Team2VO getTeamInfo(BigInteger courseId, User user) throws UserNotFoundException, NotFoundException {
        BigInteger teamId= teamDAO.getTeamIdByStuIdAndCourseId(user.getId(),courseId);

        if(teamId==null) {
            throw new UserNotFoundException();
        }
        Team myTeam = teamDAO.getTeamById(teamId);

        Team2VO teamVo=new Team2VO();
        teamVo.setTeamId(myTeam.getId());
        teamVo.setTeamName(myTeam.getTeamName());
        teamVo.setValid(myTeam.getStatus());
        StudentInTeamVO leader=new StudentInTeamVO();
        leader.setId(myTeam.getLeaderId());
        User student=studentDAO.getInformation(myTeam.getLeaderId());
        leader.setAccount(student.getAccount());
        leader.setName(student.getName());
        teamVo.setLeader(leader);

        List<BigInteger>studentIdList=teamDAO.getStuIdByTeamId(myTeam.getId());
        List<StudentInTeamVO>members=new ArrayList<StudentInTeamVO>();
        for(int i=0;i<studentIdList.size();i++)
        {
            if(!studentIdList.get(i).equals(leader.getId()))
            {
                User memberStudent= studentDAO.getInformation(studentIdList.get(i));
                StudentInTeamVO member=new StudentInTeamVO();
                member.setId(memberStudent.getId());
                member.setName(memberStudent.getName());
                member.setAccount(memberStudent.getAccount());
                members.add(member);
            }
        }
        teamVo.setMembers(members);

        return teamVo;
    }

    /**
     *  根据课程Id获取未组队的学生
     */
    public List<StudentInTeamVO> getNoTeamStudent(BigInteger courseId) throws Exception {
       List<BigInteger>studentIdList = studentDAO.getStuIdByCourseId(courseId);
       List<BigInteger>teamIdList=teamDAO.getIdByCourseId(courseId);
        List<BigInteger>studentTeamList=new ArrayList<BigInteger>();
        List<StudentInTeamVO>studentNoTeamList=new ArrayList<StudentInTeamVO>();
       for(int i=0;i< teamIdList.size();i++)
       {
           List<BigInteger>studentList=teamDAO.getStuIdByTeamId(teamIdList.get(i));
           studentTeamList.addAll(studentList);
       }
       for(int i=0;i<studentIdList.size();i++)
       {
           if(!studentTeamList.contains(studentIdList.get(i)))
           {
               User student=studentDAO.getInformation(studentIdList.get(i));
               StudentInTeamVO studentVO=new StudentInTeamVO();
               studentVO.setName(student.getName());
               studentVO.setAccount(student.getAccount());
               studentVO.setId(student.getId());
               studentNoTeamList.add(studentVO);
           }
       }
       return studentNoTeamList;



    }


    /**
     * 发起一个队伍共享请求
     */
    public void createTeamShareRequestByCourseId(BigInteger mainCourseId, BigInteger subCourseId) throws UserNotFoundException, CourseNotFoundException, InvalidOperationException {


        /**
         * 判断该课程是否为从课程
         */
        List<BigInteger> teamShareIdList = teamDAO.getIdBySubIdAndStatus(mainCourseId,1);

        /**
         * 如果该课程为从课程，不允许发起共享组队请求
         */
        if(teamShareIdList.size()!=0)
        {
            throw new InvalidOperationException("team share error！");
        }

        /**
         * 如果该课程不是从课程，允许发起共享组队请求
         */
        else {

            Course course=courseDAO.getCourseById(subCourseId);
            BigInteger teacherId = course.getTeacherId();
            teamDAO.createTeamShareRequest(mainCourseId, subCourseId, teacherId, null);
        }
    }

    /**
     * 获取所有共享信息
     */
    public List<ShareTeamVO> getAllTeamShareByCourseId(BigInteger courseId) throws CourseNotFoundException, UserNotFoundException {
        List<ShareTeamVO>teamVOList=new ArrayList<ShareTeamVO>();

        List<BigInteger> mainIdList = teamDAO.getMainIdBySubIdAndStatus(courseId,1);
        List<BigInteger> teamShareIdList = teamDAO.getIdBySubIdAndStatus(courseId,1);
        if(mainIdList.size()!=0&&teamShareIdList.size()!=0) {
            ShareTeamVO shareTeamVO = new ShareTeamVO();
            Course course = courseDAO.getCourseById(mainIdList.get(0));
            shareTeamVO.setShareCourseName(course.getCourseName());
            User user = teacherDAO.getInformation(course.getTeacherId());
            shareTeamVO.setShareTeacherName(user.getName());
            shareTeamVO.setShareTeamId(teamShareIdList.get(0));
            shareTeamVO.setShareStatus("从课程");
            teamVOList.add(shareTeamVO);
        }

        else {
            List<BigInteger> subIdList = teamDAO.getSubIdByMainIdAndStatus(courseId, 1);
            List<BigInteger> teamShareIdListT = teamDAO.getIdByMainIdAndStatus(courseId, 1);
            for (int i = 0; i < subIdList.size(); i++) {
                ShareTeamVO shareTeamVO = new ShareTeamVO();
                Course course = courseDAO.getCourseById(subIdList.get(i));
                shareTeamVO.setShareCourseName(course.getCourseName());
                User user = teacherDAO.getInformation(course.getTeacherId());
                shareTeamVO.setShareTeacherName(user.getName());
                shareTeamVO.setShareTeamId(teamShareIdListT.get(i));
                shareTeamVO.setShareStatus("主课程");
                teamVOList.add(shareTeamVO);
            }
        }
        return teamVOList;
    }


    /**
     * 按ID删除共享
     */
    public void  deleteTeamShareById( BigInteger teamshareId)
    {
        teamDAO.deleteTeamShareById(teamshareId);
    }


    public List<Round>getAllRound(BigInteger courseId) throws NotFoundException {
        return roundDAO.getAllRound(courseId);
    }


    /**
     * 创建课程页面 date:1.3
     */
    public SelectCourse2VO getAllCourseList() throws UserNotFoundException, CourseNotFoundException {
        SelectCourse2VO selectCourseVO2=new SelectCourse2VO();
        List<SelectCourseVO>courseVOList=new ArrayList<SelectCourseVO>();
        List<Course> courseList=courseDAO.getAllCourse();
       for(int i=0;i<courseList.size();i++)
       {
           SelectCourseVO course=new SelectCourseVO();
           course.setCourseId(courseList.get(i).getId());
           course.setCourseName(courseList.get(i).getCourseName());
           User teacher=teacherDAO.getInformation(courseList.get(i).getTeacherId());
           course.setTeacherName(teacher.getName());
           courseVOList.add(course);
       }
       selectCourseVO2.setConflictCourseList(courseVOList);
       selectCourseVO2.setSelectCourseList(courseVOList);
       return selectCourseVO2;
    }


    /**
     * 新建共享页面 date:1.3
     */
    public List<SelectCourseVO> getAllNoShareCourse(BigInteger courseId) throws CourseNotFoundException, UserNotFoundException {
        List<Course>courseList=courseDAO.getAllCourse();
        List<SelectCourseVO>courseVOList=new ArrayList<SelectCourseVO>();
        for(int i=0;i<courseList.size();i++)
        {
            if(!courseList.get(i).getId().equals(courseId))
            {
                List<BigInteger> mainIdList=teamDAO.getIdBySubIdAndStatus(courseList.get(i).getId(),1);
                List<BigInteger> subIdList=teamDAO.getIdByMainIdAndStatus(courseList.get(i).getId(),1);
                if(mainIdList.size()==0&&subIdList.size()==0)
                {
                    SelectCourseVO courseVO=new SelectCourseVO();
                    courseVO.setCourseId(courseList.get(i).getId());
                    courseVO.setCourseName(courseList.get(i).getCourseName());
                    User teacher=teacherDAO.getInformation(courseList.get(i).getTeacherId());
                    courseVO.setTeacherName(teacher.getName());
                    courseVOList.add(courseVO);
                }
            }
        }
        return  courseVOList;
    }


}
