package org.example.securenote.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.securenote.model.User;
import org.example.securenote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginFailureService {
	private final UserRepository userRepository;
	private Duration duration = Duration.ofMinutes(1);
	private int maxFailedLoginAttempts = 3;

	private boolean canAccountBeUnlocked(User user) {
		long userLockStart = user.getLockTime().toEpochSecond(ZoneOffset.ofHours(2));
		long now = Instant.now().toEpochMilli() / 1000;
		return userLockStart + duration.toSeconds() < now;
	}

	@Transactional
	public boolean lockAccount(String email) {
		Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmailIgnoreCase(email));
		if (userOptional.isEmpty()) {
			return false;
		}
		User user = userOptional.get();
		if (user.isAccountNonLocked()) {
			user.setFailedAttempt(user.getFailedAttempt() + 1);
			user.setAccountNonLocked(user.getFailedAttempt() < maxFailedLoginAttempts);
			user.setLockTime(LocalDateTime.now());
			userRepository.save(user);
			return !user.isAccountNonLocked();
		}
		if (canAccountBeUnlocked(user)) {
			user.setAccountNonLocked(true);
			user.setFailedAttempt(0);
			user.setLockTime(LocalDateTime.of(1970, 1, 1, 0, 0));
			userRepository.save(user);
			System.out.println("User unlocked: " + user.getEmail());
			return false;
		}

		return true;
	}

	public void resetFailedAttempts(String email) {
		User user = userRepository.findByEmailIgnoreCase(email);
		user.setFailedAttempt(0);
		user.setLockTime(LocalDateTime.of(1970, 1, 1, 0, 0));
		user.setAccountNonLocked(true);
		userRepository.save(user);
	}

}
