package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class OptionCourseVO {
    private BigInteger optionCourseId;
    private Integer optionCourseTeamNumberMin;

    public OptionCourseVO() {
    }

    @Override
    public String toString() {
        return "OptionCourseVO{" +
                "optionCourseId=" + optionCourseId +
                ", optionCourseTeamNumberMin=" + optionCourseTeamNumberMin +
                ", optionCourseTeamNumberMax=" + optionCourseTeamNumberMax +
                '}';
    }

    private Integer optionCourseTeamNumberMax;
    public OptionCourseVO(BigInteger optionCourseId, Integer optionCourseTeamNumberMin, Integer optionCourseTeamNumberMax) {
        this.optionCourseId = optionCourseId;
        this.optionCourseTeamNumberMin = optionCourseTeamNumberMin;
        this.optionCourseTeamNumberMax = optionCourseTeamNumberMax;
    }



    public BigInteger getOptionCourseId() {
        return optionCourseId;
    }

    public void setOptionCourseId(BigInteger optionCourseId) {
        this.optionCourseId = optionCourseId;
    }

    public Integer getOptionCourseTeamNumberMin() {
        return optionCourseTeamNumberMin;
    }

    public void setOptionCourseTeamNumberMin(Integer optionCourseTeamNumberMin) {
        this.optionCourseTeamNumberMin = optionCourseTeamNumberMin;
    }

    public Integer getOptionCourseTeamNumberMax() {
        return optionCourseTeamNumberMax;
    }

    public void setOptionCourseTeamNumberMax(Integer optionCourseTeamNumberMax) {
        this.optionCourseTeamNumberMax = optionCourseTeamNumberMax;
    }



}
