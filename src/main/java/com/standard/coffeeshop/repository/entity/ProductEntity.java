package com.standard.coffeeshop.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public @Data class ProductEntity extends BaseAuditEntity {

    private static final long serialVersionUID = -7008578354674160248L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @NotNull
    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "status")
    private String status;

    @ColumnDefault(value = "0")
    @Column(name = "quantity")
    private Long quantity;

    @ColumnDefault(value = "0")
    @Column(name = "price_unit")
    private Double priceUnit;


}
