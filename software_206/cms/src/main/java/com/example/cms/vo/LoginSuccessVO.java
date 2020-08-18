package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author XIE
 * @date 2018/12/17
 */
public class LoginSuccessVO {

    private BigInteger id;
    private String role;
    private String name;
    private String jwt;
    private String account;
    private Integer isActive;
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }



    public LoginSuccessVO() {
    }

    public LoginSuccessVO(BigInteger id, String role, String name, String jwt) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.jwt = jwt;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setJwt(String header, String payload, String signature) {
        this.jwt = header + '.' + payload + '.' + signature;
    }

    @Override
    public String toString(){
        return null;
    }
}
