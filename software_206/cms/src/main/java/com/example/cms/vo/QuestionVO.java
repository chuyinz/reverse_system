package com.example.cms.vo;

import com.example.cms.entity.Question;
import com.example.cms.entity.Team;

import java.math.BigInteger;
import java.math.BigDecimal;

/**
 * @author xzy
 * @date 2018-12-25
 */
public class QuestionVO {
    BigInteger id;
    BigDecimal score;
    TeamVO team;

    public QuestionVO(Question question, Team team) {
        this.id = question.getId();
        this.score = question.getScore();
        this.team=new TeamVO(team);
    }

    @Override
    public String toString() {
        return "QuestionVO{" +
                "id=" + id +
                ", score=" + score +
                ", team=" + team +
                '}';
    }

    public QuestionVO(BigInteger id, BigDecimal score, TeamVO team) {
        this.id = id;
        this.score = score;
        this.team = team;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public TeamVO getTeam() {
        return team;
    }

    public void setTeam(TeamVO team) {
        this.team = team;
    }
}
