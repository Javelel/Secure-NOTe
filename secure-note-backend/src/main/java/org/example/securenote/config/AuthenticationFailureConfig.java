package org.example.securenote.config;

import org.example.securenote.service.UserLoginFailureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class AuthenticationFailureConfig {
	final UserLoginFailureService userLoginFailureService;

	public AuthenticationFailureConfig(UserLoginFailureService userLoginFailureService) {
		this.userLoginFailureService = userLoginFailureService;
	}

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new AuthenticationFailureHandlerImpl(userLoginFailureService, "/api/auth/login?error");
	}
}
