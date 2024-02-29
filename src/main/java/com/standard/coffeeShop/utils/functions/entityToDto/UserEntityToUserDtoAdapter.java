package com.standard.coffeeShop.utils.functions.entityToDto;


import com.standard.coffeeShop.repository.entity.security.UserEntity;
import com.standard.coffeeShop.service.dto.security.UserDto;
import com.standard.coffeeShop.utils.functions.EntityToDtoAdapter;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public class UserEntityToUserDtoAdapter implements Function<UserEntity, UserDto> {

    @Override
    public UserDto apply(UserEntity userEntity) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(userEntity, dto);
        dto.setAuthorities(userEntity.getAuthorities()
                                .stream()
                                    .map(EntityToDtoAdapter.authorityEntityToAuthorityDtoAdapter)
                                        .collect(Collectors.toSet()));
        return dto;
    }


}
