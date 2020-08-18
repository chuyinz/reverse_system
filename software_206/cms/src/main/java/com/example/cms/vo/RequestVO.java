package com.example.cms.vo;

import java.util.List;

/**
 * @author zyy
 * @date 2018-12-25
 */
public class RequestVO {
    public List<TeamShareRequestVO> getTeamShareRequestVOList() {
        return teamShareRequestVOList;
    }

    public void setTeamShareRequestVOList(List<TeamShareRequestVO> teamShareRequestVOList) {
        this.teamShareRequestVOList = teamShareRequestVOList;
    }

    public List<TeamValidRequestVO> getTeamValidRequestVOList() {
        return teamValidRequestVOList;
    }

    public void setTeamValidRequestVOList(List<TeamValidRequestVO> teamValidRequestVOList) {
        this.teamValidRequestVOList = teamValidRequestVOList;
    }



    public RequestVO(List<TeamShareRequestVO> teamShareRequestVOList, List<TeamValidRequestVO> teamValidRequestVOList) {
        this.teamShareRequestVOList = teamShareRequestVOList;
        this.teamValidRequestVOList = teamValidRequestVOList;
    }

    public RequestVO() {
    }

    private List<TeamValidRequestVO>teamValidRequestVOList;
    private List<TeamShareRequestVO>teamShareRequestVOList;

    @Override
    public String toString() {
        return "RequestVO{" +
                "teamValidRequestVOList=" + teamValidRequestVOList +
                ", teamShareRequestVOList=" + teamShareRequestVOList +
                '}';
    }
}
