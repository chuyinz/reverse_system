package com.example.cms.mapper;

import com.example.cms.entity.CourseMemberLimitStrategy;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;
/**
 * @author zyy szw
 * @date 2018/12/13
 */
public interface CourseMemberLimitStrategyMapper {

    /**
     * 根据课程人数限制获取id
     * @param id
     * @param min
     * @param max
     * @return
     */
    @Select({"SELECT id FROM course_member_limit_strategy WHERE course_id=#{id} and min_member=#{min} and max_member=#{max} "})
    BigInteger getIdByCourseMemberLimit(@Param("id") BigInteger id,@Param("min")Integer min,@Param("max")Integer max);


    /**
     * 限制组队选课的人数上下限
     * @param id 课程id
     * @param min 队伍中选该课程最少人数
     * @param max 队伍中选该课程最多人数
     */
    @Insert({"INSERT INTO course_member_limit_strategy(course_id,min_member,max_member) VALUES(#{id},#{min},#{max})" })
    void createCourseMemberLimit(@Param("id") BigInteger id,@Param("min")Integer min,@Param("max")Integer max);


    /**
     * 获取某课程组队人数下限
     * @param courseId 课程id
     * @return 人数下限
     */
    @Select({"SELECT min_member FROM course_member_limit_strategy WHERE course_id=#{courseId}"})
    Integer getMinMember(@Param("courseId")BigInteger courseId);


    /**
     * 获取某课程组队人数上限
     * @param courseId 课程id
     * @return 人数上限
     */
    @Select({"SELECT max_member FROM course_member_limit_strategy WHERE course_id=#{courseId}"})
    Integer getMaxMember(@Param("courseId")BigInteger courseId);


    /**
     * 通过id获取课程人数限制
     * @param id
     * @return
     */
    @Select({"SELECT * FROM course_member_limit_strategy WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "courseId", column = "course_id",javaType = BigInteger.class),
            @Result(property = "minMember",column = "min_member",javaType = Integer.class),
            @Result(property = "maxMember",column = "max_member",javaType = Integer.class),

    })
    CourseMemberLimitStrategy getById(@Param("id")BigInteger id);

}
