package org.example.securenote.controller;

import lombok.RequiredArgsConstructor;
import org.example.securenote.dto.CreateNoteDto;
import org.example.securenote.dto.NoteDto;
import org.example.securenote.dto.NotePasswordDto;
import org.example.securenote.model.Note;
import org.example.securenote.model.User;
import org.example.securenote.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class NoteController {
	private final NoteService noteService;
	@GetMapping("api/notes")
	public ResponseEntity<List<NoteDto>> getNotes() {
		List<NoteDto> notes = noteService.getNotes();
		return ResponseEntity.ok(notes);
	}

	@GetMapping("api/notes/{id}")
	public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id) {
		NoteDto note = noteService.getNoteById(id);

		return ResponseEntity.ok(note);
	}

	@PostMapping("api/notes")
	public ResponseEntity<Void> createNote(@AuthenticationPrincipal User user, @RequestBody CreateNoteDto noteDto) {
		noteService.createNote(user, noteDto);
		return ResponseEntity.ok().build();
	}

	@PostMapping("api/notes/{id}")
	public ResponseEntity<NoteDto> getEncryptedNoteById(@PathVariable Long id, @RequestBody NotePasswordDto notePasswordDto) {
		NoteDto noteDto = noteService.getEncryptedNoteById(id, notePasswordDto.password());
		return ResponseEntity.ok(noteDto);
	}
}
