package com.example.cms.entity;

import java.math.BigInteger;

/**
 * @author XIE
 * @date 2018/11/30
 */

public class User {
    private BigInteger id;
    private String account;
    private String name;
    private String password;
    private Integer isActive;
    private Integer role;
    private String email;

    public User(){

    }

    public User(BigInteger id, String account, String name, String password, Integer isActive, Integer role, String email) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setActive(Integer active) {
        isActive = active;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
