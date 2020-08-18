package com.example.cms.controller;

import com.example.cms.dao.CourseDAO;
import com.example.cms.vo.SimpleRoundVO;
import com.example.cms.dao.StudentDAO;
import com.example.cms.dao.TeamDAO;
import com.example.cms.entity.*;
import com.example.cms.exception.*;
import com.example.cms.mapper.StudentMapper;
import com.example.cms.service.AccountService;
import com.example.cms.service.CourseService;
import com.example.cms.service.TeamService;
import com.example.cms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigInteger;
import java.sql.Timestamp;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author zyy&szw
 * @date 2018-12-30
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private StudentDAO studentDAO;

    /**
     * 创建课程
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createCourse(@RequestBody CreateCourseVO coursePackage, @RequestAttribute("user") User user) throws InfoIllegalException, UserNotFoundException, CourseNotFoundException, AccessDenyException {
        if(user.getRole()==0)
        {
            throw new AccessDenyException("学生无法创建课程");
        }

        String name = coursePackage.getName();
        String intro = coursePackage.getIntro();
        Integer presentationWeight = coursePackage.getPresentationPercentage();
        Integer questionWeight = coursePackage.getQuestionPercentage();
        Integer reportWeight = coursePackage.getReportPercentage();
        Integer teamNumberMin = coursePackage.getTeamNumberMin();
        Integer teamNumberMax = coursePackage.getTeamNumberMax();
        Integer condition=coursePackage.getCondition();


        Timestamp teamStartTime = new Timestamp(System.currentTimeMillis());
        String startTime = coursePackage.getTeamStartTime();
        teamStartTime = Timestamp.valueOf(startTime);

        Timestamp teamEndTime = new Timestamp(System.currentTimeMillis());
        String endTime = coursePackage.getTeamEndTime();
        teamEndTime = Timestamp.valueOf(endTime);

        BigInteger teacherId = user.getId();

        BigInteger teamMainCourseId = null;
        BigInteger seminarMainCourseId = null;
        Course course = new Course();
        course.setCourseName(name);
        course.setIntroduction(intro);
        course.setPresentationPercentage(presentationWeight);
        course.setQuestionPercentage(questionWeight);
        course.setReportPercentage(reportWeight);
        course.setTeamMainCourseId(teamMainCourseId);
        course.setTeamEndTime(teamEndTime);
        course.setTeamStartTime(teamStartTime);
        course.setSeminarMainCourseId(seminarMainCourseId);
        course.setTeamNumberMax(teamNumberMax);
        course.setTeamNumberMin(teamNumberMin);
        course.setTeacherId(teacherId);
        courseService.createCourse(course,coursePackage.getConflictCourseList(),coursePackage.getOptionCourseVOList(),condition);
    }


    @GetMapping("/{courseId}/round")
    public List<SimpleRoundVO> getAllRound( @PathVariable("courseId")BigInteger courseId) throws NotFoundException, NotFoundException {
        List<Round>roundList = courseService.getAllRound(courseId);
        List<SimpleRoundVO>simpleRoundVOList = new ArrayList<SimpleRoundVO>();
        for (Round round : roundList){
            simpleRoundVOList.add(new SimpleRoundVO(round));
        }
        return simpleRoundVOList;
    }



    /**
     * 查看所有课程
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<CourseVO> getAllCourse(@RequestAttribute("user") User user) throws CourseNotFoundException, ClassNotFoundException, UserNotFoundException {
        return courseService.getAllCourse(user);
    }


    /**
     * 根据课程id查看课程信息
     */
    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    public Course2VO getCourseInfoById(@PathVariable("courseId") BigInteger courseId) throws CourseNotFoundException, UserNotFoundException {
        return courseService.getCourseById(courseId);
    }


    /**
     * 根据课程id删除课程
     */
    @RequestMapping(value = "/{courseId}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable("courseId") BigInteger courseId, @RequestAttribute("user") User user) throws UserNotFoundException, CourseNotFoundException, ClassNotFoundException, AccessDenyException {

        courseService.deleteCourse(courseId, user);
    }


    /**
     * 创建班级
     */
    @PostMapping("/{courseId}/class")
    public void createKlass(@PathVariable("courseId") BigInteger courseId, @RequestBody Map<String, Object> map, @RequestAttribute("user") User user) throws UserNotFoundException, CourseNotFoundException, AccessDenyException {
        int grade = Integer.parseInt(map.get("grade").toString());
        int klassSerial = Integer.parseInt(map.get("klassSerial").toString());
        String klassTime = map.get("klassTime").toString();
        String klassLocation = map.get("klassLocation").toString();
        Klass klass = new Klass();
        klass.setCourseId(courseId);
        klass.setKlassLocation(klassLocation);
        klass.setGrade(grade);
        klass.setKlassSerial(klassSerial);
        klass.setKlassTime(klassTime);
        courseService.createKlass(klass, user, courseId);
    }


    /**
     * 按课程ID查看该课程下的所有班级
     */
    @RequestMapping(value = "/{courseId}/class", method = RequestMethod.GET)
    public List<Klass> getAllKlassByCourseId(@PathVariable("courseId") BigInteger courseId) throws ClassNotFoundException {
        return courseService.getAllKlassByCourseId(courseId);
    }


    /**
     * 根据班级id查看班级信息
     */
    @RequestMapping(value = "/class/{classId}", method = RequestMethod.GET)
    public Klass getKlassInfoById(@PathVariable("classId") BigInteger classId) throws UserNotFoundException, ClassNotFoundException {
        return courseService.getKlassById(classId);
    }


    /**
     * 导入学生名单
     */
    @RequestMapping(value = "/{courseId}/class/{classId}/upload", method = RequestMethod.POST)
    public void loadStudent(@RequestParam("file") MultipartFile file, @PathVariable("classId") BigInteger classId, @PathVariable("courseId") BigInteger courseId) throws Exception {

        accountService.ajaxUploadExcel(file, classId, courseId);
    }


    /**
     * 根据班级id删除某班级
     */
    @RequestMapping(value = "/class/{classId}", method = RequestMethod.DELETE)
    public void deleteKlass(@PathVariable("classId") BigInteger classId, @RequestAttribute("user") User user) throws UserNotFoundException, CourseNotFoundException, ClassNotFoundException, AccessDenyException {
        courseService.deleteKlass(classId, user);
    }


    /**
     * 根据课程Id获取小组信息
     */
    @RequestMapping(value = "/{courseId}/team", method = RequestMethod.GET)
    public List<Team2VO> getAllTeam(@PathVariable("courseId") BigInteger courseId, @RequestAttribute("user") User user) throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, IllegalAccessException, NotFoundException {
       return teamService.getAllTeam(user,courseId);
    }

    /**
     *  根据课程Id以及学生id获取我的小组信息
     */
    @RequestMapping(value = "/{courseId}/myTeam", method = RequestMethod.GET)
    public Team2VO getTeamInfo(@PathVariable("courseId") BigInteger courseId, @RequestAttribute("user") User user) throws UserNotFoundException, NotFoundException {
        return courseService.getTeamInfo(courseId,user);
        }

    /**
     *  根据课程Id获取未组队的学生
     */
    @RequestMapping(value = "/{courseId}/noTeam", method = RequestMethod.GET)
    public List<StudentInTeamVO> getNoTeamStudent(@PathVariable("courseId") BigInteger courseId) throws Exception {
        return courseService.getNoTeamStudent(courseId);
    }


    /**
     * 发起一个队伍共享请求
     */
    @RequestMapping(value = "/{courseId}/createTeamShare", method = RequestMethod.POST)
    public void createTeamShareRequestByCourseId(@PathVariable("courseId") BigInteger courseId,@RequestBody Map<String,Object>map,@RequestAttribute("user") User user) throws UserNotFoundException, CourseNotFoundException, InvalidOperationException, AccessDenyException {
        Course course=courseDAO.getCourseById(courseId);
        if(course==null||!course.getTeacherId().equals(user.getId()))
        {
            throw new AccessDenyException(("您不是本课程教师，没有权限发起共享请求"));
        }
        BigInteger subCourseId=new BigInteger(map.get("id").toString());

        courseService.createTeamShareRequestByCourseId(courseId,subCourseId);
    }


    /**
     * 新建共享页面 date:1.3
     */
    @RequestMapping(value = "/{courseId}/createTeamShare", method = RequestMethod.GET)
    public List<SelectCourseVO> getAllNoShareCourse(@PathVariable("courseId") BigInteger courseId,@RequestAttribute("user") User user) throws CourseNotFoundException, AccessDenyException, UserNotFoundException {
        Course course=courseDAO.getCourseById(courseId);
        if(course==null||!course.getTeacherId().equals(user.getId()))
        {
            throw new AccessDenyException(("您不是本课程教师，没有权限发起共享请求"));
        }
        return courseService.getAllNoShareCourse(courseId);
    }


    /**
     * 创建课程页面 date:1.3
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public SelectCourse2VO getConflictCourseList(@RequestAttribute("user") User user) throws AccessDenyException, UserNotFoundException, CourseNotFoundException {
        if(user.getRole()==0)
        {
            throw new AccessDenyException("学生无法创建课程");
        }
        return  courseService.getAllCourseList();
    }



    /**
     * 获取所有共享信息
     */
    @RequestMapping(value = "/{courseId}/teamshare", method = RequestMethod.GET)
    public List<ShareTeamVO> getAllTeamShareByCourseId(@PathVariable("courseId") BigInteger courseId) throws UserNotFoundException, CourseNotFoundException {
        return courseService.getAllTeamShareByCourseId(courseId);
    }


    /**
     * 按ID删除共享
     */
    @RequestMapping(value = "/teamShare/{teamShareId}", method = RequestMethod.DELETE)
    public void  deleteTeamShareById(@PathVariable("teamShareId") BigInteger teamshareId, @RequestAttribute("user") User user) throws Exception {
        if(user.getRole()==0)
        {
            throw new AccessDenyException(("学生没有权限删除组队共享"));
        }
        else {
            List<Course>courseList=courseDAO.getCourseByteacherId(user.getId());
            List<BigInteger>courseIdList=new ArrayList<BigInteger>();
            for(int i=0;i<courseList.size();i++)
            {
                courseIdList.add(courseList.get(i).getId());
            }

            BigInteger mainCourseId=teamDAO.getMainIdById(teamshareId);
            BigInteger subCourseId=teamDAO.getSubIdById(teamshareId);
            if(!courseIdList.contains(mainCourseId)||!courseIdList.contains(subCourseId))
            {
                throw new AccessDenyException(("您没有权限删除此共享"));
            }
        }
        courseService.deleteTeamShareById(teamshareId);
    }

    @RequestMapping(value="/{courseId}/class/{classId}/team",method=RequestMethod.POST)
    public void createTeam(@RequestBody CreateTeamVO createteamVO,@PathVariable("courseId")BigInteger courseId,@PathVariable("classId")BigInteger klassId)
            throws ClassNotFoundException, CourseNotFoundException, UserNotFoundException, NotFoundException,InvalidOperationException {
        //  if(teamDAO.getIdBySubIdAndStatus(createteamVO.getCourseId(),1).equals(null)) {
        TeamMemberVO teamMemberVO=createteamVO.getLeader();
        User leader=studentMapper.getById(teamMemberVO.getId());
        List<TeamMemberVO> teamMemberVOList=createteamVO.getMembers();
        List<User> userList=new ArrayList<>();
        for(TeamMemberVO teamMemberVO1:teamMemberVOList)
        {
            BigInteger id=teamMemberVO1.getId();
            User user=studentDAO.getById(id);
            userList.add(user);
        }
        teamService.createTeam(createteamVO.getKlassId(), createteamVO.getCourseId(), createteamVO.getTeamName(), leader,userList);
    }

}





