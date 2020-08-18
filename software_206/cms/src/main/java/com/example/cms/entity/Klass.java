package com.example.cms.entity;

import java.math.BigInteger;

/**
 * @author shaoziwei
 * @date 2018/12/13
 */
public class Klass {
    private BigInteger id;
    private BigInteger courseId;
    private Integer grade;
    private Integer klassSerial;
    private String klassTime;
    private String klassLocation;

    public Klass(BigInteger id, BigInteger courseId, Integer grade, Integer klassSerial, String klassTime, String klassLocation) {
        this.id = id;
        this.courseId = courseId;
        this.grade = grade;
        this.klassSerial = klassSerial;
        this.klassTime = klassTime;
        this.klassLocation = klassLocation;
    }

    public Klass()
    {

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    public String getKlassTime() {
        return klassTime;
    }

    public void setKlassTime(String klassTime) {
        this.klassTime = klassTime;
    }

    public String getKlassLocation() {
        return klassLocation;
    }

    public void setKlassLocation(String klassLocation) {
        this.klassLocation = klassLocation;
    }
}
