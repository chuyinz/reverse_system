package com.example.cms.dao;

import com.example.cms.mapper.ConflictCourseStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
/**
 * @author zyy
 * @date 2019-1-3
 */
@Component
public class ConflictCourseDAO {

    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    public List<BigInteger> getCourseIdById(BigInteger id)
    {
        return conflictCourseStrategyMapper.getCourseIdById(id);
    }
}
