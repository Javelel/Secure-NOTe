package org.example.securenote.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.securenote.model.User;
import org.example.securenote.service.UserLoginFailureService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {
	final UserLoginFailureService userLoginFailureService;

	public AuthenticationSuccessHandlerImpl(UserLoginFailureService userAccountLockService) {
		super();
		this.userLoginFailureService = userAccountLockService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		userLoginFailureService.resetFailedAttempts(user.getEmail());

		System.out.printf("successful authentication attempt by user (%s)%n", user.getEmail());
		System.out.println("Success");
		response.setStatus(200);
//		getRedirectStrategy().sendRedirect(request, response, "/dashboard");
	}
}
