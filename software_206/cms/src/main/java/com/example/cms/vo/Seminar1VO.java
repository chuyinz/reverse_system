package com.example.cms.vo;

import com.example.cms.entity.Seminar;

import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * @author xzy
 * @date 2018-12-25
 */
public class Seminar1VO {
    BigInteger id;
    String topic;
    String intro;
    Integer status;
    Integer order;
    Integer teamNumLimit;
    Timestamp reportDDL;
    Timestamp signUpStartTime;
    Timestamp signUpEndTime;

    @Override
    public String toString() {
        return "Seminar1VO{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", intro='" + intro + '\'' +
                ", status=" + status +
                ", order=" + order +
                ", teamNumLimit=" + teamNumLimit +
                ", reportDDL=" + reportDDL +
                ", signUpStartTime=" + signUpStartTime +
                ", signUpEndTime=" + signUpEndTime +
                '}';
    }

    public Seminar1VO(Seminar seminar, Integer status, Timestamp reportDdl) {
        this.id = seminar.getId();
        this.topic = seminar.getSeminarName();
        this.intro = seminar.getIntroduction();
        this.status = status;
        this.order = seminar.getSerial();
        this.teamNumLimit = seminar.getMaxTeam();
        this.reportDDL = reportDdl;
        this.signUpStartTime = seminar.getEnrollStartTime();
        this.signUpEndTime = seminar.getEnrollEndTime();
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getTeamNumLimit() {
        return teamNumLimit;
    }

    public void setTeamNumLimit(Integer teamNumLimit) {
        this.teamNumLimit = teamNumLimit;
    }

    public Timestamp getReportDDL() {
        return reportDDL;
    }

    public void setReportDDL(Timestamp reportDDL) {
        this.reportDDL = reportDDL;
    }

    public Timestamp getSignUpStartTime() {
        return signUpStartTime;
    }

    public void setSignUpStartTime(Timestamp signUpStartTime) {
        this.signUpStartTime = signUpStartTime;
    }

    public Timestamp getSignUpEndTime() {
        return signUpEndTime;
    }

    public void setSignUpEndTime(Timestamp signUpEndTime) {
        this.signUpEndTime = signUpEndTime;
    }
}
