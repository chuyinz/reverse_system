package com.example.cms.vo;

import com.example.cms.entity.User;

import java.math.BigInteger;

public class UserVO {
    BigInteger id;
    String account;
    String name;
    String email;

    public UserVO(BigInteger id, String account, String name, String email) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.email = email;
    }

    public UserVO(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.name = user.getName();
        this.email = user.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
