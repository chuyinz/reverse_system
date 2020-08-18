package com.example.cms.mapper;

import com.example.cms.entity.Attendance;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author xzy
 * @date 2018-12-25
 */
@Mapper
@Component
public interface AttendanceMapper {

    /**
     *通过id获得展示类
     * @param id 展示id
     * @return 实体
     */
    @Select({"SELECT * FROM attendance WHERE id=#{id}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "klassSeminarId", column = "klass_seminar_id",javaType = BigInteger.class),
            @Result(property = "teamId",column = "team_id",javaType = BigInteger.class ),
            @Result(property = "teamOrder",column = "team_order",javaType = Integer.class),
            @Result(property = "isPresent",column = "is_present",javaType = Integer.class),
            @Result(property = "reportName",column="report_name",javaType = String.class),
            @Result(property = "reportUrl",column="report_url",javaType = String.class),
            @Result(property = "pptName",column="ppt_name",javaType = String.class),
            @Result(property = "pptUrl",column="ppt_url",javaType = String.class)
    })
    Attendance getAttendanceById(@Param("id") BigInteger id);

    /**
     *获得正在展示
     * @param kSId 展示id
     * @return 实体
     */
    @Select({"SELECT * FROM attendance WHERE klass_seminar_id=#{klassSeminarId} AND is_present=1"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "klassSeminarId", column = "klass_seminar_id",javaType = BigInteger.class),
            @Result(property = "teamId",column = "team_id",javaType = BigInteger.class ),
            @Result(property = "teamOrder",column = "team_order",javaType = Integer.class),
            @Result(property = "isPresent",column = "is_present",javaType = Integer.class),
            @Result(property = "reportName",column="report_name",javaType = String.class),
            @Result(property = "reportUrl",column="report_url",javaType = String.class),
            @Result(property = "pptName",column="ppt_name",javaType = String.class),
            @Result(property = "pptUrl",column="ppt_url",javaType = String.class)
    })
    Attendance getPresentAttendance(@Param("klassSeminarId") BigInteger kSId);

    /**
     * a
     * @param kSId
     * @param teamId
     * @return
     */
    @Select({"SELECT * FROM attendance WHERE klass_seminar_id=#{klassSeminarId} AND team_id=#{teamId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "klassSeminarId", column = "klass_seminar_id",javaType = BigInteger.class),
            @Result(property = "teamId",column = "team_id",javaType = BigInteger.class ),
            @Result(property = "teamOrder",column = "team_order",javaType = Integer.class),
            @Result(property = "isPresent",column = "is_present",javaType = Integer.class),
            @Result(property = "reportName",column="report_name",javaType = String.class),
            @Result(property = "reportUrl",column="report_url",javaType = String.class),
            @Result(property = "pptName",column="ppt_name",javaType = String.class),
            @Result(property = "pptUrl",column="ppt_url",javaType = String.class)
    })
    Attendance getAttendance(@Param("klassSeminarId") BigInteger kSId, @Param("teamId")BigInteger teamId);

    /**
     *创建讨论课报名
     * @param attendance
     */
    @Insert ({"INSERT INTO attendance(klass_seminar_id,team_id,team_order,is_present,report_name,report_url,ppt_name,ppt_url) VALUES(#{klassSeminarId},#{teamId},#{teamOrder},#{isPresent},#{reportName},#{reportUrl},#{pptName},#{pptUrl})"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "klassSeminarId", column = "klass_seminar_id",javaType = BigInteger.class),
            @Result(property = "teamId",column = "team_id",javaType = BigInteger.class ),
            @Result(property = "teamOrder",column = "team_order",javaType = Integer.class),
            @Result(property = "isPresent",column = "is_present",javaType = Integer.class),
            @Result(property = "reportName",column="report_name",javaType = String.class),
            @Result(property = "reportUrl",column="report_url",javaType = String.class),
            @Result(property = "pptName",column="ppt_name",javaType = String.class),
            @Result(property = "pptUrl",column="ppt_url",javaType = String.class)
    })
    void create(Attendance attendance);

    /**
     *修改team_order
     * @param teamOrder 展示顺序
     * @param id
     */
    @Update({"UPDATE attendance SET team_order=#{teamOrder} WHERE id=#{id}"})
    public void setTeamOrder(@Param("id") BigInteger id,@Param("teamOrder") Integer teamOrder);

    /**
     *根据小组id查看其报名情况
     * @param teamId
     * @return
     */
    @Select({"SELECT * FROM attendance WHERE team_id=#{teamId}"})
    public List<Attendance> getAttendanceByTeamId(BigInteger teamId);

    /**
     *根据id更新书面报告
     * @param id
     * @param url
     * @param name
     */
    @Update({"UPDATE attendance set report_name=#{name},report_url=#{url} WHERE id=#{id}"})
    public void updateReportById(@Param("id") BigInteger id, @Param("url") String url, @Param("name") String name);

    /**
     *根据id更新PPT
     * @param id
     * @param url
     * @param name
     */
    @Update({"UPDATE attendance set ppt_name=#{name},ppt_url=#{url} WHERE id=#{id}"})
    public void updatePptById(@Param("id") BigInteger id, @Param("url") String url, @Param("name") String name);


    /**
     * 取消讨论课报名（根据小组id）
     * @param id:讨论课id
     */
    @Delete({"DELETE FROM attendance where id=#{id}"})
    public void deleteById(@Param("id") BigInteger id);

    /**
     * 删除讨论课时删除讨论课报名（根据小组id）
     * @param kSId:讨论课id
     */
    @Delete({"DELETE FROM attendance where klass_seminar=#{kSId}"})
    public void deleteByKSId(@Param("kSId") BigInteger kSId);

    /**
     *获取讨论课下所有展示
     * @param klassSeminarId 展示id
     * @return 展示list
     */
    @Select({"SELECT * FROM attendance WHERE klass_seminar_id=#{klassSeminarId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "klassSeminarId", column = "klass_seminar_id",javaType = BigInteger.class),
            @Result(property = "teamId",column = "team_id",javaType = BigInteger.class ),
            @Result(property = "teamOrder",column = "team_order",javaType = Integer.class),
            @Result(property = "isPresent",column = "is_present",javaType = Integer.class),
            @Result(property = "reportName",column="report_name",javaType = String.class),
            @Result(property = "reportUrl",column="report_url",javaType = String.class),
            @Result(property = "pptName",column="ppt_name",javaType = String.class),
            @Result(property = "pptUrl",column="ppt_url",javaType = String.class)
    })
    List<Attendance> getAttendanceList(@Param("klassSeminarId")BigInteger klassSeminarId);


    /**
     *获得小组展示
     * @param kSId 展示id
     * @param teamId
     * @return 实体
     */
    @Select({"SELECT * FROM attendance WHERE klass_seminar_id=#{klassSeminarId} AND teamId=#{teamId}"})
    @Results({
            @Result(property = "id", column = "id",javaType = BigInteger.class),
            @Result(property = "klassSeminarId", column = "klass_seminar_id",javaType = BigInteger.class),
            @Result(property = "teamId",column = "team_id",javaType = BigInteger.class ),
            @Result(property = "teamOrder",column = "team_order",javaType = Integer.class),
            @Result(property = "isPresent",column = "is_present",javaType = Integer.class),
            @Result(property = "reportName",column="report_name",javaType = String.class),
            @Result(property = "reportUrl",column="report_url",javaType = String.class),
            @Result(property = "pptName",column="ppt_name",javaType = String.class),
            @Result(property = "pptUrl",column="ppt_url",javaType = String.class)
    })
    Attendance getTeamAttendance(@Param("klassSeminarId") BigInteger kSId, @Param("teamId")BigInteger teamId);

    /**
     *根据id更新
     * @param attendance
     */
    @Update({"UPDATE attendance SET team_order=#{teamOrder},is_present=#{isPresent} WHERE id=#{id}"})
    void update(Attendance attendance);

}
