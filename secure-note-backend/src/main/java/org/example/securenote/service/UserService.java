package org.example.securenote.service;

import lombok.RequiredArgsConstructor;
import org.example.securenote.dto.RegisterUserDto;
import org.example.securenote.model.User;
import org.example.securenote.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordCheckService passwordCheckService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) {
		final User user = userRepository.findByEmailIgnoreCase(email);
		if (user == null) {
			throw new UsernameNotFoundException("User " + email + " not found.");
		}
		//System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		//System.out.println(passwordEncoder.encode("aA123456789."));
		return user;
	}
	public User createUser(RegisterUserDto registerUserDto) {
		if (userRepository.existsByEmailIgnoreCase(registerUserDto.email())) {
			throw new IllegalArgumentException("This email has already been taken");
		}

		passwordCheckService.checkPasswords(registerUserDto.password(), registerUserDto.passwordConfirm());

		final String encryptedPassword = passwordEncoder.encode(registerUserDto.password());
		final User user = new User(registerUserDto.email(), encryptedPassword);
		userRepository.save(user);
		return user;
	}


}
