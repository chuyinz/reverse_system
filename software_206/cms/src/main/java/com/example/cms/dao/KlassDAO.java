package com.example.cms.dao;

import com.example.cms.entity.Klass;
import com.example.cms.mapper.KlassMapper;
import com.example.cms.mapper.KlassStudentMapper;
import com.example.cms.mapper.KlassTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
/**
 * @author zyy szw
 * @date 2018-12-25
 */
@Component
public class KlassDAO {
    @Autowired
    private KlassMapper klassMapper;
    @Autowired
    private KlassStudentMapper klassStudentMapper;
    @Autowired
    private KlassTeamMapper klassTeamMapper;

    public void createKlass(Klass klass)
    {
        klassMapper.createKlass(klass);
    }

    public List<BigInteger> getKlassIdByStuId(BigInteger studentId)
    {
        return klassStudentMapper.getKlassIdByStuId(studentId);
    }


    public Klass getKlassById(BigInteger klassId) throws ClassNotFoundException {
        Klass klass= klassMapper.getKlassById(klassId);
        if(klass==null)
        {
            throw new ClassNotFoundException();
        }
        return klass;
    }




    public List<Klass> getKlassByCourseId(BigInteger courseId) throws ClassNotFoundException {
        List<Klass> klass= klassMapper.getKlassByCourseId(courseId);
        if(klass.size()==0)
        {
            throw new ClassNotFoundException();
        }
        return klass;
    }


    public List<BigInteger> getTeamIdByKlassId(BigInteger klassId)
    {
        return klassTeamMapper.getTeamIdByKlassId(klassId);
    }


    public Integer getKlassSerial(BigInteger klassId, BigInteger courseId)
    {
        return klassMapper.getKlassSerial(klassId,courseId);
    }


    public void deleteKlassById(BigInteger klassId)
    {
        klassMapper.deleteKlassById(klassId);
    }


    public void deleteKlassByCourseId(BigInteger courseId)
    {
        klassMapper.deleteKlassByCourse(courseId);
    }


    public void setTeamIdByCourseIdAndStudentId(BigInteger teamId,BigInteger couseId,BigInteger studentId)
    {
        klassStudentMapper.setTeamIdByCourseIdAndStudentId(teamId,couseId,studentId);
    }


    public BigInteger getKlassIdByStuIdAndCourseId(BigInteger studentId,BigInteger courseId)
    {
        return klassStudentMapper.getKlassIdByStuIdAndCourseId(studentId,courseId);
    }


    public void setTeamIdByKlassId(BigInteger teamId,BigInteger klassId)
    {
        klassTeamMapper.setTeamIdByklassId(teamId,klassId);
    }



}
