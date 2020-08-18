package com.example.cms.entity;

import java.math.BigInteger;
/**
 * @author szw
 * @date 2018/12/13
 */
public class TeamAndOrStrategy {
    private BigInteger id;
    private String strategyName;
    private BigInteger strategyId;

    public TeamAndOrStrategy(BigInteger id, String strategyName, BigInteger strategyId) {
        this.id = id;
        this.strategyName = strategyName;
        this.strategyId = strategyId;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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
