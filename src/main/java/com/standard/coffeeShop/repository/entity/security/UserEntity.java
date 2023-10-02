package com.standard.coffeeShop.repository.entity.security;

import com.standard.coffeeShop.repository.entity.CustomerEntity;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public  class UserEntity implements UserDetails, CredentialsContainer, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Column(name = "username")
    private String username;

    @Getter
    @Column(name = "password")
    private String password;

    @Getter
    @Singular
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<RoleEntity> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerEntity customer;

    @Getter
    @Column(name = "accountNonExpired")
    private boolean accountNonExpired = true;
    @Getter
    @Column(name = "accountNonLocked")
    private boolean accountNonLocked = true;

    @Getter
    @Column(name = "credentialNonExpired")
    private boolean credentialNonExpired = true;

    @Getter
    @Column(name = "enable")
    private boolean enable = true;

    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(RoleEntity::getAuthorities)
                .flatMap(Set::stream)
                .map(authorityEntity -> {
                    return new SimpleGrantedAuthority(authorityEntity.getPermission());
                })
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

}
