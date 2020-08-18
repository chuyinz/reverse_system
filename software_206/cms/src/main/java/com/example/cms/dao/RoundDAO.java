package com.example.cms.dao;

import com.example.cms.entity.Round;
import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.KlassRoundMapper;
import com.example.cms.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author xzy
 * @date 2018-12-25
 */
@Component
public class RoundDAO {
    @Autowired
    private RoundMapper roundMapper;

    public Round getRoundByCourseIdAndSerial(BigInteger courseId, Integer serial) throws NotFoundException {
        Round round;
        round = roundMapper.getRoundByCourseIdAndSerial(courseId, serial);
        if(round==null){
            throw new NotFoundException("轮次未找到");
        }
        return round;
    }

    public BigInteger createRound(Round round){
        roundMapper.create(round);
        return roundMapper.getId(round);
    }

    public Round getRoundById(BigInteger id) throws NotFoundException {
        Round round = roundMapper.getRoundById(id);
        if(round==null){
            throw new NotFoundException("轮次未找到");
        }
        return round;
    }

    public void update(Round round){
        roundMapper.update(round);
    }


    public List<Round> getAllRound(BigInteger courseId) throws NotFoundException {
        List<Round> roundList = roundMapper.getRoundByCourseId(courseId);
        if (roundList.size()==0){
            throw new NotFoundException("课程暂无轮次");
        }
        return roundList;
    }

}
