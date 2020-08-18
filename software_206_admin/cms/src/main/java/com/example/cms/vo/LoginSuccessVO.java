package com.example.cms.vo;

import java.math.BigInteger;

/**
 * @author XIE
 * @date 2018/12/17
 */
public class LoginSuccessVO {

    private BigInteger id;
    private String account;
    private String jwt;
    public String getAccount() {
        return account;
    }

    public LoginSuccessVO(BigInteger id, String account, String jwt) {
        this.id = id;
        this.account = account;
        this.jwt = jwt;
    }

    public LoginSuccessVO() {
    }

    public void setJwt(String header, String payload, String signature) {
        this.jwt = header + '.' + payload + '.' + signature;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString(){
        return null;
    }
}
