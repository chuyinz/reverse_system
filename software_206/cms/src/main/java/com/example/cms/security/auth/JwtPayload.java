package com.example.cms.security.auth;

import com.example.cms.entity.User;

import java.math.BigInteger;

/**
 * @author xzy
 * @date 2018-12-17
 */
public class JwtPayload {
    private BigInteger id;
    private String account;
    private String role;
    private String name;
    private Integer isActived;
    private Long exp;
    public JwtPayload() {

    }

    public JwtPayload(BigInteger id, String account, String role, String name, Integer isActived, Long exp) {
        this.id = id;
        this.account = account;
        this.role = role;
        this.name = name;
        this.isActived = isActived;
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

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(int isActived) {
        this.isActived = isActived;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        String student="student";
        String teacher="teacher";
        if (role.equals(student)) {
            user.setRole(0);
        } else if(role.equals(teacher)){
            user.setRole(1);
        }
        return user;
    }
}

