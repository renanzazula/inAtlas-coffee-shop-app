package com.standard.coffeeshop.repository.entity.security;

import com.standard.coffeeshop.repository.entity.CustomerEntity;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity implements UserDetails, CredentialsContainer, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "username")
    private String username;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserPasswordEntity userPassword;

    @Singular
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<RoleEntity> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerEntity customer;

    @Builder.Default
    @Column(name = "accountNonExpired")
    private boolean accountNonExpired = true;

    @Builder.Default
    @Column(name = "accountNonLocked")
    private boolean accountNonLocked = true;

    @Builder.Default
    @Column(name = "credentialNonExpired")
    private boolean credentialNonExpired = true;

    @Builder.Default
    @Column(name = "enable")
    private boolean enable = true;

    @Builder.Default
    @Column(name = "use_google_2fa")
    private boolean useGoogle2fa = false;

    @Column(name = "user_google_2_fa_secret")
    private String userGoogle2faSecret;

    @Getter
    @Builder.Default
    @Transient
    private boolean userGoogle2FaRequired = false;

    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set = new HashSet<>();
        for (RoleEntity role : this.roles) {
            Set<AuthorityEntity> authorities = role.getAuthorities();
            for (AuthorityEntity authorityEntity : authorities) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authorityEntity.getPermission());
                set.add(simpleGrantedAuthority);
            }
        }
        return set;
    }

    @Override
    public String getPassword() {
        return userPassword.getPassword();
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
        // TODO document why this method is empty
    }

}
