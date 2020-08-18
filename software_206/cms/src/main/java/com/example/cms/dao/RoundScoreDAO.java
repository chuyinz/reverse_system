package com.example.cms.dao;

import com.example.cms.entity.RoundScore;
import com.example.cms.exception.InvalidOperationException;
import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.RoundMapper;
import com.example.cms.mapper.RoundScoreMapper;
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
public class RoundScoreDAO {
    @Autowired
    RoundScoreMapper roundScoreMapper;

    public List<RoundScore> getRoundScoreList(BigInteger roundId) throws NotFoundException {
        List<RoundScore>roundScoreList = roundScoreMapper.getRoundScoreList(roundId);
        if (roundScoreList.size()==0){
            throw new NotFoundException("暂无无队伍成绩");
        }
        return roundScoreList;
    }

    public RoundScore getRoundScore(BigInteger roundId, BigInteger teamId) throws NotFoundException {
        RoundScore roundScore = roundScoreMapper.getRoundScore(roundId, teamId);
        if (roundScore==null){
            throw new NotFoundException("暂无无队伍该伦次成绩");
        }
        return roundScore;
    }

    public void creat(RoundScore roundScore){
        roundScoreMapper.create(roundScore);
    }

    public void update(RoundScore roundScore) throws InvalidOperationException {
        BigDecimal five = new BigDecimal("5");
        if (roundScore.getPresentationScore().compareTo(five)==1||roundScore.getReportScore().compareTo(five)==1||roundScore.getQuestionScore().compareTo(five)==1){
            throw new InvalidOperationException("分数超限");
        }
        roundScoreMapper.update(roundScore);
    }

}
