package com.example.cms.entity;

import java.math.BigInteger;
/**
 * @author shaoziwei
 * @date 2018/12/13
 */
public class CourseMemberLimitStrategy {
    private BigInteger id;
    private BigInteger courseId;
    private Integer minMember;
    private Integer maxMember;

    public boolean isValid(Integer size)
    {
        if(minMember<=size&&maxMember>=size)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public CourseMemberLimitStrategy(BigInteger id, BigInteger courseId, Integer minMember, Integer maxMember) {
        this.id = id;
        this.courseId = courseId;
        this.minMember = minMember;
        this.maxMember = maxMember;
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

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }
}
