package org.example.securenote.dto;

public record CreateNoteDto(
	String title,
	String content,
	Boolean isPublic,
	String password
) {
}
