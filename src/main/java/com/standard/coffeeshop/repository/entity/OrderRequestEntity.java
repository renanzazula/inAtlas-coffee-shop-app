package com.standard.coffeeshop.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_request")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public @Data class OrderRequestEntity extends BaseAuditEntity {

    private static final long serialVersionUID = -1728370995890730077L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, insertable = true, updatable = false, nullable = true)
    private String id;

    @CreationTimestamp
    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusOrderEnum status;

    @Column(name = "total_quantity")
    private Long totalQuantity;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ColumnDefault(value = "0")
    @Column(name = "discount")
    private Double totalDiscount;

    @ColumnDefault(value = "0")
    @Column(name = "promotion_discount")
    private Double promotionDiscount;

    @ManyToOne
    private CustomerEntity customer;

    @OneToMany(mappedBy = "orderRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    private List<OrderRequestItemEntity> orderItems = new ArrayList<>();

    public void addOrderItem(OrderRequestItemEntity orderItem){
        if(orderItems != null){
            orderItem.setOrderRequest(this);
            orderItems.add(orderItem);
        }
    }

    public void removeOrderItem(OrderRequestItemEntity orderItem){
        if(orderItems != null){
            orderItems.remove(orderItem);
        }
    }

}
