package com.example.cms.dao;

import com.example.cms.mapper.TeamAndStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
/**
 * @author zyy
 * @date 2019-1-3
 */
@Component
public class TeamAndStrategyDAO {

    @Autowired
    TeamAndStrategyMapper teamAndStrategyMapper;

    public BigInteger getStrategyIdByIdAndName(BigInteger id,String strategyName)
    {
        return teamAndStrategyMapper.getStrategyIdByIdAndName(id,strategyName);
    }


}
