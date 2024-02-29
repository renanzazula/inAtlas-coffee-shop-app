package com.standard.coffeeshop.utils.functions.dtoToDomain;


import com.standard.coffeeshop.controller.domain.security.User;
import com.standard.coffeeshop.service.dto.security.UserDto;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
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
