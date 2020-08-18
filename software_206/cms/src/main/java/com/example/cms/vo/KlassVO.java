package com.example.cms.vo;

import com.example.cms.entity.Klass;

import java.math.BigInteger;

/**
 * @author xzy
 * @date 2018-12-25
 */
public class KlassVO {
    private BigInteger id;
    private String name;

    @Override
    public String toString() {
        return "KlassVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public KlassVO(Klass klass) {
        this.id = klass.getId();
        this.name =klass.getGrade()+"-("+String.valueOf(klass.getKlassSerial())+")";
    }

    public KlassVO(BigInteger id, String name) {
        this.id = id;
        this.name = name;
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
