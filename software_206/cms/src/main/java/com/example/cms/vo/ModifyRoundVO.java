package com.example.cms.vo;

import java.util.List;

/**
 * @author xzy
 * @date 2018-12-25
 */
public class ModifyRoundVO {
    private String presentationScoreMethod;
    private String reportScoreMethod;
    private String questionScoreMethod;
    private List<KlassRoundVO> klassRoundVOList;

    public ModifyRoundVO(String presentationScoreMethod, String reportScoreMethod, String questionScoreMethod, List<KlassRoundVO> klassRoundVOList) {
        this.presentationScoreMethod = presentationScoreMethod;
        this.reportScoreMethod = reportScoreMethod;
        this.questionScoreMethod = questionScoreMethod;
        this.klassRoundVOList = klassRoundVOList;
    }

    @Override
    public String toString() {
        return "ModifyRoundVO{" +
                "presentationScoreMethod='" + presentationScoreMethod + '\'' +
                ", reportScoreMethod='" + reportScoreMethod + '\'' +
                ", questionScoreMethod='" + questionScoreMethod + '\'' +
                ", klassRoundVOList=" + klassRoundVOList +
                '}';
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
