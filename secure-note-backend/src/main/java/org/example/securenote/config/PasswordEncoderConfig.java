package org.example.securenote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
	private static final int SALT_LENGTH = 16;
	private static final int HASH_LENGTH = 64;
	private static final int PARALLELISM = 4;
	private static final int MEMORY_COST = 65536;
	private static final int ITERATIONS = 4;
/////////


	///////////
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Argon2PasswordEncoder(SALT_LENGTH, HASH_LENGTH, PARALLELISM, MEMORY_COST, ITERATIONS);
	}
}
