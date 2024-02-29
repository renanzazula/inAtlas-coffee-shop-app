package com.standard.coffeeshop.utils.functions.entityToDto;


import com.standard.coffeeshop.repository.entity.security.UserEntity;
import com.standard.coffeeshop.service.dto.security.UserDto;
import com.standard.coffeeshop.utils.functions.EntityToDtoAdapter;
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
