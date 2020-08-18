package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author xzy
 * @date 2018-12-25
 */
public class KlassRoundVO {
    BigInteger classId;
    Integer classSerial;
    Integer enrollNumber;

    @Override
    public String toString() {
        return "KlassRoundVO{" +
                "classId=" + classId +
                ", classSerial=" + classSerial +
                ", enrollNumber=" + enrollNumber +
                '}';
    }

    public KlassRoundVO(BigInteger classId, Integer classSerial, Integer enrollNumber) {
        this.classId = classId;
        this.classSerial = classSerial;
        this.enrollNumber = enrollNumber;
    }

    public BigInteger getClassId() {
        return classId;
    }

    public void setClassId(BigInteger classId) {
        this.classId = classId;
    }

    public Integer getClassSerial() {
        return classSerial;
    }

    public void setClassSerial(Integer classSerial) {
        this.classSerial = classSerial;
    }

    public Integer getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }
}
