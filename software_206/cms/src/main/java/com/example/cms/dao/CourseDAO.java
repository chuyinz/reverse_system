package com.example.cms.dao;

import com.example.cms.entity.Course;
import com.example.cms.exception.CourseNotFoundException;
import com.example.cms.mapper.ConflictCourseStrategyMapper;
import com.example.cms.mapper.CourseMapper;
import com.example.cms.mapper.CourseMemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
/**
 * @author zyy szw
 * @date 2018-12-25
 */
@Component
public class CourseDAO {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;
    @Autowired
    private CourseMemberLimitStrategyMapper courseMemberLimitMapper;

    public void createCourse(Course course)
    {
        courseMapper.createCourse(course);
    }



   public void createConflictCourse(BigInteger conflictCourseId,BigInteger courseId1,BigInteger courseId2)
   {

       conflictCourseStrategyMapper.createConflictCourseStrategy(conflictCourseId,courseId1);
       conflictCourseStrategyMapper.createConflictCourseStrategy(conflictCourseId,courseId2);

   }

   public Course getCourseById(BigInteger courseId) throws CourseNotFoundException {
      Course course= courseMapper.getCourseById(courseId);
      if(course==null)
      {
          throw new CourseNotFoundException();
      }
      return course;
   }



    public List<Course> getCourseByteacherId(BigInteger teacherId) throws CourseNotFoundException {
        List<Course> courseList= courseMapper.getCourseByteacherId(teacherId);
        if(courseList==null)
        {
            throw new CourseNotFoundException();
        }
        return courseList;
    }

    public Course getCourseByteacherIdAndcourseName(BigInteger teacherId,String courseName) throws CourseNotFoundException {
        Course course=  courseMapper.getCourseByteacherIdAndcourseName(teacherId,courseName);
        if(course==null)
        {
            throw new CourseNotFoundException();
        }
        return course;
    }


    public void deleteCourseById(BigInteger courseId)
    {
        courseMapper.deleteCourseById(courseId);
    }


    public  List<BigInteger> getIdByConflictCourseId(BigInteger courseId)
    {
        return conflictCourseStrategyMapper.getIdByConflictCourseId(courseId);
    }


    public BigInteger getMaxIdOfConflictCourse()
    {
        return conflictCourseStrategyMapper.getMaxIdOfConflictCourse();
    }


    public BigInteger getIdByCourseMemberLimit(BigInteger id,Integer min,Integer max)
    {
        return courseMemberLimitMapper.getIdByCourseMemberLimit(id,min,max);
    }


    public void createCourseMemberLimit(BigInteger id,Integer min,Integer max)
    {
        courseMemberLimitMapper.createCourseMemberLimit(id,min,max);
    }


    public Integer getMinMember(BigInteger courseId)
    {
        return courseMemberLimitMapper.getMinMember(courseId);
    }

    public Integer getMaxMember(BigInteger courseId)
    {
        return courseMemberLimitMapper.getMaxMember(courseId);
    }


    public void setTeamMainCourseIdById(BigInteger mainCourseId,BigInteger subCourseId)
    {
        courseMapper.setTeamMainCourseIdById(mainCourseId,subCourseId);
    }

    public List<Course>getAllCourse() throws CourseNotFoundException {
       List<Course>courseList=courseMapper.getAllCourse();
       if(courseList.size()==0)
       {
           throw new CourseNotFoundException("目前没有课程！");
       }
        return courseList;
    }


}
