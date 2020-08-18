package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class CourseInTeamVO {
    private BigInteger id;
    private String name;

    public CourseInTeamVO(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CourseInTeamVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}
