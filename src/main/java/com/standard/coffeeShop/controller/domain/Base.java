package com.standard.coffeeShop.controller.domain;

import java.io.Serializable;


public class Base implements Serializable {

    private Long id;

    public Base() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
