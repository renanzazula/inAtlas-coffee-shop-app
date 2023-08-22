package com.inAtlas.coffeeShop.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
@EqualsAndHashCode
@ToString
public @Data class DiscountEntity extends BaseAuditEntity {

    private static final long serialVersionUID = 320781767776608424L;


    @Column(name = "status")
    private String status;

}
