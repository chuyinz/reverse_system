package com.example.cms.entity;

import java.math.BigInteger;
/**
 * @author xzy
 * @date 2018/12/13
 */
public class KlassRound {
    BigInteger klassId;
    BigInteger roundId;
    Integer enrollNumber;

    public KlassRound(BigInteger klassId, BigInteger roundId, Integer enrollNumber) {
        this.klassId = klassId;
        this.roundId = roundId;
        this.enrollNumber = enrollNumber;
    }

    public BigInteger getKlassId() {
        return klassId;
    }

    public void setKlassId(BigInteger klassId) {
        this.klassId = klassId;
    }

    public BigInteger getRoundId() {
        return roundId;
    }

    public void setRoundId(BigInteger roundId) {
        this.roundId = roundId;
    }

    public Integer getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }
}
