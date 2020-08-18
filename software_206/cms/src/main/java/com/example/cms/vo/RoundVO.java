package com.example.cms.vo;

import com.example.cms.entity.Round;

import java.math.BigInteger;
import java.util.List;
/**
 * @author xzy
 * @date 2018-12-25
 */
public class RoundVO {
    private BigInteger id;
    private BigInteger courseId;
    private Integer serial;
    private String presentationScoreMethod;
    private String reportScoreMethod;
    private String questionScoreMethod;
    private List<KlassRoundVO> klassRoundVOList;

    @Override
    public String toString() {
        return "RoundVO{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", serial=" + serial +
                ", presentationScoreMethod='" + presentationScoreMethod + '\'' +
                ", reportScoreMethod='" + reportScoreMethod + '\'' +
                ", questionScoreMethod='" + questionScoreMethod + '\'' +
                ", klassRoundVOList=" + klassRoundVOList +
                '}';
    }

    public RoundVO(Round round, List<KlassRoundVO> klassRoundVOList) {
        this.id = round.getId();
        this.courseId = round.getCourseId();
        this.serial = round.getSerial();
        String s;
        switch (round.getPresentationScoreMethod()){
            case 1:s="最高分";break;
            case 2:s="最低分";break;
            default:s="平均分";break;
        }
        this.presentationScoreMethod = s;
        switch (round.getReportScoreMethod()){
            case 1:s="最高分";break;
            case 2:s="最低分";break;
            default:s="平均分";break;
        }
        this.reportScoreMethod =s;
        switch (round.getQuestionScoreMethod()){
            case 1:s="最高分";break;
            case 2:s="最低分";break;
            default:s="平均分";break;
        }
        this.questionScoreMethod = s;
        this.klassRoundVOList = klassRoundVOList;
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

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getPresentationScoreMethod() {
        return presentationScoreMethod;
    }

    public void setPresentationScoreMethod(String presentationScoreMethod) {
        this.presentationScoreMethod = presentationScoreMethod;
    }

    public String getReportScoreMethod() {
        return reportScoreMethod;
    }

    public void setReportScoreMethod(String reportScoreMethod) {
        this.reportScoreMethod = reportScoreMethod;
    }

    public String getQuestionScoreMethod() {
        return questionScoreMethod;
    }

    public void setQuestionScoreMethod(String questionScoreMethod) {
        this.questionScoreMethod = questionScoreMethod;
    }

    public List<KlassRoundVO> getKlassRoundVOList() {
        return klassRoundVOList;
    }

    public void setKlassRoundVOList(List<KlassRoundVO> klassRoundVOList) {
        this.klassRoundVOList = klassRoundVOList;
    }
}
