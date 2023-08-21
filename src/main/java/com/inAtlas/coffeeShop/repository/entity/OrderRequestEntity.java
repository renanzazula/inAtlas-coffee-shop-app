package com.inAtlas.coffeeShop.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = "orderHasProductEntity")
@Entity
@Table(name = "order_request")
public @Data
class OrderRequestEntity extends BaseAuditEntity {

    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private String status;

    @Column(name = "total_quantity")
    private Long totalQuantity;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ColumnDefault(value = "0")
    @Column(name = "discount")
    private Double totalDiscount;

    @NotNull
    @OneToMany(mappedBy = "orderRequest", cascade = CascadeType.ALL)
    private Set<OrderHasProductEntity> orderHasProduct = new HashSet<>();
}
