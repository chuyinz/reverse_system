package com.example.cms.mapper;

import com.example.cms.entity.*;
import com.example.cms.vo.Team1VO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author XIE
 * @date 2018/12/14
 */
public interface TeamMapper {


    /**
     *获取
     * @param studentId
     * @param courseId
     * @return
     */
    @Select({"SELECT team_id FROM klass_student where student_id=#{studentId} AND course_id=#{courseId}"})
    public BigInteger getTeamIdByStuIdAndCourseId(@Param("studentId")BigInteger studentId,@Param("courseId")BigInteger courseId);



    /**
     * 查询学生所属队伍
     * @param studentId
     * @return
     */
    @Select({"SELECT team_id FROM team_student where student_id=#{studentId}"})
    public List<BigInteger> getTeamIdByStuId(@Param("studentId")BigInteger studentId);

    /**
     *获取
     * @param teamId
     * @return
     */
    @Select({"SELECT student_id FROM team_student where team_id=#{teamId}"})
    public List<BigInteger> getStuIdByTeamId(@Param("teamId")BigInteger teamId);

    /**
     * 通过id获取队伍信息
     * @param teamId 队伍id
     * @return 队伍信息
     */
    @Select({"SELECT * FROM team where id=#{teamId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "klassId", column = "klass_id",javaType = BigInteger.class),
            @Result(property = "courseId",column = "course_id",javaType = BigInteger.class ),
            @Result(property = "leaderId",column = "leader_id",javaType = BigInteger.class),
            @Result(property = "teamName",column = "team_name",javaType = String.class),
            @Result(property = "teamSerial",column="team_serial",javaType = int.class),
            @Result(property = "klassSerial",column="klass_serial",javaType = int.class),
            @Result(property = "isStatus",column="status",javaType = int.class)
    })
    public Team getTeamById(@Param("teamId")BigInteger teamId);


    /**
     *删除
     * @param courseId
     */
    @Delete({"DELETE FROM team WHERE course_id=#{courseId}"})
    void deleteTeamByCourseId(@Param("courseId")BigInteger courseId);



    /**
     * 新建队伍记录
     * @param courseId 课程id
     * @param klassId 班级id
     * @param leaderId 组长id
     * @param teamName 队伍名称
     * @param teamSerial 队伍序号
     * @param klassSerial 班级序号
     */
    @Insert({"INSERT INTO team(klass_id,course_id,leader_id,team_name,team_serial,klass_serial,status) VALUES(#{klassId},#{courseId}," +
            "#{leaderId},#{teamName},#{teamSerial},#{klassSerial},1)"})
    void createTeam( @Param("klassId") BigInteger klassId,@Param("courseId") BigInteger courseId, @Param("leaderId") BigInteger leaderId,
                     @Param("teamName") String teamName, @Param("teamSerial") Integer teamSerial, @Param("klassSerial") Integer klassSerial);
    // void createTeam( BigInteger klassId,BigInteger courseId,  BigInteger leaderId, String teamName,Integer teamSerial,Integer klassSerial);



    /**
     * 获得同班级序号下的队伍序号最大值
     * @param klassId 班级id
     * @return 队伍序号最大值
     */
    @Select({"SELECT MAX(team_serial) FROM team WHERE klass_id=#{klassId}"})
    Integer getTeamSerialByKlassId(@Param("klassId")BigInteger klassId);


    /**
     * 通过班级序号和小组序号获得小组id
     * @param klassSerial 班级序号
     * @param teamSerial 小组序号
     * @return 小组id
     */
    @Select({"SELECT id FROM team WHERE klass_serial=#{klassSerial} AND team_serial=#{teamSerial}"})
    BigInteger getTeamId(@Param("klassSerial")int klassSerial,@Param("teamSerial")int teamSerial);

    /**
     * 增加klass_team 表数据
     * @param klassId 班级id
     * @param teamId 队伍id
     */
    @Insert({"INSERT INTO klass_team VALUES(#{klassId},#{teamId})"})
    void createKlassTeam(@Param("klassId")BigInteger klassId,@Param("teamId")BigInteger teamId);



    /**
     * 改变队伍状态
     * @param teamId 队伍id
     * @param status 状态
     */
    @Update({"UPDATE team SET status=#{status} WHERE id=#{teamId}"})
    void changeTeamStatus(@Param("teamId")BigInteger teamId,@Param("status")Integer status);


    /**
     * 通过id获取队伍信息
     * @param teamId 队伍id
     * @return 队伍信息
     */
    @Select({"SELECT * FROM team WHERE id=#{teamId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "klassId", column = "klass_id",javaType = BigInteger.class),
            @Result(property = "courseId", column = "course_id",javaType = BigInteger.class),
            @Result(property = "leaderId", column = "leader_id",javaType = BigInteger.class),
            @Result(property = "teamName", column = "team_name",javaType = String.class),
            @Result(property = "teamSerial", column = "team_serial",javaType = Integer.class),
            @Result(property = "klassSerial", column = "klass_serial",javaType = Integer.class),
            @Result(property = "status", column = "status",javaType = Integer.class)
    })
    Team1VO getTeam1VOById(@Param("teamId")BigInteger teamId);



    /**
     * 获得策略名字
     * @param courseId
     * @return
     */
    @Select({"SELECT * FROM team_strategy WHERE course_id=#{courseId}"})
    @Results({
            @Result(property = "courseId",column = "course_id",javaType = BigInteger.class),
            @Result(property = "strategySerial", column = "strategy_serial",javaType = Integer.class),
            @Result(property = "strategyName", column = "strategy_name",javaType = String.class),
            @Result(property = "strategyId",column = "strategy_id",javaType = BigInteger.class)
    })
    List<TeamStrategy> getStrategyByCourseId(@Param("courseId")BigInteger courseId);


    /**
     * 根据id获得member_limit_strategy
     * @param strategyId id
     * @return member_limit_strategy
     */
    @Select({"SELECT * FROM member_limit_strategy WHERE id=#{strategyId}"})
    MemberLimitStrategy getMemberLimitStrategy(@Param("strategyId")BigInteger strategyId);


    /**
     * 根据id获得course_member_limit_strategy
     * @param strategyId
     * @return course_member_limit_strategy
     */
    @Select({"SELECT * FROM course_member_limit_strategy WHERE id=#{strategyId}"})
    CourseMemberLimitStrategy getCourseMemberLimitStrategy(@Param("strategyId")BigInteger strategyId);

    /**
     * 通过组长id获得teamID
     * @param leaderId 组长id
     * @return teamid
     */
    @Select({"SELECT id FROM team WHERE leader_id=#{leaderId}"})
    BigInteger getTeamIdByLeaderId(@Param("leaderId")BigInteger leaderId);


    /**
     *获取
     * @param strategyId
     * @return
     */
    @Select({"SELECT * FROM team_and_strategy WHERE id=#{strategyId}"})
    public List<TeamAndOrStrategy> getAndStrategy(@Param("strategyId")BigInteger strategyId);

    /**
     * 获取orstrategy
     * @param strategyId id
     * @return
     */
    @Select({"SELECT * FROM team_or_strategy WHERE id=#{strategyId}"})
    List<TeamAndOrStrategy> getOrStrategy(@Param("strategyId")BigInteger strategyId);

    /**
     * 获取
     * @param courseId
     * @return
     */
    public List<TeamStrategy> getStrategyByCourseId(@Param("courseId")Long courseId);

    /**
     *获取
     * @param courseId
     * @param teamId
     * @return
     */
    @Select({"SELECT team_student.student_id FROM team_student LEFT JOIN klass_student ON team_student.student_id" +
            "=klass_student.student_id WHERE klass_student.course_id=#{courseId} AND team_student.team_id=#{teamId}"})
    List<BigInteger> getMemberIdByCourseId(@Param("courseId")BigInteger courseId,@Param("teamId")BigInteger teamId);


    /**
     * 根据策略id获取课程id'
     * @param strategyId 策略id
     * @return 课程id
     */
    @Select({"SELECT course_id FROM conflict_course_strategy WHERE id=#{strategyId}"})
    List<BigInteger> getConflictCourseId(@Param("strategyId")BigInteger strategyId);

    /**
     * 删除队伍记录
     * @param studentId 学生id
     * @param courseId 课程id
     */
    @Delete({"DELETE FROM team WHERE student_id=#{id} AND course_id=#{courseId}"})
    public void delete(@Param("studentId") String studentId,@Param("courseId") String courseId);

    /**
     * 删除
     * @param teamId
     */
    @Delete({"DELETE FROM team WHERE id=#{teamId}"})
    void deleteTeam(@Param("teamId")BigInteger teamId);


    /**
     * 查询队伍队长
     * @param teamId 队伍id
     * @return BigInteger 组长id
     */
    @Select({"SELECT leader_id FROM klass_student WHERE team_id=#{teamId}"})
    public BigInteger findTeamLeader(@Param("teamId") BigInteger teamId);

    /**
     *获取
     * @param mainCourseId
     * @return
     */
    @Select({"SELECT team_id FROM klass_student WHERE course_id=#{mainCourseId}"})
    List<BigInteger> getTeamIdByCourseId(@Param("mainCourseId")BigInteger mainCourseId);

    /**
     *获取
     * @param courseId
     * @return
     */
    @Select({"SELECT id FROM team WHERE course_id=#{courseId}"})
    List<BigInteger> getIdByCourseId(@Param("courseId")BigInteger courseId);
}
