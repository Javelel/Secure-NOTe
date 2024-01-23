package org.example.securenote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginUserDto(
	@NotNull
	@Size(max = 100)
	@NotBlank
	String email,
	@NotNull
	@Size(max = 100)
	@NotBlank
	String password,
	@NotNull
	@Size(max = 100)
	@NotBlank
	String verificationCode
) {
}
