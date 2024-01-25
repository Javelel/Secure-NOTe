package org.example.securenote.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.securenote.dto.CreateNoteDto;
import org.example.securenote.dto.NoteDto;
import org.example.securenote.model.Note;
import org.example.securenote.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.securenote.repository.NoteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

	private final EncryptionService encryptionService;
	private final NoteRepository noteRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public List<NoteDto> getNotes() {
		List<Note> notes = noteRepository.findAll();
		return notes.stream()
				.map(note -> new NoteDto(note.getId(), note.getTitle(), note.getContent(), note.getIsEncrypted(), note.getAuthor().getUsername()))
				.collect(Collectors.toList());
	}

	@Transactional
	public NoteDto getNoteById(Long id) {
		Note note = noteRepository.findById(id).orElseThrow();
		return new NoteDto(note.getId(), note.getTitle(), note.getContent(), note.getIsEncrypted(), note.getAuthor().getUsername());
	}

	@Transactional
	public void createNote(User user, CreateNoteDto noteDto) {
		Note note = new Note();
		note.setTitle(noteDto.title());
		note.setIsEncrypted(noteDto.isEncrypted());
		note.setAuthor(user);

		if (noteDto.isEncrypted()) {
			String encryptedPassword = passwordEncoder.encode(noteDto.password());
			note.setPassword(encryptedPassword);
			String encryptedContent = encryptionService.encrypt(noteDto.content(), noteDto.password());
			note.setContent(encryptedContent);
		} else {
			note.setContent(noteDto.content());
		}

		noteRepository.save(note);
	}

	@Transactional
	public NoteDto getEncryptedNoteById(Long id, String password) {
		Note note = noteRepository.findById(id).orElseThrow();
		if (note.getIsEncrypted()) {
			if (passwordEncoder.matches(password, note.getPassword())) {
				String decryptedContent = encryptionService.decrypt(note.getContent(), password);
				return new NoteDto(note.getId(), note.getTitle(), decryptedContent, true, note.getAuthor().getUsername());
			}
			else {
				return null;
			}
		}
		else {
			return new NoteDto(note.getId(), note.getTitle(), note.getContent(), false, note.getAuthor().getUsername());
		}
	}

	@Transactional
	public void deleteNoteById(User user, Long id) {
		Note note = noteRepository.findById(id).orElseThrow();
		if (note.getAuthor().equals(user)) {
			noteRepository.deleteById(id);
		}
	}

}
