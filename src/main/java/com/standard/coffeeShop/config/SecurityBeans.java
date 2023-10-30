package com.standard.coffeeShop.config;

import com.standard.coffeeShop.repository.security.UserSessionRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import com.warrenstrange.googleauth.ICredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;
@AllArgsConstructor
@Configuration
public class SecurityBeans {

    private final UserSessionRepository userSessionRepository;

    @Bean
    public GoogleAuthenticator googleAuthenticator(ICredentialRepository credentialRepository){
        GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder configBuilder = new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder();
        configBuilder.setTimeStepSizeInMillis(TimeUnit.SECONDS.toMillis(60)).setNumberOfScratchCodes(0);
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator(configBuilder.build());
        googleAuthenticator.setCredentialRepository(credentialRepository);
        return googleAuthenticator;

    }
    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher){
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

//    @Bean
//    public ConcurrentSessionFilter concurrencyFilter(){
//        ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(userSessionRepository);
//        return concurrentSessionFilter;
//    }
//
//    @Bean
//    public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        List<SessionAuthenticationStrategy> delegateStrategies = new ArrayList<>();
//        delegateStrategies.add(sessionFixationProtectionStrategy());
//        delegateStrategies.add(timeoutAuthenticationStrategy());
//        delegateStrategies.add(registerSessionAuthenticationStrategy());
//        return new CompositeSessionAuthenticationStrategy(delegateStrategies);
//    }
//
//    @Bean
//    public SessionFixationProtectionStrategy sessionFixationProtectionStrategy() {
//        return new SessionFixationProtectionStrategy();
//    }
//
//    @Bean
//    public TimeoutAuthenticationStrategy timeoutAuthenticationStrategy() {
//        return new TimeoutAuthenticationStrategy(60);
//    }
//
//    @Bean
//    public RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy(){
//        return new RegisterSessionAuthenticationStrategy(userSessionRepository);
//    }

}
