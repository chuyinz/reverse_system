package com.example.cms.vo;

import com.example.cms.entity.Seminar;

import java.math.BigInteger;
/**
 * @author xzy
 * @date 2018-12-25
 */
public class SeminarVO {
    BigInteger id;
    String topic;
    Integer order;

    @Override
    public String toString() {
        return "SeminarVO{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", order=" + order +
                '}';
    }

    public SeminarVO(Seminar seminar) {
        this.id = seminar.getId();
        this.topic = seminar.getSeminarName();
        this.order = seminar.getSerial();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
