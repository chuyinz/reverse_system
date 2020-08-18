package com.example.cms.mapper;

import com.example.cms.vo.TeamValidRequestVO;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zyy
 * @date 2018/12/13
 */
public interface TeamValidApplicationMapper {
    /**
     *获取
     * @param teacherId
     * @param status
     * @return
     */
   @Select({"SELECT id,reason FROM team_valid_application WHERE teacher_id=#{teacherId} and status=#{status}"})
   @Results({
           @Result(property = "teamValidId", column = "id",javaType = BigInteger.class),
           @Result(property = "reason", column = "reason",javaType = String.class),

   })
    List<TeamValidRequestVO> getTeamValidByTeacherIdAndStatus(@Param("teacherId") BigInteger teacherId, @Param("status")Integer status);

    /**
     *获取
     * @param teacherId
     * @param status
     * @return
     */
    @Select({"SELECT team_id FROM team_valid_application WHERE teacher_id=#{teacherId} and status=#{status}"})
    List<BigInteger> getTeamIdByTeacherIdAndStatus(@Param("teacherId") BigInteger teacherId, @Param("status")Integer status);


    /**
     * 修改
     * @param teamvalidId
     * @param status
     */
    @Update({"UPDATE team_valid_application SET status=#{status} WHERE id=#{teamvalidId} "})
    void setStatusById(@Param("teamvalidId") BigInteger teamvalidId,@Param("status")Integer status);


    /**
     *插入
     * @param teamId
     * @param teacherId
     * @param reason
     */
    @Insert({"INSERT INTO team_valid_application(team_id,teacher_id,reason,status)" +
            " VALUES(#{teamId},#{teacherId},#{reason},null)"})
    void createRequest(@Param("teamId")BigInteger teamId,@Param("teacherId")BigInteger teacherId,
                       @Param("reason")String reason);



    /**
     *获取
     * @param teacherId
     * @return
     */
    @Select({"SELECT team_id FROM team_valid_application WHERE teacher_id=#{teacherId} and status is null"})
    List<BigInteger> getTeamIdByTeacherIdAndNull(@Param("teacherId") BigInteger teacherId);



    /**
     *获取
     * @param teacherId
     * @return
     */
    @Select({"SELECT id,reason FROM team_valid_application WHERE teacher_id=#{teacherId} and status is null"})
    @Results({
            @Result(property = "teamValidId", column = "id",javaType = BigInteger.class),
            @Result(property = "reason", column = "reason",javaType = String.class),

    })
    List<TeamValidRequestVO> getTeamValidByTeacherIdAndNull(@Param("teacherId") BigInteger teacherId);

}
