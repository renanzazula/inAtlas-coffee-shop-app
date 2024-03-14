package com.standard.coffeeshop.controller.user;

import com.standard.coffeeshop.controller.domain.security.User;
import com.standard.coffeeshop.repository.entity.security.UserEntity;
import com.standard.coffeeshop.service.dto.security.UserDto;
import com.standard.coffeeshop.utils.ConstantsApi;
import com.standard.coffeeshop.utils.functions.DtoToDomainAdapter;
import com.standard.coffeeshop.utils.functions.EntityToDtoAdapter;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "User Authentication controller", tags = "authentication")
@Slf4j
@RestController
@RequestMapping(UserController.USER)
@RequiredArgsConstructor
public class UserController {

    public static final String USER = ConstantsApi.USER;

    @GetMapping()
    public ResponseEntity<User> getUser() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = EntityToDtoAdapter.userEntityToUserDtoAdapter.apply(userEntity);
        User user = DtoToDomainAdapter.userDtoToUserAdapter.apply(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
