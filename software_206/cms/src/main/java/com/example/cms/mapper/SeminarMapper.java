package com.example.cms.mapper;

import com.example.cms.entity.Klass;
import com.example.cms.entity.Seminar;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author xzy
 * @date 2018/12/13
 */
public interface SeminarMapper {
    /**
     *创建新讨论课
     * @param seminar 讨论课对象
     * @return id
     */
    @Insert({"INSERT INTO seminar(course_id,round_id,seminar_name,introduction,max_team,is_visible,seminar_serial,enroll_start_time,enroll_end_time) VALUES(#{courseId},#{roundId},#{seminarName},#{introduction},#{maxTeam},#{isVisible},#{serial},#{enrollStartTime},#{enrollEndTime})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS id FROM seminar", keyProperty = "id", before = false, resultType = BigInteger.class)
    BigInteger createSeminar(Seminar seminar);

    /**
     *修改新讨论课
     * @param seminar 讨论课对象
     * @return id
     */
    @Update({"UPDATE seminar SET course_id = #{courseId},round_id=#{roundId},seminar_name=#{seminarName},introduction=#{introduction},max_team=#{maxTeam},is_visible=#{isVisible},seminar_serial=#{serial},enroll_start_time=#{enrollStartTime},enroll_end_time=#{enrollEndTime} WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "courseId", column = "course_id",javaType = BigInteger.class),
            @Result(property = "roundId",column = "round_id",javaType = BigInteger.class ),
            @Result(property = "seminarName",column="seminar_name",javaType = String.class),
            @Result(property = "introduction",column="introduction",javaType = String.class),
            @Result(property = "maxTeam",column = "max_team",javaType = Integer.class),
            @Result(property = "isVisible",column = "is_Visible",javaType = Integer.class),
            @Result(property = "serial",column = "seminar_serial",javaType = Integer.class),
            @Result(property = "enrollStartTime",column="enroll_startTime",javaType = Timestamp.class),
            @Result(property = "enrollEndTime",column="enroll_endTime",javaType = Timestamp.class),
    })
    void updateSeminar(Seminar seminar);

    /**
     *根据id获取讨论课
     * @param seminarId 讨论课id
     * @return 讨论课对象
     */
    @Select({"SELECT * FROM seminar WHERE id = #{seminarId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "courseId", column = "course_id",javaType = BigInteger.class),
            @Result(property = "roundId",column = "round_id",javaType = BigInteger.class ),
            @Result(property = "seminarName",column="seminar_name",javaType = String.class),
            @Result(property = "introduction",column="introduction",javaType = String.class),
            @Result(property = "maxTeam",column = "max_team",javaType = Integer.class),
            @Result(property = "isVisible",column = "is_Visible",javaType = Integer.class),
            @Result(property = "serial",column = "seminar_serial",javaType = Integer.class),
            @Result(property = "enrollStartTime",column="enroll_startTime",javaType = Timestamp.class),
            @Result(property = "enrollEndTime",column="enroll_endTime",javaType = Timestamp.class),
    })
    Seminar getById(@Param("seminarId") BigInteger seminarId);

    /**
     *根据id获取讨论课
     * @param seminar 讨论课
     * @return id
     */
    @Select({"SELECT id FROM seminar WHERE course_id = #{courseId} AND round_id=#{roundId} And seminar_serial=#{serial}"})
    BigInteger getId(Seminar seminar);

    @Delete({"DELETE FROM seminar where course_id=#{courseId}"})
    /**
     * 根据课程课程删除讨论课
     * @param courseId:课程id
     *
     */
    public void deleteSeminarByCourse(BigInteger courseId);

    /**
     * 根据课程课程删除讨论课
     * @param id 课程id
     *
     */
    @Delete({"DELETE FROM seminar where id=#{id}"})
    public void deleteSeminarById(@Param("id") BigInteger id);


    /**
     *根据round获取讨论课
     * @param roundId 轮次id
     * @return 讨论课对象list
     */
    @Select({"SELECT * FROM seminar WHERE round_id = #{roundId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "courseId", column = "course_id",javaType = BigInteger.class),
            @Result(property = "roundId",column = "round_id",javaType = BigInteger.class ),
            @Result(property = "seminarName",column="seminar_name",javaType = String.class),
            @Result(property = "introduction",column="introduction",javaType = String.class),
            @Result(property = "maxTeam",column = "max_team",javaType = Integer.class),
            @Result(property = "isVisible",column = "is_Visible",javaType = Integer.class),
            @Result(property = "serial",column = "seminar_serial",javaType = Integer.class),
            @Result(property = "enrollStartTime",column="enroll_startTime",javaType = Timestamp.class),
            @Result(property = "enrollEndTime",column="enroll_endTime",javaType = Timestamp.class),
    })
    List<Seminar> getAllSeminar(@Param("roundId") BigInteger roundId);

    /**
     *根据round获取讨论课id
     * @param roundId 轮次id
     * @return 讨论课对象list
     */
    @Select({"SELECT id FROM seminar WHERE round_id = #{roundId}"})

    List<BigInteger> getAllSeminarId(@Param("roundId") BigInteger roundId);
}
