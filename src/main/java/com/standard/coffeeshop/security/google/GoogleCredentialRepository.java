package com.standard.coffeeshop.security.google;

import com.standard.coffeeshop.repository.entity.security.UserEntity;
import com.standard.coffeeshop.repository.security.UserRepository;
import com.warrenstrange.googleauth.ICredentialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleCredentialRepository implements ICredentialRepository {

    private final UserRepository userRepository;

    @Override
    public String getSecretKey(String userName) {
        UserEntity user = userRepository.findByUsername(userName).orElseThrow();
        return user.getUserGoogle2faSecret();
    }

    @Override
    public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
        UserEntity user = userRepository.findByUsername(userName).orElseThrow();
        user.setUserGoogle2faSecret(secretKey);
        user.setUseGoogle2fa(true);
        userRepository.save(user);
    }
}
