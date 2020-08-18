package com.example.cms.mapper;

import com.example.cms.entity.Seminar;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
/**
 * @author zyy
 * @date 2018/12/13
 */
public interface ConflictCourseStrategyMapper {

    /**
     * 插入冲突课程
     * @param conflictCourseId
     * @param courseId
     */
    @Insert({"INSERT INTO conflict_course_strategy(id,course_id) VALUES(#{conflictCourseId},#{courseId})" })
    void createConflictCourseStrategy(@Param("conflictCourseId") BigInteger conflictCourseId,@Param("courseId") BigInteger courseId);


    /**
     * 获取冲突课程id
     * @param courseId
     * @return
     */
    @Select({"SELECT id FROM conflict_course_strategy WHERE course_id=#{courseId} "})
    List<BigInteger> getIdByConflictCourseId(@Param("courseId")BigInteger courseId);

    /**
     * 获取最大id
     * @return
     */
    @Select({"SELECT MAX(id) FROM conflict_course_strategy"})
    BigInteger getMaxIdOfConflictCourse();

    /**
     * 根据id获取客课程id
     * @param id
     * @return
     */
    @Select({"SELECT course_id FROM conflict_course_strategy WHERE id=#{id} "})
    List<BigInteger> getCourseIdById(@Param("id")BigInteger id);
}
