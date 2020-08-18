package com.example.cms.vo;

import java.math.BigInteger;
import java.util.List;
/**
 * @author zyy
 * @date 2018-12-25
 */
public class Team2VO {
    BigInteger teamId;
    String teamName;
    Integer valid;

    @Override
    public String toString() {
        return "Team2VO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", valid=" + valid +
                ", leader=" + leader +
                ", members=" + members +
                '}';
    }

    public Team2VO() {
    }

    StudentInTeamVO leader;
    List<StudentInTeamVO>members;
    public Team2VO(BigInteger teamId, String teamName, Integer valid, StudentInTeamVO leader, List<StudentInTeamVO> members) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.valid = valid;
        this.leader = leader;
        this.members = members;
    }


    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public StudentInTeamVO getLeader() {
        return leader;
    }

    public void setLeader(StudentInTeamVO leader) {
        this.leader = leader;
    }

    public List<StudentInTeamVO> getMembers() {
        return members;
    }

    public void setMembers(List<StudentInTeamVO> members) {
        this.members = members;
    }





}
