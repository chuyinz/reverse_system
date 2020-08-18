package com.example.cms.vo;

import com.example.cms.entity.Attendance;

import java.math.BigDecimal;
import java.math.BigInteger;
/**
 * @author xzy
 * @date 2018-12-25
 */
public class ReportScoreVO {
    BigInteger attendanceId;
    String reportName;
    BigDecimal reportScore;

    @Override
    public String toString() {
        return "ReportScoreVO{" +
                "attendanceId=" + attendanceId +
                ", reportName='" + reportName + '\'' +
                ", reportScore=" + reportScore +
                '}';
    }

    public ReportScoreVO(Attendance attendance, BigDecimal reportScore) {
        attendanceId = attendance.getId();
        this.reportName = attendance.getReportName();
        this.reportScore = reportScore;
    }

    public BigInteger getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(BigInteger attendanceId) {
        attendanceId = attendanceId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }
}
