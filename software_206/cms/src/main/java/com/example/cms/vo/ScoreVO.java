package com.example.cms.vo;

import com.example.cms.entity.RoundScore;
import com.example.cms.entity.SeminarScore;

import java.math.BigDecimal;

/**
 * @author xzy
 * @date 2018-12-25
 */
public class ScoreVO {
    TeamVO team;
    Integer klassSerial;
    BigDecimal preScore;
    BigDecimal reportScore;
    BigDecimal questionScore;
    BigDecimal totalScore;

    @Override
    public String toString() {
        return "ScoreVO{" +
                "team=" + team +
                ", klassSerial=" + klassSerial +
                ", preScore=" + preScore +
                ", reportScore=" + reportScore +
                ", questionScore=" + questionScore +
                ", totalScore=" + totalScore +
                '}';
    }

    public ScoreVO(TeamVO team, RoundScore roundScore, Integer klassSerial) {
        this.team = team;
        this.klassSerial = klassSerial;
        this.preScore = roundScore.getPresentationScore();
        this.reportScore = roundScore.getReportScore();
        this.questionScore = roundScore.getQuestionScore();
        this.totalScore = roundScore.getTotalScore();
    }


    public ScoreVO(TeamVO team, SeminarScore seminarScore, Integer klassSerial) {
        this.team = team;
        this.klassSerial = klassSerial;
        this.preScore = seminarScore.getPresentationScore();
        this.reportScore = seminarScore.getReportScore();
        this.questionScore = seminarScore.getQuestionScore();
        this.totalScore = seminarScore.getTotalScore();
    }


    public ScoreVO(TeamVO team, BigDecimal preScore, BigDecimal reportScore, BigDecimal questionScore, BigDecimal totalScore) {
        this.team = team;
        this.preScore = preScore;
        this.reportScore = reportScore;
        this.questionScore = questionScore;
        this.totalScore = totalScore;
    }

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    public TeamVO getTeam() {
        return team;
    }

    public void setTeam(TeamVO team) {
        this.team = team;
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

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }
}
