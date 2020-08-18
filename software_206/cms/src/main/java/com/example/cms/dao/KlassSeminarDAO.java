package com.example.cms.dao;

import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.KlassSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author xzy
 * @date 2018-12-25
 */
@Component
public class KlassSeminarDAO {
    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    public void createKlassSeminar(BigInteger klassId, BigInteger seminarId, Integer status){
        klassSeminarMapper.createKlassSeminar(klassId, seminarId, status);
    }

    public BigInteger getKlassIdById(BigInteger id){
        return  klassSeminarMapper.getKlassIdById(id);
    }

    public  BigInteger getIdByKlassIdAndSeminarId(BigInteger klassId, BigInteger seminarId){
        return  klassSeminarMapper.getIdByKlassIdAndSeminarId(klassId,seminarId);
    }

    public List<BigInteger> getKlassIdBySeminarId(BigInteger seminarId){
        return klassSeminarMapper.getKlassIdBySeminarId(seminarId);
    }

    public List<BigInteger> getIdBySeminarId(BigInteger seminarId){
        return klassSeminarMapper.getIdBySeminarId(seminarId);
    }

    public void deleteById(BigInteger id){
        klassSeminarMapper.deleteById(id);
    }

    public void updateDDLById(BigInteger id, Timestamp reportDDL){
        klassSeminarMapper.updateDDLById(id, reportDDL);
    }

    public void updateStatusById(BigInteger id, Integer status){
        klassSeminarMapper.updateStatusById(id, status);
    }

    public BigInteger getSeminarIdById(BigInteger id){
        return  klassSeminarMapper.getSeminarIdById(id);
    }

   public  Timestamp getReportDdlById(BigInteger id){
        return klassSeminarMapper.getReportDdlById(id);
    }

    public Integer getStatusById(BigInteger id){
        return klassSeminarMapper.getStatusById(id);
    }

}
