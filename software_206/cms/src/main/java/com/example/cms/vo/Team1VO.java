package com.example.cms.vo;

import com.example.cms.entity.Course;
import com.example.cms.entity.Klass;
import com.example.cms.entity.User;

import java.math.BigInteger;
import java.util.List;
/**
 * @author szw
 * @date 2018-12-25
 */
public class Team1VO {
    private BigInteger id;
    private String teamName;
    private User leader;
    private Integer teamSerial;
    private Integer classSerial;
    private Klass klass;
    private Course course;
    private List<User> members;
    private Integer status;

    @Override
    public String toString() {
        return "Team1VO{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", leader=" + leader +
                ", teamSerial=" + teamSerial +
                ", classSerial=" + classSerial +
                ", klass=" + klass +
                ", course=" + course +
                ", members=" + members +
                ", status=" + status +
                '}';
    }

    public int countMembersNum() {
        return members.size();
    }

    public Team1VO(BigInteger id, String teamName, User leader, Integer teamSerial, Integer classSerial, Klass klass, Course course, List<User> members, Integer status) {
        this.id = id;
        this.teamName = teamName;
        this.leader = leader;
        this.teamSerial = teamSerial;
        this.classSerial = classSerial;
        this.klass = klass;
        this.course = course;
        this.members = members;
        this.status = status;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public Integer getClassSerial() {
        return classSerial;
    }

    public void setClassSerial(Integer classSerial) {
        this.classSerial = classSerial;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}



