package com.example.cms.vo;

import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;

import java.math.BigInteger;
/**
 * @author xzy
 * @date 2018-1-3
 */
public class TeamCourseRequestVO {
    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    BigInteger courseId;
    String courseName;

    @Override
    public String toString() {
        return "TeamCourseRequestVO{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teamNumberMin=" + teamNumberMin +
                ", teamNumberMax=" + teamNumberMax +
                '}';
    }

    Integer teamNumberMin;
    Integer teamNumberMax;
    public TeamCourseRequestVO(BigInteger courseId, String courseName, Integer teamNumberMin, Integer teamNumberMax) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teamNumberMin = teamNumberMin;
        this.teamNumberMax = teamNumberMax;
    }


    public TeamCourseRequestVO()
    {}

}
