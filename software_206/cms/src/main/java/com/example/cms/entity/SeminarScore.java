package com.example.cms.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
/**
 * @author xzy
 * @date 2018/12/13
 */
public class SeminarScore {
    BigInteger klassSeminarId;
    BigInteger teamId;
    BigDecimal totalScore;
    BigDecimal presentationScore;
    BigDecimal questionScore;
    BigDecimal reportScore;

    public SeminarScore(BigInteger klassSeminarId, BigInteger teamId, BigDecimal presentationScore, BigDecimal questionScore, BigDecimal reportScore) {
        this.klassSeminarId = klassSeminarId;
        this.teamId = teamId;
        this.presentationScore = presentationScore;
        this.questionScore = questionScore;
        this.reportScore = reportScore;
    }

    public SeminarScore(BigInteger klassSeminarId, BigInteger teamId) {
        this.klassSeminarId = klassSeminarId;
        this.teamId = teamId;
        this.totalScore = new BigDecimal("0");
        this.presentationScore = new BigDecimal("0");
        this.questionScore = new BigDecimal("0");
        this.reportScore = new BigDecimal("0");
    }

    public SeminarScore() {

    }

    public BigInteger getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(BigInteger klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public BigDecimal getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(BigDecimal presentationScore) {
        this.presentationScore = presentationScore;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }
}
