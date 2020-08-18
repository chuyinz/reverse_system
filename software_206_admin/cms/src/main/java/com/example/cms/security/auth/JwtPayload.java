package com.example.cms.security.auth;

import com.example.cms.entity.Admin;
import com.example.cms.entity.User;

import java.math.BigInteger;

/**
 * @author xzy
 * @date 2018-12-17
 */
public class JwtPayload {
    private BigInteger id;
    private String account;
    private Long exp;
    public JwtPayload() {

    }

    public JwtPayload(BigInteger id, String account, Long exp) {
        this.id = id;
        this.account = account;
        this.exp = exp;
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

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Admin toAdmin() {
        Admin admin = new Admin();
        admin.setId(id);
        return admin;
    }
}

