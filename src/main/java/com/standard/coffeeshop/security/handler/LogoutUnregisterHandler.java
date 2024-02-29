package com.standard.coffeeshop.security.handler;

import com.standard.coffeeshop.repository.entity.security.UserEntity;
import com.standard.coffeeshop.security.utils.HashUtils;
import com.standard.coffeeshop.service.security.UserSessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
public class LogoutUnregisterHandler implements LogoutHandler {

	private final UserSessionService userSessionService;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		if (authentication != null) {
			UserEntity user = (UserEntity) authentication.getPrincipal();
			String sessionId = (request.getSession(false) != null) ? request.getSession(false).getId() : null;
			if (sessionId != null) {
				String sessionIdHashed = HashUtils.hashString(sessionId);
				userSessionService.unregisterUserSession(user.getUsername(), sessionIdHashed);
				log.info("unregisterUserSession {} done", sessionIdHashed);
			}
		}
	}
}
