package com.example.cms.dao;

import com.example.cms.entity.Seminar;
import com.example.cms.exception.NotFoundException;
import com.example.cms.exception.SeminarNotFoundException;
import com.example.cms.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.dnd.InvalidDnDOperationException;
import java.math.BigInteger;
import java.util.List;

/**
 * @author xzy
 * @date 2018-12-25
 */
@Component
public class SeminarDAO {
    @Autowired
    private SeminarMapper seminarMapper;

    public BigInteger createSeminar(Seminar seminar) throws InvalidDnDOperationException {
        if(seminarMapper.getId(seminar)!=null){
            throw new InvalidDnDOperationException("已存在");
        }
        seminarMapper.createSeminar(seminar);
        return seminarMapper.getId(seminar);
    }

    public BigInteger getId(Seminar seminar) throws SeminarNotFoundException {
        BigInteger id = seminarMapper.getId(seminar);
        if(id == null){
            throw new SeminarNotFoundException();
        }
        return id;
    }

    public Seminar getSeminarById(BigInteger seminarId) throws SeminarNotFoundException {
        Seminar seminar;
        seminar=seminarMapper.getById(seminarId);
        if (seminar==null){
            throw new SeminarNotFoundException();
        }
        return seminar;
    }

    public void updateSeminar(Seminar seminar){
        seminarMapper.updateSeminar(seminar);
    }

    public void deleteSeminar(BigInteger seminarId){
        seminarMapper.deleteSeminarById(seminarId);
    }



    public List<Seminar>getAllSeminar(BigInteger roundId) throws NotFoundException {
        List<Seminar> seminarList =  seminarMapper.getAllSeminar(roundId);
        if (seminarList.size()==0){
            throw new NotFoundException("暂无讨论课");
        }
        return seminarList;
    }

    public List<BigInteger>getAllSeminarId(BigInteger roundId) throws NotFoundException {
        List<BigInteger> seminarIdList =  seminarMapper.getAllSeminarId(roundId);
        if (seminarIdList.size()==0){
            throw new NotFoundException("暂无讨论课");
        }
        return seminarIdList;
    }

}
