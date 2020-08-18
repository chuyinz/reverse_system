package com.example.cms.entity;
/**
 * @author shaoziwei
 * @date 2018/12/9
 */

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author shaoziwei
 * @date 2018/12/13
 */
public class Course {
    private BigInteger id;
    private BigInteger teacherId;
    private String courseName;
    private String introduction;
    private Integer presentationPercentage;
    private Integer questionPercentage;
    private Integer reportPercentage;
    private Timestamp teamStartTime;
    private Timestamp teamEndTime;
    private BigInteger teamMainCourseId;
    private BigInteger seminarMainCourseId;
    private Integer teamNumberMin;
    private Integer teamNumberMax;

    public Course()
    {

    }

    public Course(BigInteger id, BigInteger teacherId, String courseName, String introduction, Integer presentationPercentage, Integer questionPercentage, Integer reportPercentage, Timestamp teamStartTime, Timestamp teamEndTime, BigInteger teamMainCourseId, BigInteger seminarMainCourseId, Integer teamNumberMin, Integer teamNumberMax) {
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



}
