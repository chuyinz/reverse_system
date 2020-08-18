package com.example.cms.vo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class ExportSeminarScoreVO {
    Integer roundSerial;
    Integer teamSerial;
    BigDecimal totalScore;
    BigDecimal presentationScore;
    BigDecimal questionScore;
    BigDecimal reportScore;

    public ExportSeminarScoreVO() {
    }

    @Override
    public String toString() {
        return "ExportSeminarScoreVO{" +
                "roundSerial=" + roundSerial +
                ", teamSerial=" + teamSerial +
                ", totalScore=" + totalScore +
                ", presentationScore=" + presentationScore +
                ", questionScore=" + questionScore +
                ", reportScore=" + reportScore +
                '}';
    }

    public ExportSeminarScoreVO(Integer roundSerial, Integer teamSerial, BigDecimal totalScore, BigDecimal presentationScore, BigDecimal questionScore, BigDecimal reportScore) {
        this.roundSerial = roundSerial;
        this.teamSerial = teamSerial;
        this.totalScore = totalScore;
        this.presentationScore = presentationScore;
        this.questionScore = questionScore;
        this.reportScore = reportScore;
    }


    public  Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial( Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public BigDecimal getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(BigDecimal presentationScore) {
        this.presentationScore = presentationScore;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }

    public Map<String,Object> convertThis2Map() {
        Map<String,Object> map = new HashMap<String,Object>(1);
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String filedName = fields[i].getName();
            if("serialVersionUID".equals(filedName)){
                continue;
            }
            StringBuilder sb = new StringBuilder("get");
            sb.append(filedName.substring(0, 1).toUpperCase());
            sb.append(filedName.substring(1, filedName.length()));
            try {
                Method method = this.getClass().getMethod(sb.toString());
                Object object = method.invoke(this);
                if(object != null){
                    map.put(filedName, object);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("MAX_SIZE", 100);
        return map;
    }




}
