package com.inAtlas.coffeeShop.repository.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_request")
@EqualsAndHashCode(exclude = {"orderHasProduct"})
@ToString
public @Data
class OrderRequestEntity extends BaseAuditEntity {

    private static final long serialVersionUID = -1728370995890730077L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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

    @NotNull
    @OneToMany(mappedBy = "orderRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderHasProductEntity> orderHasProduct = new HashSet<>();
}
