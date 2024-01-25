package org.example.securenote.dto;

public record CreateNoteDto(
	String title,
	String content,
	Boolean isEncrypted,
	String password
) {
}
