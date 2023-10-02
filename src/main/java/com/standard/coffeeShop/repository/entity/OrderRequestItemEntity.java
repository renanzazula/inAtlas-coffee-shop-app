package com.standard.coffeeShop.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_request_request_item")
public @Data class OrderRequestItemEntity {

    private static final long serialVersionUID = 7167425684167438414L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_request_items_id")
    private Long Id;

    @ManyToOne(targetEntity = OrderRequestEntity.class)
    @JoinColumn(name = "order_request_id", referencedColumnName = "id", nullable = false, updatable = false)
    private OrderRequestEntity orderRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false ,updatable = false)
    @OrderBy("id")
    private ProductEntity product;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "price_unit")
    private Double priceUnit;

}
