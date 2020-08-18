package com.example.cms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zyy
 * @date 2018/12/13
 */
public interface TeamOrStrategyMapper {
    /**
     *获取
     * @return
     */
    @Select({"SELECT MAX(id) FROM team_or_strategy"})
    BigInteger getMaxIdOfTeamOrStrategy();

    /**
     *创建
     * @param teamOrStrategyId
     * @param name
     * @param id
     */
    @Insert({"INSERT INTO team_or_strategy(id,strategy_name,strategy_id) VALUES(#{teamOrStrategyId},#{name},#{id})" })
    void createTeamOrStrategy(@Param("teamOrStrategyId") BigInteger teamOrStrategyId, @Param("name") String name, @Param("id") BigInteger id);

    /**
     *获取
     * @param id
     * @param name
     * @return
     */
    @Select({"SELECT strategy_id FROM team_or_strategy where id=#{id} and strategy_name=#{name}"})
    List<BigInteger> getStrategyIdByIdAndName(@Param("id")BigInteger id, @Param("name")String name);
}
