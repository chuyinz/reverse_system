package com.example.cms.vo;

import java.math.BigInteger;
/**
 * @author zyy
 * @date 2018-12-25
 */
public class TeamRequestVO {
    private String requestType;
    private BigInteger courseId;
    private BigInteger classId;
    private BigInteger teamId;
    private BigInteger leaderId;
    private String reason;

    @Override
    public String toString() {
        return "TeamRequestVO{" +
                "requestType='" + requestType + '\'' +
                ", courseId=" + courseId +
                ", classId=" + classId +
                ", teamId=" + teamId +
                ", leaderId=" + leaderId +
                ", reason='" + reason + '\'' +
                '}';
    }

    public TeamRequestVO(String requestType, BigInteger courseId, BigInteger classId, BigInteger teamId, BigInteger leaderId, String reason) {
         this.courseId = courseId;
        this.classId = classId;
        this.teamId = teamId;
        this.leaderId = leaderId;
        this.reason = reason;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getClassId() {
        return classId;
    }

    public void setClassId(BigInteger classId) {
        this.classId = classId;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public BigInteger getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(BigInteger leaderId) {
        this.leaderId = leaderId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
