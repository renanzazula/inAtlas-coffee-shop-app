package com.standard.coffeeShop.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "combo_item")
public class ComboItemEntity {

    private static final long serialVersionUID = 3722675736039704604L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discount_item_id")
    private Long Id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id", referencedColumnName = "id", nullable = false ,updatable = false)
    private DiscountEntity discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false ,updatable = false)
    private ProductEntity product;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public DiscountEntity getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountEntity discountEntity) {
        this.discount = discountEntity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }


}
