package com.example.cms.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
/**
 * @author zyy
 * @date 2018-12-25
 */
public class TeamShareRequestVO {
    private String mainCourseName;
    private String mainTeacherName;
    private BigInteger shareTeamId;

    @Override
    public String toString() {
        return "TeamShareRequestVO{" +
                "mainCourseName='" + mainCourseName + '\'' +
                ", mainTeacherName='" + mainTeacherName + '\'' +
                ", shareTeamId=" + shareTeamId +
                '}';
    }

    public TeamShareRequestVO() {
    }

    public TeamShareRequestVO(String mainCourseName, String mainTeacherName,BigInteger shareTeamId) {
        this.mainCourseName = mainCourseName;
        this.mainTeacherName = mainTeacherName;
        this.shareTeamId = shareTeamId;
    }


    public String getMainCourseName() {
        return mainCourseName;
    }

    public void setMainCourseName(String mainCourseName) {
        this.mainCourseName = mainCourseName;
    }

    public String getMainTeacherName() {
        return mainTeacherName;
    }

    public void setMainTeacherName(String mainTeacherName) {
        this.mainTeacherName = mainTeacherName;
    }

    public BigInteger getShareTeamId() {
        return shareTeamId;
    }

    public void setShareTeamId(BigInteger shareTeamId) {
        this.shareTeamId = shareTeamId;
    }



}
