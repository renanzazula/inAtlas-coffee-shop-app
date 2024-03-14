package com.standard.coffeeshop.utils.functions.dto;


import com.standard.coffeeshop.controller.domain.security.User;
import com.standard.coffeeshop.service.dto.security.AuthorityDto;
import com.standard.coffeeshop.service.dto.security.UserDto;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
import org.springframework.beans.BeanUtils;

import java.util.Comparator;
import java.util.function.Function;

public class UserDtoToUserAdapter implements Function<UserDto, User> {

    @Override
    public User apply(UserDto userDto) {
        User domain = new User();
        BeanUtils.copyProperties(userDto, domain);
        domain.setAuthorities(userDto.getAuthorities()
                .stream()
                .sorted(Comparator.comparing(AuthorityDto::getRole))
                .map(DtoToDomainAdapter.authorityDtoToUserAdapter)
                .toList());
        return domain;
    }
}
