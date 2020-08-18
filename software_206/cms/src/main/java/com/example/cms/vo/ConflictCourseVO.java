package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class ConflictCourseVO {
    private BigInteger courseId1;
    private BigInteger courseId2;

    @Override
    public String toString() {
        return "ConflictCourseVO{" +
                "courseId1=" + courseId1 +
                ", courseId2=" + courseId2 +
                '}';
    }

    public ConflictCourseVO() {
    }
    public ConflictCourseVO(BigInteger courseId1, BigInteger courseId2) {
        this.courseId1 = courseId1;
        this.courseId2 = courseId2;
    }



    public BigInteger getCourseId1() {
        return courseId1;
    }

    public void setCourseId1(BigInteger courseId1) {
        this.courseId1 = courseId1;
    }

    public BigInteger getCourseId2() {
        return courseId2;
    }

    public void setCourseId2(BigInteger courseId2) {
        this.courseId2 = courseId2;
    }




}
