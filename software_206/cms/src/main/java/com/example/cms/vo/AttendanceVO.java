package com.example.cms.vo;

import com.example.cms.entity.Attendance;
import com.example.cms.entity.Team;

import java.math.BigInteger;

/**
 * @author xzy
 * @date 2018-12-25
 */
public class AttendanceVO {
    BigInteger id;
    TeamVO team;
    String pptName;
    String reportName;
    Integer teamOrder;
    Integer isPresent;

    @Override
    public String toString() {
        return "AttendanceVO{" +
                "id=" + id +
                ", team=" + team +
                ", pptName='" + pptName + '\'' +
                ", reportName='" + reportName + '\'' +
                ", teamOrder=" + teamOrder +
                ", isPresent=" + isPresent +
                '}';
    }

    public AttendanceVO(Attendance attendance, Team team) {
        this.id = attendance.getId();
        this.team = new TeamVO(team);
        this.pptName = attendance.getPptName();
        this.reportName = attendance.getReportName();
        this.teamOrder = attendance.getTeamOrder();
        this.isPresent = attendance.getIsPresent();
    }

    public AttendanceVO(BigInteger id, TeamVO team, String pptName, String reportName, Integer teamOrder, Integer isPresent) {
        this.id = id;
        this.team = team;
        this.pptName = pptName;
        this.reportName = reportName;
        this.teamOrder = teamOrder;
        this.isPresent = isPresent;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public TeamVO getTeam() {
        return team;
    }

    public void setTeam(TeamVO team) {
        this.team = team;
    }

    public String getPptName() {
        return pptName;
    }

    public void setPptName(String pptName) {
        this.pptName = pptName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Integer teamOrder) {
        this.teamOrder = teamOrder;
    }

    public Integer getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Integer isPresent) {
        this.isPresent = isPresent;
    }
}
