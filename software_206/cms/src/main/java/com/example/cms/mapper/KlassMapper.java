package com.example.cms.mapper;

import com.example.cms.entity.Klass;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
/**
 * @author szw zyy
 * @date 2018/12/13
 */
public interface KlassMapper {

    /**
     * 创建班级
     * @param klass
     */
    @Insert({"INSERT INTO klass(course_id,grade,klass_serial,klass_time,klass_location) VALUES(#{courseId},#{grade},#{klassSerial},#{klassTime},#{klassLocation})"})
    public void createKlass(Klass klass);


    /**
     * 根据课程删除班级
     * @param courseId id
     *
     */
    @Delete({"DELETE FROM klass where course_id=#{courseId}"})
    void deleteKlassByCourse(BigInteger courseId);


    /**
     * 获取班级
     * @param klassId id
     * @return
     */
    @Select({"SELECT * FROM klass where id=#{klassId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "courseId", column = "course_id",javaType = BigInteger.class),
            @Result(property = "grade",column = "grade",javaType =Integer.class),
            @Result(property = "klassSerial",column = "klass_serial",javaType =Integer.class),
            @Result(property = "klassTime",column = "klass_time",javaType = String.class ),
            @Result(property = "klassLocation",column = "klass_location",javaType = String.class ),

    })
    Klass getKlassById(@Param("klassId")BigInteger klassId);

    /**
     *根据课程id获取班级
     * @param courseId
     * @return
     */
    @Select({"SELECT * FROM klass where course_id=#{courseId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "courseId", column = "course_id",javaType = BigInteger.class),
            @Result(property = "grade",column = "grade",javaType =Integer.class),
            @Result(property = "klassSerial",column = "klass_serial",javaType =Integer.class),
            @Result(property = "klassTime",column = "klass_time",javaType = String.class ),
            @Result(property = "klassLocation",column = "klass_location",javaType = String.class ),

    })
    List<Klass> getKlassByCourseId(@Param("courseId")BigInteger courseId);



    /**
     * 获得班级序号
     * @param klassId 班级id
     * @param courseId 课程id
     * @return 班级序号
     */
    @Select({"SELECT klass_serial FROM klass WHERE id=#{klassId} AND course_id=#{courseId}"})
    Integer getKlassSerial(@Param("klassId")BigInteger klassId,@Param("courseId")BigInteger courseId);


    /**
     *根据班级id删除班级
     * @param klassId
     */
    @Delete({"DELETE FROM klass WHERE id=#{klassId}"})
    void deleteKlassById(@Param("klassId")BigInteger klassId);
}
