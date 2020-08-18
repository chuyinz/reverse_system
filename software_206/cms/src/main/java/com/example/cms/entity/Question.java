package com.example.cms.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author shaoziwei
 * @date 2018/11/30
 */
/**
 * @author shaoziwei
 * @date 2018/12/16
 */
public class Question {
    private BigInteger id;
    private BigInteger klassSeminarId;
    private BigInteger attendanceId;
    private BigInteger teamId;
    private BigInteger studentId;
    private Integer isSelected;
    private BigDecimal score;

    public Question() {
    }

    public Question(BigInteger id, BigInteger klassSeminarId, BigInteger attendanceId, BigInteger teamId, BigInteger studentId, Integer isSelected, BigDecimal score) {
        this.id = id;
        this.klassSeminarId = klassSeminarId;
        this.attendanceId = attendanceId;
        this.teamId = teamId;
        this.studentId = studentId;
        this.isSelected = isSelected;
        this.score = score;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(BigInteger klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public BigInteger getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(BigInteger attendanceId) {
        this.attendanceId = attendanceId;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public BigInteger getStudentId() {
        return studentId;
    }

    public void setStudentId(BigInteger studentId) {
        this.studentId = studentId;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
