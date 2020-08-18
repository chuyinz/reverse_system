package com.example.cms.entity;

import java.math.BigInteger;

/**
 * @author shaoziwei
 * @date 2018/12/13
 */
public class Attendance {
    private BigInteger id;
    private BigInteger klassSeminarId;
    private BigInteger teamId;
    private Integer teamOrder;
    private Integer isPresent;
    private String reportName;
    private String reportUrl;
    private String pptName;
    private String pptUrl;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(BigInteger klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public int getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(int teamOrder) {
        this.teamOrder = teamOrder;
    }

    public int getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(int isPresent) {
        this.isPresent = isPresent;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getPptName() {
        return pptName;
    }

    public void setPptName(String pptName) {
        this.pptName = pptName;
    }

    public String getPptUrl() {
        return pptUrl;
    }

    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl;
    }

    public Attendance(){

    }

    public Attendance(BigInteger id, BigInteger klassSeminarId, BigInteger teamId, int teamOrder, int isPresent, String reportName, String reportUrl, String pptName, String pptUrl) {
        this.id = id;
        this.klassSeminarId = klassSeminarId;
        this.teamId = teamId;
        this.teamOrder = teamOrder;
        this.isPresent = isPresent;
        this.reportName = reportName;
        this.reportUrl = reportUrl;
        this.pptName = pptName;
        this.pptUrl = pptUrl;
    }

    public Attendance( BigInteger klassSeminarId, BigInteger teamId, int teamOrder, int isPresent) {
        this.klassSeminarId = klassSeminarId;
        this.teamId = teamId;
        this.teamOrder = teamOrder;
        this.isPresent = isPresent;
    }



}
