package org.example.securenote.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	@EqualsAndHashCode.Exclude
	private Boolean isEncrypted;

	@Column(nullable = true)
	@EqualsAndHashCode.Exclude
	private String password;

	@ManyToOne
	private User author;
}
