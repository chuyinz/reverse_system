package com.example.cms.dao;

import com.example.cms.entity.KlassRound;
import com.example.cms.exception.NotFoundException;
import com.example.cms.mapper.KlassRoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
/**
 * @author xzy
 * @date 2018-12-25
 */
@Component
public class KlassRoundDAO {
    @Autowired
    KlassRoundMapper klassRoundMapper;

    public void createKlassRound(BigInteger klassId, BigInteger roundId, Integer enrollNumber) {
        try {
            klassRoundMapper.createKlassRound(klassId, roundId, enrollNumber);
        } catch (Exception e) {
        }
    }

    public void update(KlassRound klassRound) {
        klassRoundMapper.update(klassRound);
    }

    public List<KlassRound> getByRoundId(BigInteger roundId) throws NotFoundException {
        List<KlassRound> klassRoundList = klassRoundMapper.getByRoundId(roundId);
        if (klassRoundList.size() == 0) {
            throw new NotFoundException("还未加入班级");
        }
        return klassRoundList;
    }

    public KlassRound getByKlassIdAndRoundId(BigInteger klassId, BigInteger roundId){
        KlassRound klassRound = klassRoundMapper.getByKlassIdAndRoundId(klassId, roundId);
        return klassRound;
    }
}

