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

    @JsonFormat(pattern = Constants.PATTERN_DATE_FORMAT)
    private Date dateCreate;

    @JsonFormat(pattern = Constants.PATTERN_TIME_FORMAT)
    private Date timeCreate;

    @JsonFormat(pattern = Constants.PATTERN_DATE_FORMAT)
    private Date dateUpdate;

    @JsonFormat(pattern = Constants.PATTERN_TIME_FORMAT)
    private Date timeUpdate;

    private Long whoCreate;
    private Long whoUpdate;

    public Base() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    @JsonIgnore
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    @JsonIgnore
    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    @JsonIgnore
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    @JsonIgnore
    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public Long getWhoCreate() {
        return whoCreate;
    }

    @JsonIgnore
    public void setWhoCreate(Long whoCreate) {
        this.whoCreate = whoCreate;
    }

    public Long getWhoUpdate() {
        return whoUpdate;
    }

    @JsonIgnore
    public void setWhoUpdate(Long whoUpdate) {
        this.whoUpdate = whoUpdate;
    }
}
