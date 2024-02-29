package com.standard.coffeeShop.utils.functions.dtoToDomain;


import com.standard.coffeeShop.controller.domain.security.User;
import com.standard.coffeeShop.service.dto.security.UserDto;
import com.standard.coffeeShop.utils.functions.DtoToDomainAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public class UserDtoToUserAdapter implements Function<UserDto, User> {

    @Override
    public User apply(UserDto userDto) {
        User domain = new User();
        BeanUtils.copyProperties(userDto, domain);
        domain.setAuthorities(userDto.getAuthorities()
                .stream()
                .sorted((r1, r2) -> {
                    return r1.getRole().compareTo(r2.getRole());
                })
                .map(DtoToDomainAdapter.authorityDtoToUserAdapter)
                .collect(Collectors.toList()));
        return domain;
    }
}
