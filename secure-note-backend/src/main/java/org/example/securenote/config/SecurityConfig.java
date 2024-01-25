package org.example.securenote.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors((cors) -> cors.configurationSource(corsConfigurationSource()))
				.csrf(AbstractHttpConfigurer::disable)
				.formLogin((formLogin) -> formLogin.loginProcessingUrl("/api/auth/login")
						.usernameParameter("email")
						.passwordParameter("password")
						.successHandler(successHandler())
						.failureHandler(failureHandler())
						.permitAll()
				)
				.logout((logout) -> logout.logoutUrl("/api/auth/logout")
						.logoutSuccessHandler(logoutSuccessHandler())
						.deleteCookies("JSESSIONID")
						.permitAll()
				)
				.authorizeHttpRequests((request) -> {
					request
							.requestMatchers("/api/auth/**").permitAll()
							.requestMatchers("/api/notes/**").authenticated()
							.anyRequest().denyAll();
				})
				.exceptionHandling((exceptionHandling) -> exceptionHandling
						.authenticationEntryPoint(
								(request, response, authException) ->
										response.setStatus(HttpStatus.UNAUTHORIZED.value())
						)
				);

		return http.build();
	}

	private LogoutSuccessHandler logoutSuccessHandler() {
		return new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				System.out.println("Logout Success");
				response.setStatus(HttpServletResponse.SC_OK);
			}
		};
	}

	private AuthenticationSuccessHandler successHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
												HttpServletResponse httpServletResponse, Authentication authentication)
					throws IOException, ServletException {
				System.out.println("Success");
				httpServletResponse.setStatus(200);
			}
		};
	}

	private AuthenticationFailureHandler failureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
												HttpServletResponse httpServletResponse, AuthenticationException e)
					throws IOException, ServletException {
				System.out.println("Failure");
				httpServletResponse.getWriter().append("Authentication failure");
				httpServletResponse.setStatus(401);
			}
		};
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		configuration.setAllowedHeaders(Arrays.asList("content-type", "authorization"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}