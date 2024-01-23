package org.example.securenote.repository;

import org.example.securenote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmailIgnoreCase(String email);
	User findByEmailIgnoreCase(String email);
}
