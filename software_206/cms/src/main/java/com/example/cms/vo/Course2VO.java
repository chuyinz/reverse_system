package com.example.cms.vo;

import com.example.cms.entity.Course;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
/**
 * @author xzy
 * @date 2018-1-3
 */
public class Course2VO {
    private BigInteger id;
    private BigInteger teacherId;
    private String courseName;
    private String introduction;

    @Override
    public String toString() {
        return "Course2VO{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", courseName='" + courseName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", presentationPercentage=" + presentationPercentage +
                ", questionPercentage=" + questionPercentage +
                ", reportPercentage=" + reportPercentage +
                ", teamStartTime=" + teamStartTime +
                ", teamEndTime=" + teamEndTime +
                ", teamMainCourseId=" + teamMainCourseId +
                ", seminarMainCourseId=" + seminarMainCourseId +
                ", teamNumberMin=" + teamNumberMin +
                ", teamNumberMax=" + teamNumberMax +
                ", teamCourseRequestList=" + teamCourseRequestList +
                ", conflictCourseList=" + conflictCourseList +
                '}';
    }

    private Integer presentationPercentage;
    private Integer questionPercentage;
    private Integer reportPercentage;
    private Timestamp teamStartTime;
    private Timestamp teamEndTime;

    public Course2VO(BigInteger id, BigInteger teacherId, String courseName, String introduction, Integer presentationPercentage, Integer questionPercentage, Integer reportPercentage, Timestamp teamStartTime, Timestamp teamEndTime, BigInteger teamMainCourseId, BigInteger seminarMainCourseId, Integer teamNumberMin, Integer teamNumberMax, List<TeamCourseRequestVO> teamCourseRequestList, List<SelectCourseVO> conflictCourseList) {
        this.id = id;
        this.teacherId = teacherId;
        this.courseName = courseName;
        this.introduction = introduction;
        this.presentationPercentage = presentationPercentage;
        this.questionPercentage = questionPercentage;
        this.reportPercentage = reportPercentage;
        this.teamStartTime = teamStartTime;
        this.teamEndTime = teamEndTime;
        this.teamMainCourseId = teamMainCourseId;
        this.seminarMainCourseId = seminarMainCourseId;
        this.teamNumberMin = teamNumberMin;
        this.teamNumberMax = teamNumberMax;
        this.teamCourseRequestList = teamCourseRequestList;
        this.conflictCourseList = conflictCourseList;
    }


    public Course2VO()
    {}


    private BigInteger teamMainCourseId;
    private BigInteger seminarMainCourseId;
    private Integer teamNumberMin;
    private Integer teamNumberMax;
    private List<TeamCourseRequestVO> teamCourseRequestList;
    private List<SelectCourseVO>conflictCourseList;
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(BigInteger teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPresentationPercentage() {
        return presentationPercentage;
    }

    public void setPresentationPercentage(Integer presentationPercentage) {
        this.presentationPercentage = presentationPercentage;
    }

    public Integer getQuestionPercentage() {
        return questionPercentage;
    }

    public void setQuestionPercentage(Integer questionPercentage) {
        this.questionPercentage = questionPercentage;
    }

    public Integer getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(Integer reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public Timestamp getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(Timestamp teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public Timestamp getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(Timestamp teamEndTime) {
        this.teamEndTime = teamEndTime;
    }

    public BigInteger getTeamMainCourseId() {
        return teamMainCourseId;
    }

    public void setTeamMainCourseId(BigInteger teamMainCourseId) {
        this.teamMainCourseId = teamMainCourseId;
    }

    public BigInteger getSeminarMainCourseId() {
        return seminarMainCourseId;
    }

    public void setSeminarMainCourseId(BigInteger seminarMainCourseId) {
        this.seminarMainCourseId = seminarMainCourseId;
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

    public List<TeamCourseRequestVO> getTeamCourseRequestList() {
        return teamCourseRequestList;
    }

    public void setTeamCourseRequestList(List<TeamCourseRequestVO> teamCourseRequestList) {
        this.teamCourseRequestList = teamCourseRequestList;
    }

    public List<SelectCourseVO> getConflictCourseList() {
        return conflictCourseList;
    }

    public void setConflictCourseList(List<SelectCourseVO> conflictCourseList) {
        this.conflictCourseList = conflictCourseList;
    }




}
