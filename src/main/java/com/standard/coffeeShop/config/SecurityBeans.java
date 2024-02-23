package com.standard.coffeeShop.config;

import com.standard.coffeeShop.repository.security.UserSessionRepository;
import com.standard.coffeeShop.security.RegisterSessionAuthenticationStrategy;
import com.standard.coffeeShop.security.TimeoutAuthenticationStrategy;
import com.standard.coffeeShop.security.filter.ConcurrentSessionFilter;
import com.standard.coffeeShop.security.handler.LogoutUnregisterHandler;
import com.standard.coffeeShop.security.handler.RedirectSuccessFilter;
import com.standard.coffeeShop.service.security.UserSessionService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Configuration
public class SecurityBeans {

    private final UserSessionService userSessionService;
    private final UserSessionRepository userSessionRepository;

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

    @Bean
    public HttpSessionCsrfTokenRepository csrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(redirectSuccessFilter(), logoutUnregisterHandler(), securityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl("/logout");
        return logoutFilter;
    }

    @Bean
    public RedirectSuccessFilter redirectSuccessFilter() {
        return new RedirectSuccessFilter();
    }

    @Bean
    public LogoutUnregisterHandler logoutUnregisterHandler() {
        return new LogoutUnregisterHandler(userSessionService);
    }

    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.setInvalidateHttpSession(true);
        return logoutHandler;
    }

    @Bean
    public ConcurrentSessionFilter concurrencyFilter() {
        ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(userSessionRepository);
        return concurrentSessionFilter;
    }

    @Bean
    public CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy() {
        List<SessionAuthenticationStrategy> strategies = new ArrayList<>();
        strategies.add(new SessionFixationProtectionStrategy());
        strategies.add(new TimeoutAuthenticationStrategy(60));
        strategies.add(new CsrfAuthenticationStrategy(csrfTokenRepository()));
        strategies.add(new RegisterSessionAuthenticationStrategy(userSessionRepository));
        return new CompositeSessionAuthenticationStrategy(strategies);
    }


}
