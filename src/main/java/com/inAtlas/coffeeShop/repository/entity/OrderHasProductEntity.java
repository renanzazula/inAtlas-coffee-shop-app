package com.inAtlas.coffeeShop.repository.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "order_request_has_product")
@ToString
public @Data class OrderHasProductEntity  {

    private static final long serialVersionUID = 7167425684167438414L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_request_has_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_request_id",  updatable = false)
    private OrderRequestEntity orderRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", updatable = false)
    private ProductEntity product;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "price_unit")
    private Double priceUnit;

}
