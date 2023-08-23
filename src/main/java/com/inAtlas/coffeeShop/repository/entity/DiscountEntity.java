package com.inAtlas.coffeeShop.repository.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "discount")
@EqualsAndHashCode
@ToString
public @Data class DiscountEntity extends BaseAuditEntity {

    private static final long serialVersionUID = 320781767776608424L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

}
