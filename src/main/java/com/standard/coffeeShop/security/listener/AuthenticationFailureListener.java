package com.standard.coffeeShop.security.listener;

import com.standard.coffeeShop.repository.entity.security.LoginFailureEntity;
import com.standard.coffeeShop.repository.entity.security.UserEntity;
import com.standard.coffeeShop.repository.security.LoginFailureRepository;
import com.standard.coffeeShop.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener {

    private final LoginFailureRepository loginFailureRepository;
    private final UserRepository userRepository;

    @EventListener
    public void failureListen(AuthenticationFailureBadCredentialsEvent event){
        log.debug("Login failure ");
        if(event.getSource() instanceof UsernamePasswordAuthenticationToken){
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getSource();
            LoginFailureEntity.LoginFailureEntityBuilder builder = LoginFailureEntity.builder();

            if(token.getPrincipal() instanceof String){
                builder.username((String) token.getPrincipal());
                userRepository.findByUsername((String) token.getPrincipal()).ifPresent(builder::user);
                log.debug("Attempted username: " + token.getPrincipal());
            }

            if (token.getDetails() instanceof WebAuthenticationDetails){
                WebAuthenticationDetails details = (WebAuthenticationDetails) token.getDetails();
                builder.sourceIp(details.getRemoteAddress());
                log.debug("Source IP: " + details.getRemoteAddress());
            }

            LoginFailureEntity loginFailure = loginFailureRepository.save(builder.build());
            log.debug("Failure event: " + loginFailure.getId());

            if(loginFailure.getUser() != null){
                lockUserAccount(loginFailure.getUser());
            }
        }
    }

    private void lockUserAccount(UserEntity user) {
        List<LoginFailureEntity> failures = loginFailureRepository.findAllByUsernameAndCreatedDateAfter(user.getUsername(),
                Timestamp.valueOf(LocalDateTime.now().minusDays(1)));

        if(failures.size() > 3){
            log.debug("locking user account...: " + user.getUsername());
            user.setAccountNonLocked(false);
            userRepository.save(user);
        }
    }
}
