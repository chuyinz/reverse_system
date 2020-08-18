package com.example.cms.entity;
/**
 * @author shaoziwei
 * @date 2018/11/30
 */
import java.util.Date;

public class CourseRule {
    private Date startTeamTime;
    private Date endTeamTime;
    private Integer teamNumberMin;
    private Integer teamNumberMax;
    private Integer score1;
    private Integer score2;
    private Integer score3;

    public CourseRule(Date startTeamTime, Date endTeamTime, Integer teamNumberMin, Integer teamNumberMax, Integer score1, Integer score2, Integer score3) {
        this.startTeamTime = startTeamTime;
        this.endTeamTime = endTeamTime;
        this.teamNumberMin = teamNumberMin;
        this.teamNumberMax = teamNumberMax;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    public Date getStartTeamTime() {
        return startTeamTime;
    }

    public void setStartTeamTime(Date startTeamTime) {
        this.startTeamTime = startTeamTime;
    }

    public Date getEndTeamTime() {
        return endTeamTime;
    }

    public void setEndTeamTime(Date endTeamTime) {
        this.endTeamTime = endTeamTime;
    }

    public Integer getTeamNumberMin() {
        return teamNumberMin;
    }

    public void setTeamNumberMin(Integer teamNumberMin) {
        this.teamNumberMin = teamNumberMin;
    }

    public Integer getTeamNumberMax() {
        return teamNumberMax;
    }

    public void setTeamNumberMax(Integer teamNumberMax) {
        this.teamNumberMax = teamNumberMax;
    }

    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer score1) {
        this.score1 = score1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer score2) {
        this.score2 = score2;
    }

    public Integer getScore3() {
        return score3;
    }

    public void setScore3(Integer score3) {
        this.score3 = score3;
    }
}
