package com.example.cms.vo;

import com.example.cms.entity.Team;

import java.math.BigInteger;
/**
 * @author xzy
 * @date 2018-12-25
 */
public class TeamVO {
    private Integer teamSerial;
    private String teamName;
    private BigInteger teamId;

    @Override
    public String toString() {
        return "TeamVO{" +
                "teamSerial=" + teamSerial +
                ", teamName='" + teamName + '\'' +
                ", teamId=" + teamId +
                '}';
    }

    public TeamVO(Team team) {
        this.teamName = team.getTeamName();
        this.teamId = team.getId();
        this.teamSerial =team.getTeamSerial();
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }
}
