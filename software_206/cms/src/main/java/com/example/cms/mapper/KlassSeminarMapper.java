package com.example.cms.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
/**
 * @author xzy
 * @date 2018/12/13
 */


public interface KlassSeminarMapper {

    /**
     * 查找课程id
     * @param id KSid
     * @return 返回课程id
     */
    @Select({"SELECT klass_id FROM klass_seminar WHERE id= #{id}"})
    BigInteger getKlassIdById(@Param("id") BigInteger id);

    /**
     * 查找课程id
     * @param seminarId 讨论课id
     * @return 返回课程id
     */
    @Select({"SELECT klass_id FROM klass_seminar WHERE seminar_id= #{seminarId}"})
    List<BigInteger> getKlassIdBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * 查找课程id
     * @param seminarId 讨论课id
     * @return 返回课程id
     */
    @Select({"SELECT id FROM klass_seminar WHERE seminar_id = #{seminarId}"})
    List<BigInteger> getIdBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * 查找id
     * @param klassId
     * @param seminarId
     * @return
     */
    @Select({"SELECT id FROM klass_seminar WHERE klass_id= #{klassId} And seminar_id= #{seminarId}"})
    BigInteger getIdByKlassIdAndSeminarId(@Param("klassId") BigInteger klassId, @Param("seminarId") BigInteger seminarId);

    /**
     * 查找id
     * @param id
     * @return
     */
    @Delete({"DELETE FROM klass_seminar WHERE id=#{id}"})
    void deleteById(@Param("id") BigInteger id);

    /**
     * 创建关系
     * @param klassId
     * @param seminarId
     * @param status
     * @return
     */
    @Insert({"INSERT INTO klass_seminar(klass_id,seminar_id,status) VALUES (#{klassId},#{seminarId},#{status})"})
    void createKlassSeminar(BigInteger klassId, BigInteger seminarId, Integer status);

    /**
     * 修改DDL
     * @param id
     * @param reportDDL
     * @return
     */
    @Update({"UPDATE klass_seminar SET report_ddl=#{reportDDL} WHERE id=#{id}"})
    void updateDDLById(@Param("id") BigInteger id,@Param("reportDDL") Timestamp reportDDL);

    /**
     * 修改status
     * @param id
     * @param status
     * @return
     */
    @Update({"UPDATE klass_seminar SET status=#{status} WHERE id=#{id}"})
    void updateStatusById(@Param("id") BigInteger id,@Param("status") Integer status);

    /**
     * 查找讨论课id
     * @param id KSid
     * @return 返回讨论课id
     */
    @Select({"SELECT seminar_id FROM klass_seminar WHERE id= #{id}"})
    BigInteger getSeminarIdById(@Param("id") BigInteger id);

    /**
     * 查找课程reportDdl
     * @param id KSid
     * @return 返回课程id
     */
    @Select({"SELECT report_ddl FROM klass_seminar WHERE id= #{id}"})
    Timestamp getReportDdlById(@Param("id") BigInteger id);

    /**
     * 查找课程status
     * @param id KSid
     * @return 返回课程id
     */
    @Select({"SELECT status FROM klass_seminar WHERE id= #{id}"})
    Integer getStatusById(@Param("id") BigInteger id);




}
