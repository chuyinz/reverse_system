package com.example.cms.vo;

import java.math.BigInteger;
/**
 * @author xzy
 * @date 2018-1-3
 */
public class SelectCourseVO {
    BigInteger courseId;

    @Override
    public String toString() {
        return "SelectCourseVO{" +
                "courseId=" + courseId +
                ", CourseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

    String courseName;
    String teacherName;
    public SelectCourseVO(BigInteger courseId, String courseName, String teacherName) {
        this.courseId = courseId;
        courseName = courseName;
        this.teacherName = teacherName;
    }


    public SelectCourseVO()
    {}


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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }




}
