package org.example.securenote.repository;

import org.example.securenote.model.Note;
import org.example.securenote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	List<Note> findAllByAuthor(User author);
}
