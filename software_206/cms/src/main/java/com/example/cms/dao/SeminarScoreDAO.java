package com.example.cms.dao;

import com.example.cms.entity.SeminarScore;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author xzy
 * @date 2018-12-25
 */
@Component
public class SeminarScoreDAO {
    @Autowired
    private SeminarScoreMapper seminarScoreMapper;



    public SeminarScore getSeminarScore(BigInteger klassSeminarId, BigInteger teamId) throws NotFoundException {
        return seminarScoreMapper.getSeminarScore(klassSeminarId,teamId);
    }

    public List<BigInteger>getKlassSeminarList(BigInteger teamId){
        return seminarScoreMapper.getKlassSeminarList(teamId);
    }

    public void creat(SeminarScore seminarScore){
        BigDecimal decimal=new BigDecimal("0");
        if (seminarScore.getQuestionScore()==null){seminarScore.setQuestionScore(decimal);}
        if (seminarScore.getPresentationScore()==null){seminarScore.setPresentationScore(decimal);}
        if (seminarScore.getReportScore()==null){seminarScore.setReportScore(decimal);}
        seminarScoreMapper.create(seminarScore);
    }

    public void update(SeminarScore seminarScore) throws InvalidOperationException {
        BigDecimal decimal=new BigDecimal("0");
        if (seminarScore.getQuestionScore()==null){seminarScore.setQuestionScore(decimal);}
        if (seminarScore.getPresentationScore()==null){seminarScore.setPresentationScore(decimal);}
        if (seminarScore.getReportScore()==null){seminarScore.setReportScore(decimal);}
        decimal = new BigDecimal("5");
        if (seminarScore.getPresentationScore().compareTo(decimal)==1||seminarScore.getReportScore().compareTo(decimal)==1||seminarScore.getQuestionScore().compareTo(decimal)==1){
            throw new InvalidOperationException("分数超限");
        }
        seminarScoreMapper.update(seminarScore);
    }

    public void delete(SeminarScore seminarScore){seminarScoreMapper.delete(seminarScore);}


}
