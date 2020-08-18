package com.example.cms.dao;

import com.example.cms.entity.CourseMemberLimitStrategy;
import com.example.cms.mapper.CourseMemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
/**
 * @author zyy
 * @date 2019-1-3
 */
@Component
public class CourseMemberLimitStrategyDAO {
    @Autowired
    private CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;


    public CourseMemberLimitStrategy getById(BigInteger id)
    {
        return courseMemberLimitStrategyMapper.getById(id);
    }

}
