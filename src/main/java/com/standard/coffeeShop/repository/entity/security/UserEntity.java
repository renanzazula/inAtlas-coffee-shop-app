package com.standard.coffeeShop.repository.entity.security;

import lombok.Getter;
import lombok.Singular;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

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
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<RoleEntity> roles;

    @Transient
    private Set<AuthorityEntity> authorities;

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


    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialNonExpired(boolean credentialNonExpired) {
        this.credentialNonExpired = credentialNonExpired;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Set<AuthorityEntity> getAuthorities() {
        return this.roles.stream()
                .map(RoleEntity::getAuthorities)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

}
