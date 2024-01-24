package org.example.securenote.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.securenote.dto.LoginUserDto;
import org.example.securenote.dto.RegisterUserDto;
import org.example.securenote.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.AuthenticationException;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
	private final UserService userService;

	@PostMapping("api/auth/register")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {

		userService.createUser(registerUserDto);
		return ResponseEntity.ok().build();
	}

//	@PostMapping("api/login")
//	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUserDto loginUserDto) {
//		try {
//			userService.authenticateUser(loginUserDto);
//			return ResponseEntity.ok().build();
//		} catch (AuthenticationException e) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//	}
}
