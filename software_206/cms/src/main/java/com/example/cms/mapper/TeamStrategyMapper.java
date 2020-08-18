package com.example.cms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
/**
 * @author zyy
 * @date 2018/12/13
 */
public interface TeamStrategyMapper {
    /**
     *创建
     * @param courseId
     * @param strategySerial
     * @param name
     * @param strategyId
     */
    @Insert({"INSERT INTO team_strategy(course_id,strategy_serial,strategy_name,strategy_id) VALUES(#{courseId},#{strategySerial},#{name},#{strategyId})" })
    void createTeamStrategy(@Param("courseId") BigInteger courseId,@Param("strategySerial")int strategySerial,@Param("name")String name,@Param("strategyId")BigInteger strategyId);

    /**
     *获取
     * @param courseId
     * @return
     */
    @Select({"SELECT MAX(strategy_serial) FROM team_strategy WHERE course_id=#{courseId}"})
    Integer getMaxIdByCourseId(BigInteger courseId);

}
