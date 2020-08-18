package com.example.cms.mapper;

import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

public interface GetListMapper {
    /**
     *得到教师课程
     * @param teacherId
     * @return
     */
    @Select({"SELECT id FROM course WHERE teacher_id=#{teacherId}"})
    List<BigInteger> getCourseIdList(BigInteger teacherId);

    /**
     *得到教师课程
     * @param courseId
     * @return
     */
    @Select({"SELECT id FROM klass WHERE course_id=#{courseId}"})
    List<BigInteger> getKlassIdList(BigInteger courseId);

    /**
     *得到教师课程
     * @param klassId
     * @return
     */
    @Select({"SELECT id FROM team WHERE klass_id=#{klassId}"})
    List<BigInteger> getTeamIdList(BigInteger klassId);

    /**
     *得到klass_seminar
     * @param klassId
     * @return
     */
    @Select({"SELECT id FROM klass_seminar WHERE klass_id=#{klassId}"})
    List<BigInteger>getKlassSeminarIdList(BigInteger klassId);

    /**
     *得到RoundId
     * @param courseId
     * @return
     */
    @Select({"SELECT id FROM round WHERE course_id=#{courseId}"})
    List<BigInteger>getRoundIdList(BigInteger courseId);
}
