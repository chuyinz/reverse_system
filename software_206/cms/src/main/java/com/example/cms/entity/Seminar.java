package com.example.cms.entity;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author shaoziwei
 * @date 2018/12/16
 */
public class Seminar {
    private BigInteger id;
    private BigInteger courseId;
    private BigInteger roundId;
    private String seminarName;
    private String introduction;
    private Integer maxTeam;
    private Integer isVisible;
    private Integer serial;
    private Timestamp enrollStartTime;
    private Timestamp enrollEndTime;

    public Seminar(BigInteger courseId, BigInteger roundId, String seminarName, String introduction, Integer maxTeam, Integer isVisible, Integer serial, Timestamp enrollStartTime, Timestamp enrollEndTime) {
        this.courseId = courseId;
        this.roundId = roundId;
        this.seminarName = seminarName;
        this.introduction = introduction;
        this.maxTeam = maxTeam;
        this.isVisible = isVisible;
        this.serial = serial;
        this.enrollStartTime = enrollStartTime;
        this.enrollEndTime = enrollEndTime;
    }

    public Seminar(BigInteger id, BigInteger courseId, BigInteger roundId, String seminarName, String introduction, Integer maxTeam, Integer isVisible, Integer serial, Timestamp enrollStartTime, Timestamp enrollEndTime) {
        this.id = id;
        this.courseId = courseId;
        this.roundId = roundId;
        this.seminarName = seminarName;
        this.introduction = introduction;
        this.maxTeam = maxTeam;
        this.isVisible = isVisible;
        this.serial = serial;
        this.enrollStartTime = enrollStartTime;
        this.enrollEndTime = enrollEndTime;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getRoundId() {
        return roundId;
    }

    public void setRoundId(BigInteger roundId) {
        this.roundId = roundId;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getMaxTeam() {
        return maxTeam;
    }

    public void setMaxTeam(Integer maxTeam) {
        this.maxTeam = maxTeam;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Timestamp getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(Timestamp enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public Timestamp getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(Timestamp enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }
}
