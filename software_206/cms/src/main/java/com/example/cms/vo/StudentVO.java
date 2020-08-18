package com.example.cms.vo;
/**
 * @author zyy
 * @date 2018-12-25
 */
public class StudentVO {
    public StudentVO() {
    }

    @Override
    public String toString() {
        return "StudentVO{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private String account;
    private String name;
    public StudentVO(String account, String name) {
        this.account = account;
        this.name = name;
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
