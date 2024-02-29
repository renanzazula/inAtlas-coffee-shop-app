package com.standard.coffeeShop.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
public class TimeoutAuthenticationStrategy implements SessionAuthenticationStrategy {

    private final Integer defaultTimeout;

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(retrieveSessionTimeOut());
        }
    }

    private Integer retrieveSessionTimeOut() {
        return 60;
    }

}
