package com.example.cms.vo;

import com.example.cms.entity.SeminarScore;

import java.math.BigDecimal;
/**
 * @author xzy
 * @date 2018-12-25
 */
public class SeminarScoreVO {
    SeminarVO seminar;
    BigDecimal preScore;
    BigDecimal reportScore;
    BigDecimal questionScore;

    @Override
    public String toString() {
        return "SeminarScoreVO{" +
                "seminar=" + seminar +
                ", preScore=" + preScore +
                ", reportScore=" + reportScore +
                ", questionScore=" + questionScore +
                '}';
    }

    public SeminarScoreVO(SeminarVO seminarVO, SeminarScore seminarScore) {
        this.seminar = seminarVO;
        this.preScore = seminarScore.getPresentationScore();
        this.reportScore = seminarScore.getReportScore();
        this.questionScore = seminarScore.getQuestionScore();
    }

    public SeminarVO getSeminar() {
        return seminar;
    }

    public void setSeminar(SeminarVO seminar) {
        this.seminar = seminar;
    }

    public BigDecimal getPreScore() {
        return preScore;
    }

    public void setPreScore(BigDecimal preScore) {
        this.preScore = preScore;
    }

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }
}
