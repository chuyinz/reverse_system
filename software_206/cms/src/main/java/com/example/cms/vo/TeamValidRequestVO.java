package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class TeamValidRequestVO {
    private String courseName;
    private String klassName;
    private String leaderName;
    private String reason;
    private BigInteger teamValidId;

    public TeamValidRequestVO() {
    }

    @Override
    public String toString() {
        return "TeamValidRequestVO{" +
                "courseName='" + courseName + '\'' +
                ", klassName='" + klassName + '\'' +
                ", leaderName='" + leaderName + '\'' +
                ", reason='" + reason + '\'' +
                ", teamValidId=" + teamValidId +
                '}';
    }

    public TeamValidRequestVO(String courseName, String klassName, String leaderName, String reason, BigInteger teamValidId) {
        this.courseName = courseName;
        this.klassName = klassName;
        this.leaderName = leaderName;
        this.reason = reason;
        this.teamValidId = teamValidId;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getKlassName() {
        return klassName;
    }

    public void setKlassName(String klassName) {
        this.klassName = klassName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigInteger getTeamValidId() {
        return teamValidId;
    }

    public void setTeamValidId(BigInteger teamValidId) {
        this.teamValidId = teamValidId;
    }



}
