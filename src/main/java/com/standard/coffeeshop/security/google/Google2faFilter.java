package com.standard.coffeeshop.security.google;

import com.standard.coffeeshop.repository.entity.security.UserEntity;
import com.standard.coffeeshop.service.configParam.ConfigParamService;
import com.standard.coffeeshop.service.dto.ConfigParamsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Component
public class Google2faFilter extends GenericFilterBean {

    private AuthenticationTrustResolver authenticationTrustResolver;
    private RequestMatcher url2fa;
    private final ConfigParamService configParamService;

    public Google2faFilter(ConfigParamService configParamService) {
        this.configParamService = configParamService;
        this.url2fa = new AntPathRequestMatcher("/authentication/2fa/**");
        this.authenticationTrustResolver =  new AuthenticationTrustResolverImpl();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(configParamService.getParameterValue(ConfigParamsEnum.GOOGLE_2FA, Boolean.class).orElse(Boolean.FALSE)) {
            if (url2fa.matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && !authenticationTrustResolver.isAnonymous(authentication)) {
                log.debug("Processing 2FA Filter");
                if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserEntity) {
                    UserEntity user = (UserEntity) authentication.getPrincipal();
                    if (user.isUseGoogle2fa()) {
                        if (user.isUseGoogle2fa() && user.isUserGoogle2FaRequired()) {
                            log.debug("2FA required");
                            return;
                        }
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
