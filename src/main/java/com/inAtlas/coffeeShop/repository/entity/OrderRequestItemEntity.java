package com.inAtlas.coffeeShop.repository.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_request_request_item")
public class OrderRequestItemEntity {

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

    public Long getId() {
        return Id;
    }

    public void setId(Long orderRequestHasProductId) {
        this.Id = orderRequestHasProductId;
    }

    public OrderRequestEntity getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequestEntity orderRequest) {
        this.orderRequest = orderRequest;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Double priceUnit) {
        this.priceUnit = priceUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequestItemEntity that = (OrderRequestItemEntity) o;
        return Objects.equals(Id, that.Id) && Objects.equals(orderRequest, that.orderRequest) && Objects.equals(product, that.product) && Objects.equals(discount, that.discount) && Objects.equals(priceUnit, that.priceUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, orderRequest, product, discount, priceUnit);
    }

    @Override
    public String toString() {
        return "OrderRequestItemsEntity{" +
                "Id=" + Id +
                ", orderRequest=" + orderRequest +
                ", product=" + product +
                ", discount=" + discount +
                ", priceUnit=" + priceUnit +
                '}';
    }
}
