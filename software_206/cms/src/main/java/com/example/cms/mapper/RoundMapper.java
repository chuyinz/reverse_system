package com.example.cms.mapper;

import com.example.cms.entity.Round;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xzy
 * @date 2018/12/13
 */
public interface RoundMapper {

    /**
     * 查找round
     *
     * @param courseId 课程id
     * @param serial   轮次序号
     * @return round对象
     */
    @Select({"SELECT * FROM round WHERE course_id=#{courseId} AND round_serial=#{serial}"})
    public Round getRoundByCourseIdAndSerial(@Param("courseId") BigInteger courseId, @Param("serial") Integer serial);

    /**
     * 创建round
     *
     * @param
     * @param round 对象
     * @return id
     */
    @Insert({"INSERT INTO round(course_id, round_serial, presentation_score_method, report_score_method, question_score_method)" +
            "VALUES (#{courseId}, #{serial}, #{presentationScoreMethod}, #{reportScoreMethod},#{questionScoreMethod})"})
    void create(Round round);

    /**
     * 获取round id
     * @param round 实体
     * @return id
     */
    @Select({"SELECT id FROM round WHERE course_id = #{courseId} AND round_serial = #{serial}"})
    BigInteger getId(Round round);

    /**
     * 查找round
     * @param id
     * @return round对象
     */
    @Select({"SELECT * FROM round WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "serial", column = "round_serial"),
            @Result(property = "presentationScoreMethod", column = "presentation_score_method"),
            @Result(property = "reportScoreMethod", column = "report_score_method"),
            @Result(property = "questionScoreMethod", column = "question_score_method"),

    })
    Round getRoundById(@Param("id") BigInteger id);

    /**
     *a
     * @param round
     */
    @Update({"UPDATE round SET presentation_score_method=#{presentationScoreMethod},report_score_method=#{reportScoreMethod},question_score_method=#{questionScoreMethod} WHERE id=#{id}"})
    void update(Round round);


    /**
     * 查找roundList
     * @param courseId
     * @return round对象
     */
    @Select({"SELECT * FROM round WHERE course_id=#{courseId}"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "serial", column = "round_serial"),
            @Result(property = "presentationScoreMethod", column = "presentation_score_method"),
            @Result(property = "reportScoreMethod", column = "report_score_method"),
            @Result(property = "questionScoreMethod", column = "question_score_method"),

    })
    List<Round> getRoundByCourseId(@Param("courseId") BigInteger courseId);
}
