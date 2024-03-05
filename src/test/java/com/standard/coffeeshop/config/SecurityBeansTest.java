package com.standard.coffeeshop.config;

import com.standard.coffeeshop.repository.configparam.ConfigParamRepository;
import com.standard.coffeeshop.repository.security.UserSessionRepository;
import com.standard.coffeeshop.service.configparam.ConfigParamService;
import com.standard.coffeeshop.service.configparam.ConfigParamServiceImpl;
import com.standard.coffeeshop.service.security.UserSessionService;
import com.standard.coffeeshop.service.security.UserSessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SecurityBeansTest {

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @Mock
    SecurityBeans securityBeans;

    @Mock
    UserSessionService userSessionService;

    @Mock
    ConfigParamService configParamService;

    @Autowired
    UserSessionRepository userSessionRepository;

    @Autowired
    ConfigParamRepository configParamRepository;

    @Mock
    DataSource dataSource;

    @BeforeEach
    void setUp() {
        configParamService = new ConfigParamServiceImpl(configParamRepository);
        userSessionService = new UserSessionServiceImpl(userSessionRepository);
        securityBeans = new SecurityBeans(userSessionService, userSessionRepository, configParamService);

    }

    @Test
    void authenticationEventPublisher() {
        assertNotNull(securityBeans.authenticationEventPublisher(applicationEventPublisher));
    }

    @Test
    void persistentTokenRepository() {
        assertNotNull(securityBeans.persistentTokenRepository(dataSource));
    }

    @Test
    void csrfTokenRepository() {
        assertNotNull(securityBeans.csrfTokenRepository());
    }

    @Test
    void logoutFilter() {
        assertNotNull(securityBeans.logoutFilter());
    }

    @Test
    void redirectSuccessFilter() {
        assertNotNull(securityBeans.redirectSuccessFilter());
    }

    @Test
    void logoutUnregisterHandler() {
        assertNotNull(securityBeans.logoutUnregisterHandler());
    }

    @Test
    void securityContextLogoutHandler() {
        assertNotNull(securityBeans.securityContextLogoutHandler());
    }

    @Test
    void concurrencyFilter() {
        assertNotNull(securityBeans.concurrencyFilter());
    }

    @Test
    void compositeSessionAuthenticationStrategy() {
        assertNotNull(securityBeans.compositeSessionAuthenticationStrategy());

    }
}