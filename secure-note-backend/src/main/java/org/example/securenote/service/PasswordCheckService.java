package org.example.securenote.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class PasswordCheckService {

	public static final int MIN_PASSWORD_LENGTH = 8;
	public static final int MIN_PASSWORD_ENTROPY = 70;

	private final EntropyService entropyService;

	public void checkPassword(@NotNull String password) {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			throw new IllegalStateException("Password must be at least " + MIN_PASSWORD_LENGTH +" characters long.");
		}
		if (!password.matches(".*[a-z].*")) {
			throw new IllegalStateException("Provided password must have at least one small letter.");
		}
		if (!password.matches(".*[A-Z].*")) {
			throw new IllegalStateException("Provided password must have at least one capital letter.");
		}
		if (!password.matches("\\p{Punct}")) {
			throw new IllegalStateException("Provided password must have at least one special character.");
		}
		if (entropyService.calculateEntropy(password) < MIN_PASSWORD_ENTROPY) {
			throw new IllegalStateException("Provided password is too weak.");
		}
	}

	public void checkPasswords(@NotNull String password, @NotNull String passwordConfirmation) {
		if (!password.equals(passwordConfirmation)) {
			throw new IllegalStateException("Provided passwords do not match.");
		}
		checkPassword(password);
	}
}
