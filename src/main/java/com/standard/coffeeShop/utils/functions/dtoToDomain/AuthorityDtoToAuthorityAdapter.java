package com.standard.coffeeShop.utils.functions.dtoToDomain;


import com.standard.coffeeShop.controller.domain.security.Authority;
import com.standard.coffeeShop.service.dto.security.AuthorityDto;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class AuthorityDtoToAuthorityAdapter implements Function<AuthorityDto, Authority> {

    @Override
    public Authority apply(AuthorityDto authorityDto) {
        Authority domain = new Authority();
        BeanUtils.copyProperties(authorityDto, domain);
        return domain;
    }
}
