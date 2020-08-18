package com.example.cms.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zyy
 * @date 2018/12/13
 */
public interface TeamStudentMapper {
    /**
     *创建
     * @param teamId
     * @param studentId
     */
    @Insert({"INSERT INTO team_student VALUES(#{teamId},#{studentId})"})
    void createTeamStudent(@Param("teamId")BigInteger teamId,@Param("studentId")BigInteger studentId);


    /**
     *获取
     * @param studentId
     * @return
     */
    @Select({"SELECT team_id FROM team_student WHERE student_id=#{studentId}"})
    BigInteger getTeamIdByStudentId(@Param("studentId")BigInteger studentId);

    /**
     *获取
     * @param teamId
     * @return
     */
    @Select({"SELECT student_id FROM team_student WHERE team_id=#{teamId}"})
    List<BigInteger> getStudentIdByTeamId(@Param("teamId")BigInteger teamId);

    /**
     *删除
     * @param teamId
     */
    @Delete({"DELETE FROM team_student WHERE team_id=#{teamId}"})
    void deleteTeamStudent(@Param("teamId")BigInteger teamId);

    /**
     *添加小组成员
     * @param teamId
     * @param studentId
     */
    @Insert({"INSERT INTO team_student VALUES(#{teamId},#{studentId})"})
    void addTeamMember(@Param("teamId")BigInteger teamId,@Param("studentId")BigInteger studentId);

    /**
     *删除小组成员
     * @param teamId
     * @param studentId
     */
    @Delete({"DELETE FROM team_student WHERE team_id=#{teamId} AND student_id=#{studentId}"})
    void deleteTeamMember(@Param("teamId")BigInteger teamId,@Param("studentId")BigInteger studentId);
}
