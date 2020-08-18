package com.example.cms.vo;

import java.math.BigInteger;
/**
 * @author szw
 * @date 2018-12-25
 */
public class ShareTeamVO {
    private String shareCourseName;
    private BigInteger shareTeamId;
    private String shareTeacherName;
    private String shareStatus;
    public ShareTeamVO() {
    }

    @Override
    public String toString() {
        return "ShareTeamVO{" +
                "shareCourseName='" + shareCourseName + '\'' +
                ", shareTeamId=" + shareTeamId +
                ", shareTeacherName='" + shareTeacherName + '\'' +
                ", shareStatus='" + shareStatus + '\'' +
                '}';
    }

    public ShareTeamVO(String shareCourseName, BigInteger shareTeamId, String shareTeacherName, String shareStatus) {
        this.shareCourseName = shareCourseName;
        this.shareTeamId = shareTeamId;
        this.shareTeacherName = shareTeacherName;
        this.shareStatus = shareStatus;
    }


    public BigInteger getShareTeamId() {
        return shareTeamId;
    }

    public void setShareTeamId(BigInteger shareTeamId) {
        this.shareTeamId = shareTeamId;
    }



    public String getShareCourseName() {
        return shareCourseName;
    }

    public void setShareCourseName(String shareCourseName) {
        this.shareCourseName = shareCourseName;
    }

    public String getShareTeacherName() {
        return shareTeacherName;
    }

    public void setShareTeacherName(String shareTeacherName) {
        this.shareTeacherName = shareTeacherName;
    }

    public String getShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(String shareStatus) {
        this.shareStatus = shareStatus;
    }


}
