package com.example.cms.entity;

import com.example.cms.vo.Team1VO;

import java.math.BigInteger;
import java.util.List;


/**
 * @author shaoziwei
 * @date 2018/12/13
 */
public class MemberLimitStrategy {
    private BigInteger id;
    private Integer minMember;
    private Integer maxMember;

    public boolean isValid(Team1VO team1VO)
    {
        int count=0;
        List<User>members= team1VO.getMembers();
        for(User member:members)
        {
            count++;
        }
        if(minMember<=count&&maxMember>=count)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public MemberLimitStrategy(BigInteger id, Integer minMember, Integer maxMember) {
        this.id = id;
        this.minMember = minMember;
        this.maxMember = maxMember;
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }
}
