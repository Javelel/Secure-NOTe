package org.example.securenote.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.securenote.service.UserLoginFailureService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {
	private final UserLoginFailureService userLoginFailureService;

	public AuthenticationFailureHandlerImpl(
			UserLoginFailureService userLoginFailureService,
			String defaultFailureUrl
	) {
		super(defaultFailureUrl);
		this.userLoginFailureService = userLoginFailureService;
	}

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception
	) throws IOException {
		String email = request.getParameter("email");
		userLoginFailureService.lockAccount(email);

		System.out.printf("failed authentication attempt by user (%s)%n", email);
		response.getWriter().append("Authentication failure");
		response.setStatus(401);
//		getRedirectStrategy().sendRedirect(request, response, redirectUriBuilder(exception.getMessage()));
	}

//	private String redirectUriBuilder(String message) {
//		String path = "login";
//		String error = "true";
//		String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
//		return path + "?error=" + error + "&message=" + encodedMessage;
//	}

}
