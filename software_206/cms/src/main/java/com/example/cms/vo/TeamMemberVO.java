package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author szw
 * @date 2018-12-25
 */
public class TeamMemberVO {
    private BigInteger id;
    private String name;

    @Override
    public String toString() {
        return "TeamMemberVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public TeamMemberVO(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeamMemberVO()
    {

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
