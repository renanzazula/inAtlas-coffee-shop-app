package com.inAtlas.coffeeShop.repository.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "order_request_has_product")
public @Data class OrderHasProductEntity  extends BaseAuditEntity{

    @ManyToOne
    @JoinColumn(name = "order_request_id")
    private OrderRequestEntity orderRequest;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private Integer quantity;

    @ColumnDefault(value = "0")
    @Column(name = "discount")
    private Double discount;

}
