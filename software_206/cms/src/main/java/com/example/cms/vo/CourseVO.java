package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class CourseVO {
    private BigInteger id;
    private BigInteger klassId;
    private String name;
    private String klassName;
    private String teacherName;
    private  Integer status;
    public BigInteger getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CourseVO{" +
                "id=" + id +
                ", klassId=" + klassId +
                ", name='" + name + '\'' +
                ", klassName='" + klassName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", status=" + status +
                '}';
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKlassId() {
        return klassId;
    }

    public void setKlassId(BigInteger klassId) {
        this.klassId = klassId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKlassName() {
        return klassName;
    }

    public void setKlassName(String klassName) {
        this.klassName = klassName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public CourseVO()
    {}






}
