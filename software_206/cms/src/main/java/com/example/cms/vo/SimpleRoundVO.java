package com.example.cms.vo;

import com.example.cms.entity.Round;

import java.math.BigInteger;
/**
 * @author xzy
 * @date 2018-12-25
 */
public class SimpleRoundVO {
    BigInteger id;
    Integer order;

    @Override
    public String toString() {
        return "SimpleRoundVO{" +
                "id=" + id +
                ", order=" + order +
                '}';
    }

    public SimpleRoundVO(Round round) {
        this.id = round.getId();
        this.order = round.getSerial();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
