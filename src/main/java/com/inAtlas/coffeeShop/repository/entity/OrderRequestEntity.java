package com.inAtlas.coffeeShop.repository.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "order_request")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class OrderRequestEntity extends BaseAuditEntity {

    private static final long serialVersionUID = -1728370995890730077L;

    public OrderRequestEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, insertable = true, updatable = false, nullable = true)
    private Long id;

    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date;

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

    @OneToMany(mappedBy = "orderRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    private List<OrderRequestItemsEntity> orderItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public StatusOrderEnum getStatus() {
        return status;
    }

    public void setStatus(StatusOrderEnum status) {
        this.status = status;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Double getPromotionDiscount() {
        return promotionDiscount;
    }

    public void setPromotionDiscount(Double promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }

    public List<OrderRequestItemsEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderRequestItemsEntity> orderHasProduct) {
        this.orderItems = orderHasProduct;
    }

    public void addOrderItem(OrderRequestItemsEntity orderItem){
        if(orderItems != null){
            if(orderItems == null){
                orderItems = new ArrayList<>();
            }
            orderItem.setOrderRequest(this);
            orderItems.add(orderItem);
        }
    }

    public void removeOrderHasProduct(OrderRequestItemsEntity orderItem){
        if(orderItems != null){
//            orderItem.setOrderRequest(this);
            orderItems.remove(orderItem);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderRequestEntity entity = (OrderRequestEntity) o;
        return Objects.equals(id, entity.id) && Objects.equals(date, entity.date) && status == entity.status && Objects.equals(totalQuantity, entity.totalQuantity) && Objects.equals(totalAmount, entity.totalAmount) && Objects.equals(totalDiscount, entity.totalDiscount) && Objects.equals(promotionDiscount, entity.promotionDiscount) && Objects.equals(orderItems, entity.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, date, status, totalQuantity, totalAmount, totalDiscount, promotionDiscount, orderItems);
    }

    @Override
    public String toString() {
        return "OrderRequestEntity{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", totalQuantity=" + totalQuantity +
                ", totalAmount=" + totalAmount +
                ", totalDiscount=" + totalDiscount +
                ", promotionDiscount=" + promotionDiscount +
                ", orderHasProduct=" + orderItems +
                '}';
    }
}
