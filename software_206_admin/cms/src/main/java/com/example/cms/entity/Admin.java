package com.example.cms.entity;


/**
 * @author shaoziwei
 * @date 2018/11/30
 */

import java.math.BigInteger;

/**
 * @author shaoziwei
 * @date 2018/12/13
 */
public class Admin {
    private BigInteger id;
    private String account;
    private String password;

    public Admin() {
    }

    public Admin(BigInteger id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


