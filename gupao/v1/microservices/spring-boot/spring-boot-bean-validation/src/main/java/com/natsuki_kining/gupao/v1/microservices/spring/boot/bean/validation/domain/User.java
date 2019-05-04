package com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.domain;


import com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.validation.constraints.ValidCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class User {

    @Max(value = 1000)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @ValidCardNumber
    private String cardNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
