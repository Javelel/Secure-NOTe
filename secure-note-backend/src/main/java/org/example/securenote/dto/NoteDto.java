package org.example.securenote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NoteDto(
		@NotNull
		Long id,

		@NotBlank
		@Size(max = 100)
		String title,

		@Size(max = 1000)
		String content,

		Boolean isEncrypted,

		@NotBlank
		@Size(max = 100)
		String authorName
) {
}
