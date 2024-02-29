package com.standard.coffeeshop.utils.functions.dto;


import com.standard.coffeeshop.controller.domain.security.Authority;
import com.standard.coffeeshop.service.dto.security.AuthorityDto;
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
