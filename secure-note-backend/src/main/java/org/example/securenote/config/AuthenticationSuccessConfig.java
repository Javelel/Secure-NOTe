package org.example.securenote.config;

import org.example.securenote.service.UserLoginFailureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AuthenticationSuccessConfig {
	final UserLoginFailureService userLoginFailureService;

	public AuthenticationSuccessConfig(UserLoginFailureService userLoginFailureService) {
		this.userLoginFailureService = userLoginFailureService;
	}

	@Bean
	AuthenticationSuccessHandler successAuthenticationHandler() {
		return new AuthenticationSuccessHandlerImpl(userLoginFailureService);
	}
}
