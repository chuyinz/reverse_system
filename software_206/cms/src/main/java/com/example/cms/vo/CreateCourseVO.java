package com.example.cms.vo;

import java.util.List;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class CreateCourseVO {

    private String name;
    private String intro;
    private Integer presentationPercentage;
    private Integer questionPercentage;
    private Integer reportPercentage;
    private String teamStartTime;
    private String teamEndTime;

    @Override
    public String toString() {
        return "CreateCourseVO{" +
                "name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", presentationPercentage=" + presentationPercentage +
                ", questionPercentage=" + questionPercentage +
                ", reportPercentage=" + reportPercentage +
                ", teamStartTime='" + teamStartTime + '\'' +
                ", teamEndTime='" + teamEndTime + '\'' +
                ", condition=" + condition +
                ", teamNumberMin=" + teamNumberMin +
                ", teamNumberMax=" + teamNumberMax +
                ", optionCourseVOList=" + optionCourseVOList +
                ", conflictCourseList=" + conflictCourseList +
                '}';
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    private Integer condition;





    private Integer teamNumberMin;
    private Integer teamNumberMax;
   private List<OptionCourseVO>optionCourseVOList;
    private List<ConflictCourseVO>conflictCourseList;


    public List<OptionCourseVO> getOptionCourseVOList() {
        return optionCourseVOList;
    }

    public void setOptionCourseVOList(List<OptionCourseVO> optionCourseVOList) {
        this.optionCourseVOList = optionCourseVOList;
    }

    public List<ConflictCourseVO> getConflictCourseList() {
        return conflictCourseList;
    }

    public void setConflictCourseList(List<ConflictCourseVO> conflictCourseList) {
        this.conflictCourseList = conflictCourseList;
    }




    public String getName() {
        return name;
    }

    public void setCourseName(String courseName) {
        this.name = courseName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntroduction(String introduction) {
        this.intro = introduction;
    }

    public Integer getPresentationPercentage() {
        return presentationPercentage;
    }

    public void setPresentationPercentage(Integer presentationPercentage) {
        this.presentationPercentage = presentationPercentage;
    }

    public Integer getQuestionPercentage() {
        return questionPercentage;
    }

    public void setQuestionPercentage(Integer questionPercentage) {
        this.questionPercentage = questionPercentage;
    }

    public Integer getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(Integer reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public String getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(String teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public String getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(String teamEndTime) {
        this.teamEndTime = teamEndTime;
    }


    public Integer getTeamNumberMin() {
        return teamNumberMin;
    }

    public void setTeamNumberMin(Integer teamNumberMin) {
        this.teamNumberMin = teamNumberMin;
    }

    public Integer getTeamNumberMax() {
        return teamNumberMax;
    }

    public void setTeamNumberMax(Integer teamNumberMax) {
        this.teamNumberMax = teamNumberMax;
    }




}
