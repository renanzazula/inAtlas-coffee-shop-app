package com.standard.coffeeshop.repository.entity;

import com.standard.coffeeshop.repository.entity.security.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public @Data class CustomerEntity  extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(length = 36, columnDefinition = "varchar")
    private UUID apiKey;

    @OneToMany(mappedBy = "customer")
    private Set<OrderRequestEntity> orderRequests;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<UserEntity> users;


}
