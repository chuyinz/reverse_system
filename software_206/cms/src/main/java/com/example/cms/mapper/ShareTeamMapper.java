package com.example.cms.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;

import java.math.BigInteger;
import java.util.List;
/**
 * @author zyy
 * @date 2018/12/13
 */
public interface ShareTeamMapper {
    /**
     *获取
     * @param courseId
     * @param status
     * @return
     */
    @Select({"SELECT main_course_id FROM share_team_application where sub_course_id=#{courseId} and status=#{status}"})
    List<BigInteger> getMainIdBySubIdAndStatus(@Param("courseId")BigInteger courseId, @Param("status")Integer status);


    /**
     *获取
     * @param courseId
     * @param status
     * @return
     */
    @Select({"SELECT sub_course_id FROM share_team_application where main_course_id=#{courseId} and status=#{status}"})
    List<BigInteger> getSubIdByMainIdAndStatus(@Param("courseId")BigInteger courseId, @Param("status")Integer status);

    /**
     *获取
     * @param courseId
     * @param status
     * @return
     */
    @Select({"SELECT id FROM share_team_application where main_course_id=#{courseId} and status=#{status}"})
    List<BigInteger> getIdByMainIdAndStatus(@Param("courseId")BigInteger courseId, @Param("status")Integer status);


    /**
     *获取
     * @param courseId
     * @param status
     * @return
     */
    @Select({"SELECT id FROM share_team_application where sub_course_id=#{courseId} and status=#{status}"})
    List<BigInteger> getIdBySubIdAndStatus(@Param("courseId")BigInteger courseId, @Param("status")Integer status);

    /**
     *删除
     * @param teamShareId
     */
    @Delete({"DELETE FROM share_team_application WHERE id=#{teamShareId}"})
    void deleteTeamShareById(@Param("teamShareId")BigInteger teamShareId);

    /**
     *增加
     * @param mainCourseId
     * @param subCourseId
     * @param teacherId
     * @param status
     */
    @Insert({"INSERT INTO share_team_application(main_course_id,sub_course_id,sub_course_teacher_id,status) VALUES(#{mainCourseId},#{subCourseId},#{teacherId},#{status})"})
    void createTeamShareRequest(@Param("mainCourseId") BigInteger mainCourseId,@Param("subCourseId")BigInteger subCourseId,@Param("teacherId")BigInteger teacherId,@Param("status")Integer status);

    /**
     *修改
     * @param teamshareId
     * @param status
     */
    @Update({"UPDATE share_team_application SET status=#{status} WHERE id=#{teamshareId} "})
    void setStatusById(@Param("teamshareId") BigInteger teamshareId,@Param("status")Integer status);

    /**
     *获取
     * @param teamshareId
     * @return
     */
    @Select({"SELECT sub_course_id FROM share_team_application where id=#{teamshareId}"})
    BigInteger getSubIdById(@Param("teamshareId")BigInteger teamshareId);

    /**
     *获取
     * @param teamshareId
     * @return
     */
    @Select({"SELECT main_course_id FROM share_team_application where id=#{teamshareId}"})
    BigInteger getMainIdById(@Param("teamshareId")BigInteger teamshareId);


    /**
     *获取
     * @param courseId
     * @return
     */
    @Select({"SELECT id FROM share_team_application where sub_course_id=#{courseId} and status is null"})
    List<BigInteger>getIdBySubIdAndNull(@Param("courseId")BigInteger courseId);


    /**
     *获取
     * @param courseId
     * @return
     */
    @Select({"SELECT main_course_id FROM share_team_application where sub_course_id=#{courseId} and status is null"})
    List<BigInteger> getMainIdBySubIdAndNull(@Param("courseId")BigInteger courseId);
}
