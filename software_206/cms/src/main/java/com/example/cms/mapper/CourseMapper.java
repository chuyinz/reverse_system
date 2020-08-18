package com.example.cms.mapper;

import com.example.cms.entity.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author shaoziwei
 * @date 2018/12/13
 */


public interface CourseMapper {
    /**
     * 创建课程
     * @param course
     */
    @SelectKey(statement="select max(id) from course" , before=false,keyColumn="id",resultType=BigInteger.class,keyProperty="id")
    @Insert({"INSERT INTO course(teacher_id,course_name,introduction,presentation_percentage,question_percentage," +
            "report_percentage,team_start_time,team_end_time,team_main_course_id,seminar_main_course_id) " +
            "values (#{teacherId},#{courseName},#{introduction},#{presentationPercentage},#{questionPercentage}," +
            "#{reportPercentage},#{teamStartTime},#{teamEndTime},#{teamMainCourseId},#{seminarMainCourseId})"})
    void createCourse(Course course);

    /**
     * 根据教师id和课程名称获取课程
     * @param teacherId
     * @param courseName
     * @return
     */
    @Select({"SELECT * FROM course WHERE course_name=#{courseName} AND teacher_id=#{teacherId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "teacherId", column = "teacher_id",javaType = BigInteger.class),
            @Result(property = "courseName",column = "course_name",javaType = String.class),
            @Result(property = "introduction",column = "introduction",javaType = String.class),
            @Result(property = "presentationPercentage",column = "presentation_percentage",javaType = Integer.class ),
            @Result(property = "questionPercentage",column = "question_percentage",javaType = Integer.class ),
            @Result(property = "reportPercentage",column = "report_percentage",javaType = Integer.class ),
            @Result(property = "teamStartTime",column = "team_start_time",javaType = Timestamp.class ),
            @Result(property = "teamEndTime",column = "team_end_time",javaType = Timestamp.class ),
            @Result(property = "teamMainCourseId", column = "team_main_course_id",javaType = BigInteger.class),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id",javaType = BigInteger.class)
    })
    Course getCourseByteacherIdAndcourseName(@Param("teacherId")BigInteger teacherId, @Param("courseName")String courseName);

    /**
     * 根据课程id获取课程
     * @param courseId
     * @return
     */
    @Select({"SELECT * FROM course WHERE id=#{courseId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "teacherId", column = "teacher_id",javaType = BigInteger.class),
            @Result(property = "courseName",column = "course_name",javaType = String.class),
            @Result(property = "introduction",column = "introduction",javaType = String.class),
            @Result(property = "presentationPercentage",column = "presentation_percentage",javaType = Integer.class ),
            @Result(property = "questionPercentage",column = "question_percentage",javaType = Integer.class ),
            @Result(property = "reportPercentage",column = "report_percentage",javaType = Integer.class ),
            @Result(property = "teamStartTime",column = "team_start_time",javaType = Timestamp.class ),
            @Result(property = "teamEndTime",column = "team_end_time",javaType = Timestamp.class ),
            @Result(property = "teamMainCourseId", column = "team_main_course_id",javaType = BigInteger.class),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id",javaType = BigInteger.class)
    })
    Course getCourseById(@Param("courseId")BigInteger courseId);


    /**
     *获取
     * @param teacherId
     * @return
     */
    @Select({"SELECT * FROM course WHERE  teacher_id=#{teacherId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "teacherId", column = "teacher_id",javaType = BigInteger.class),
            @Result(property = "courseName",column = "course_name",javaType = String.class),
            @Result(property = "introduction",column = "introduction",javaType = String.class),
            @Result(property = "presentationPercentage",column = "presentation_percentage",javaType = Integer.class ),
            @Result(property = "questionPercentage",column = "question_percentage",javaType = Integer.class ),
            @Result(property = "reportPercentage",column = "report_percentage",javaType = Integer.class ),
            @Result(property = "teamStartTime",column = "team_start_time",javaType = Timestamp.class ),
            @Result(property = "teamEndTime",column = "team_end_time",javaType = Timestamp.class ),
            @Result(property = "teamMainCourseId", column = "team_main_course_id",javaType = BigInteger.class),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id",javaType = BigInteger.class)
    })
    List<Course> getCourseByteacherId(@Param("teacherId")BigInteger teacherId);

    /**
     * 根据教师id获取课程
     * @param courseId
     */
    @Delete({"DELETE FROM course WHERE id=#{courseId}"})
    void deleteCourseById(@Param("courseId")BigInteger courseId);


    /**
     * 根据课程id修改主课程id
     * @param mainCourseId
     * @param subCourseId
     */
    @Update({"UPDATE course set team_main_course_id=#{mainCourseId} WHERE id=#{subCourseId}"})
    void setTeamMainCourseIdById(@Param("mainCourseId")BigInteger mainCourseId,@Param("subCourseId")BigInteger subCourseId);


    /**
     * 获取所有课程
     * @return
     */
    @Select({"SELECT * FROM course "})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "teacherId", column = "teacher_id",javaType = BigInteger.class),
            @Result(property = "courseName",column = "course_name",javaType = String.class),
            @Result(property = "introduction",column = "introduction",javaType = String.class),
            @Result(property = "presentationPercentage",column = "presentation_percentage",javaType = Integer.class ),
            @Result(property = "questionPercentage",column = "question_percentage",javaType = Integer.class ),
            @Result(property = "reportPercentage",column = "report_percentage",javaType = Integer.class ),
            @Result(property = "teamStartTime",column = "team_start_time",javaType = Timestamp.class ),
            @Result(property = "teamEndTime",column = "team_end_time",javaType = Timestamp.class ),
            @Result(property = "teamMainCourseId", column = "team_main_course_id",javaType = BigInteger.class),
            @Result(property = "seminarMainCourseId", column = "seminar_main_course_id",javaType = BigInteger.class)
    })
    List<Course> getAllCourse();

}
