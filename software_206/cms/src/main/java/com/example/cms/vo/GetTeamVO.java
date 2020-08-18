package com.example.cms.vo;

import java.math.BigInteger;
import java.util.List;

/**
 * @author szw
 * @date 2018-12-25
 */
public class GetTeamVO {
    private BigInteger id;
    private String name;
    private CourseInTeamVO course;
    private KlassInTeamVO klass;
    private StudentInTeamVO leader;
    private List<StudentInTeamVO> members;
    private Integer status;

    @Override
    public String toString() {
        return "GetTeamVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", klass=" + klass +
                ", leader=" + leader +
                ", members=" + members +
                ", status=" + status +
                '}';
    }

    public GetTeamVO(BigInteger id, String name, CourseInTeamVO course, KlassInTeamVO klass, StudentInTeamVO leader, List<StudentInTeamVO> members, Integer status) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.klass = klass;
        this.leader = leader;
        this.members = members;
        this.status = status;
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseInTeamVO getCourse() {
        return course;
    }

    public void setCourse(CourseInTeamVO course) {
        this.course = course;
    }

    public KlassInTeamVO getKlass() {
        return klass;
    }

    public void setKlass(KlassInTeamVO klass) {
        this.klass = klass;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
