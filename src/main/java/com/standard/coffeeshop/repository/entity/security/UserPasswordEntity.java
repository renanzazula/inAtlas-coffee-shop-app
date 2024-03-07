package com.standard.coffeeshop.repository.entity.security;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_pass")
public class UserPasswordEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "userPassword")
    private UserEntity user;

}
