package org.example.securenote.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserDto(
	@NotNull
	@Size(max = 100)
	String email,
	@NotNull
	@Size(max = 100)
	String password,
	@NotNull
	@Size(max = 100)
	String passwordConfirm
) {
}
