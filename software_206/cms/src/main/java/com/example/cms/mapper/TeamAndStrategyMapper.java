package com.example.cms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
/**
 * @author zyy
 * @date 2018/12/13
 */
public interface TeamAndStrategyMapper {
    /**
     *获取
     * @return
     */
    @Select({"SELECT MAX(id) FROM team_and_strategy"})
    BigInteger getMaxIdOfTeamAndStrategy();

    /**
     *插入
     * @param teamAndStrategyId
     * @param name
     * @param id
     */
    @Insert({"INSERT INTO team_and_strategy(id,strategy_name,strategy_id) VALUES(#{teamAndStrategyId},#{name},#{id})" })
    void createTeamAndStrategy(@Param("teamAndStrategyId") BigInteger teamAndStrategyId, @Param("name") String name,@Param("id") BigInteger id);

    /**
     *获取
     * @param id
     * @param name
     * @return
     */
    @Select({"SELECT strategy_id FROM team_and_strategy where id=#{id} and strategy_name=#{name}"})
    BigInteger getStrategyIdByIdAndName(@Param("id")BigInteger id,@Param("name")String name);
}
