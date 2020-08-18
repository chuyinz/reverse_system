package com.example.cms.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
/**
 * @author xzy
 * @date 2018/12/13
 */
@Mapper
@Component
public interface KlassStudentMapper {
    /**
     * 查看
     * @param studentId
     * @return 返回队伍id
     */
    @Select({"SELECT klass_id  FROM klass_student WHERE student_id= #{studentId}"})
    List<BigInteger> getKlassIdByStuId(@Param("studentId") BigInteger studentId);


    /**
     * 通过学生和课程id获取班级id
     * @param studentId
     * @param courseId
     * @return
     */
    @Select({"SELECT klass_id  FROM klass_student WHERE student_id= #{studentId} and course_id= #{courseId}"})
    BigInteger getKlassIdByStuIdAndCourseId(@Param("studentId") BigInteger studentId,@Param("courseId") BigInteger courseId);

    /**
     * 创建
     * @param studentId
     * @param klassId
     * @param courseId
     * @param teamId
     */
    @Insert({"INSERT INTO klass_student(klass_id,student_id,course_id,team_id) VALUES(#{klassId},#{studentId},#{courseId},#{teamId})"})
    void createKlassStudent(@Param("studentId") BigInteger studentId, @Param("klassId")BigInteger klassId,@Param("courseId")BigInteger courseId,@Param("teamId")BigInteger teamId);

    /**
     * 通过课程和学生id更改小组id
     * @param teamId
     * @param couseId
     * @param studentId
     */
    @Update({"UPDATE klass_student SET team_id=#{teamId} WHERE course_id=#{courseId} and student_id= #{studentId}"})
    void setTeamIdByCourseIdAndStudentId(@Param("teamId")BigInteger teamId,@Param("courseId") BigInteger couseId,@Param("studentId") BigInteger studentId);


    /**
     * 根据课程id获得学生id
     * @param courseId
     * @return
     */
    @Select({"SELECT student_id  FROM klass_student WHERE course_id= #{courseId}"})
    List<BigInteger> getStuIdByCourseId(@Param("courseId") BigInteger courseId);

    /**
     * 通过课程id和小组id获得学生id
     * @param courseId
     * @param teamId
     * @return
     */
    @Select({"SELECT student_id  FROM klass_student WHERE course_id= #{courseId} and team_id= #{teamId}"})
    List<BigInteger> getStuIdByCourseIdAndTeamId(@Param("courseId")BigInteger courseId,@Param("teamId")BigInteger teamId);


    /**
     * 查看
     * @param studentId
     * @param klassId
     * @return 返回队伍id
     */
    @Select({"SELECT team_id FROM klass_student WHERE student_id= #{studentId} AND klass_id= #{klassId}"})
    BigInteger getTeamIdBySK(@Param("studentId") BigInteger studentId, @Param("klassId")BigInteger klassId);

    /**
     * 查看
     * @param studentId
     * @param courseId
     * @return 返回队伍id
     */
    @Select({"SELECT team_id FROM klass_student WHERE student_id= #{studentId} AND course_id= #{courseId}"})
    BigInteger getTeamIdBySC(@Param("studentId") BigInteger studentId, @Param("courseId")BigInteger courseId);

    /**
     * 通过学生id获得课程id
     * @param studentId
     * @return
     */
    @Select({"SELECT course_id FROM klass_student WHERE student_id= #{studentId} "})
    List<BigInteger> getCourseIdByStuId(@Param("studentId") BigInteger studentId);

    /**
     * 更改
     * @param klassId
     * @param studentId
     * @param teamId
     */
    @Update({"UPDATE klass_student SET team_id=#{teamId} WHERE klass_id=#{klassId} AND student_id=#{studentId}"})
    void updateKlassStudent(@Param("klassId") BigInteger  klassId, @Param("studentId") BigInteger
            studentId,@Param("teamId") BigInteger teamId);

    /**
     * 删除
     * @param teamId
     */
    @Update({"UPDATE klass_student SET team_id=null WHERE team_id=#{teamId}"})
    void deleteklassStudentTeam(@Param("teamId")BigInteger teamId);


    /**
     * 添加小组成员
     * @param teamId
     * @param studentId
     * @param klassId
     */
    @Update({"UPDATE klass_student SET team_id=#{teamId} WHERE student_id=#{studentId} AND klass_id=#{klassId}"})
    void addTeamMember(@Param("teamId")BigInteger teamId,@Param("studentId")BigInteger studentId,
                       @Param("klassId")BigInteger klassId);


    /**
     * 删除小组成员
     * @param teamId
     * @param studentId
     */
    @Update({"UPDATE klass_student SET team_id=null WHERE team_id=#{teamId} AND student_id=#{studentId}"})
    void deleteTeamMember(@Param("teamId")BigInteger teamId,@Param("studentId")BigInteger studentId);

    /**
     * 查看学生是否在该课程
     * @param studentId
     * @param courseId
     * @return
     */
    @Select({"SELECT student_id FROM klass_student WHERE student_id=#{studentId} AND course_id=#{courseId}"})
    BigInteger checkCourseId(@Param("studentId")BigInteger studentId,@Param("courseId")BigInteger courseId);


}
