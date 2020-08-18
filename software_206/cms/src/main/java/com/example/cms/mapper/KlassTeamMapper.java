package com.example.cms.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zyy
 * @date 2018-12-25
 */
public interface KlassTeamMapper {

    /**
     * 根据班级id获得小组id
     * @param klassId
     * @return
     */
    @Select({"SELECT team_id  FROM klass_team WHERE klass_id= #{klassId}"})
    public List<BigInteger> getTeamIdByKlassId(@Param("klassId") BigInteger klassId);


    /**
     *根据班级id修改小组id
     * @param teamId
     * @param klassId
     */
    @Update({"UPDATE klass_team SET team_id=#{teamId} WHERE klass_id=#{klassId}"})
    void setTeamIdByklassId(@Param("teamId") BigInteger teamId,@Param("klassId") BigInteger klassId);

    /**
     *获取班级id
     * @param teamId
     * @return
     */
    @Select({"SELECT klass_id FROM klass_team WHERE team_id=#{teamId}"})
    List<BigInteger> getKlassIds(@Param("teamId")BigInteger teamId);


    /**
     *删除
     * @param teamId
     */
    @Delete({"DELETE FROM klass_team WHERE team_id=#{teamId}"})
    void deleteKlassTeam(@Param("teamId")BigInteger teamId);
}
