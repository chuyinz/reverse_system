package com.example.cms.entity;

/**
 * @author XIE
 * @date 2018/11/30
 */

public class Presentation {

    private String id;
    private String teamID;
    private String seminarID;
    private int score;
    private  String pptAddress;
    private  String reportAddress;
    private  String reportScore;
    private  String status;

    public Presentation(String id, String teamID, String seminarID, int score, String pptAddress, String reportAddress, String reportScore, String status) {
        this.id = id;
        this.teamID = teamID;
        this.seminarID = seminarID;
        this.score = score;
        this.pptAddress = pptAddress;
        this.reportAddress = reportAddress;
        this.reportScore = reportScore;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getSeminarID() {
        return seminarID;
    }

    public void setSeminarID(String seminarID) {
        this.seminarID = seminarID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPptAddress() {
        return pptAddress;
    }

    public void setPptAddress(String pptAddress) {
        this.pptAddress = pptAddress;
    }

    public String getReportAddress() {
        return reportAddress;
    }

    public void setReportAddress(String reportAddress) {
        this.reportAddress = reportAddress;
    }

    public String getReportScore() {
        return reportScore;
    }

    public void setReportScore(String reportScore) {
        this.reportScore = reportScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private  void getQuestionList(){

    }

    private void getQuestionScoreList(){

    }

}
