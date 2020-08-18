package com.example.cms.entity;

import java.math.BigInteger;
/**
 * @author szw
 * @date 2018/12/13
 */
public class TeamStrategy {
    private BigInteger courseId;
    private Integer strategySerial;
    private String strategyName;
    private BigInteger strategyId;

    public TeamStrategy(BigInteger courseId, Integer strategySerial, String strategyName, BigInteger strategyId) {
        this.courseId = courseId;
        this.strategySerial = strategySerial;
        this.strategyName = strategyName;
        this.strategyId = strategyId;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public Integer getStrategySerial() {
        return strategySerial;
    }

    public void setStrategySerial(Integer strategySerial) {
        this.strategySerial = strategySerial;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public BigInteger getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(BigInteger strategyId) {
        this.strategyId = strategyId;
    }
}
