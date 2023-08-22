package com.inAtlas.coffeeShop.controller.domain;

import com.fasterxml.jackson.annotation.*;
import com.inAtlas.coffeeShop.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


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
