package com.example.cms.vo;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class CreateTeamVO {
    private String teamName;
    private BigInteger courseId;
    private BigInteger klassId;
    private TeamMemberVO leader;
    private List<TeamMemberVO>members;

    @Override
    public String toString() {
        return "CreateTeamVO{" +
                "teamName='" + teamName + '\'' +
                ", courseId=" + courseId +
                ", klassId=" + klassId +
                ", leader=" + leader +
                ", members=" + members +
                '}';
    }

    public CreateTeamVO(String teamName, BigInteger courseId, BigInteger klassId, TeamMemberVO leader, List<TeamMemberVO> members) {
        this.teamName = teamName;
        this.courseId = courseId;
        this.klassId = klassId;
        this.leader = leader;
        this.members = members;
    }

    public CreateTeamVO()
    {

    }
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getKlassId() {
        return klassId;
    }

    public void setKlassId(BigInteger klassId) {
        this.klassId = klassId;
    }

    public TeamMemberVO getLeader() {
        return leader;
    }

    public void setLeader(TeamMemberVO leader) {
        this.leader = leader;
    }

    public List<TeamMemberVO> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMemberVO> members) {
        this.members = members;
    }
}
