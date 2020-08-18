package com.example.cms.dao;

import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @author xzy
 * @date 2018-12-25
 */
@Component
public class KlassStudentDAO {
    @Autowired
    KlassStudentMapper klassStudentMapper;


    public BigInteger getTeamIdBySK(BigInteger studentId, BigInteger klassId){
        return  klassStudentMapper.getTeamIdBySK(studentId, klassId);
    }

    public BigInteger getTeamIdBySC(BigInteger studentId, BigInteger courseId) throws NotFoundException {
        BigInteger teamId = klassStudentMapper.getTeamIdBySC(studentId, courseId);
        if (teamId == null){
            throw new NotFoundException("您未选该课");
        }
        return teamId;
    }
}
