package com.standard.coffeeshop.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "combo_item")
public @Data class ComboItemEntity {

    private static final long serialVersionUID = 3722675736039704604L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discount_item_id")
    private String id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_id", referencedColumnName = "id", nullable = false ,updatable = false)
    private DiscountEntity discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false ,updatable = false)
    private ProductEntity product;




}
