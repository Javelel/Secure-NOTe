package org.example.securenote.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private Boolean isPublic;

	@Column(nullable = false)
	@EqualsAndHashCode.Exclude
	private Boolean isEncrypted;

	@Column(nullable = false)
	@EqualsAndHashCode.Exclude
	private String password;

	@ManyToOne
	private User author;
}
