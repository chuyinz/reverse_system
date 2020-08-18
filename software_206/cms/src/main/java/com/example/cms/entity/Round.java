package com.example.cms.entity;

import java.math.BigInteger;

/**
 * @author shaoziwei
 * @date 2018/11/30
 */
/**
 * @author shaoziwei
 * @date 2018/12/16
 */
public class Round
{
    private BigInteger id;
    private BigInteger courseId;
    private Integer serial;
    private Integer presentationScoreMethod;
    private Integer reportScoreMethod;
    private Integer questionScoreMethod;

    public Round(){

    }

    public Round(BigInteger courseId, Integer serial, Integer presentationScoreMethod, Integer reportScoreMethod, Integer questionScoreMethod) {
        this.courseId = courseId;
        this.serial = serial;
        this.presentationScoreMethod = presentationScoreMethod;
        this.reportScoreMethod = reportScoreMethod;
        this.questionScoreMethod = questionScoreMethod;
    }

    public Round(BigInteger courseId, Integer serial) {
        this.courseId = courseId;
        this.serial = serial;
        this.presentationScoreMethod = 0;
        this.reportScoreMethod = 0;
        this.questionScoreMethod = 0;
    }

    public Round(BigInteger id, BigInteger courseId, Integer serial, Integer presentationScoreMethod, Integer reportScoreMethod, Integer questionScoreMethod) {
        this.id = id;
        this.courseId = courseId;
        this.serial = serial;
        this.presentationScoreMethod = presentationScoreMethod;
        this.reportScoreMethod = reportScoreMethod;
        this.questionScoreMethod = questionScoreMethod;
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

    public Integer getPresentationScoreMethod() {
        return presentationScoreMethod;
    }

    public void setPresentationScoreMethod(Integer presentationScoreMethod) {
        this.presentationScoreMethod = presentationScoreMethod;
    }

    public Integer getReportScoreMethod() {
        return reportScoreMethod;
    }

    public void setReportScoreMethod(Integer reportScoreMethod) {
        this.reportScoreMethod = reportScoreMethod;
    }

    public Integer getQuestionScoreMethod() {
        return questionScoreMethod;
    }

    public void setQuestionScoreMethod(Integer questionScoreMethod) {
        this.questionScoreMethod = questionScoreMethod;
    }
}
