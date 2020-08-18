package com.example.cms.dao;

import com.example.cms.mapper.TeamOrStrategyMapper;
import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
/**
 * @author zyy
 * @date 2019-1-3
 */
@Component
public class TeamOrStrategyDAO {
    @Autowired
    TeamOrStrategyMapper teamOrStrategyMapper;

    public List<BigInteger> getStrategyIdByIdAndName(BigInteger id, String strategyName)
    {
        return teamOrStrategyMapper.getStrategyIdByIdAndName(id,strategyName);
    }

}
