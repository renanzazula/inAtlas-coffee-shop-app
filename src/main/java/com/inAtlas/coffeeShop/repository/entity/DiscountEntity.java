package com.inAtlas.coffeeShop.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
public @Data class DiscountEntity extends BaseAuditEntity {


    @Column(name = "status")
    private String status;

}
