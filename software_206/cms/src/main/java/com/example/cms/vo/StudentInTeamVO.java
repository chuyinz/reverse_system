package com.example.cms.vo;

import java.math.BigInteger;
/**
 * @author szw
 * @date 2018-12-25
 */
public class StudentInTeamVO {
    private BigInteger id;
    private String account;
    private String name;

    @Override
    public String toString() {
        return "StudentInTeamVO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public StudentInTeamVO()
    {

    }
    public StudentInTeamVO(BigInteger id, String account, String name) {
        this.id = id;
        this.account = account;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
