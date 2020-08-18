package com.example.cms.entity;


import java.math.BigInteger;

/**
 * @author shaoziwei
 * @date 2018/12/16
 */

public class Team {
   private BigInteger id;

   public Team()
   {

   }

    public Team(BigInteger id, BigInteger klassId, BigInteger courseId, BigInteger leaderId, String teamName, int teamSerial, int klassSerial, int status) {
        this.id = id;
        this.klassId = klassId;
        this.courseId = courseId;
        this.leaderId = leaderId;
        this.teamName = teamName;
        this.teamSerial = teamSerial;
        this.klassSerial = klassSerial;
        this.status = status;
    }

    private BigInteger klassId;
   private BigInteger courseId;
   private BigInteger leaderId;
   private String teamName;
   private int teamSerial;

    public int getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(int klassSerial) {
        this.klassSerial = klassSerial;
    }

    private int klassSerial;
   private int status;



    public BigInteger getId() {
        return id;
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

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(BigInteger leaderId) {
        this.leaderId = leaderId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(int teamSerial) {
        this.teamSerial = teamSerial;
    }

    public int getStatus() {
        return status;
    }

    public void setIsStatus(int isValid) {
        this.status = isValid;
    }
}
