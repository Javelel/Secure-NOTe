package org.example.securenote.model;


import jakarta.persistence.*;
import lombok.*;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "users")
public class User implements UserDetails {
	@jakarta.persistence.Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private String password;

	@Column(name = "is_user_non_locked")
	private boolean accountNonLocked = true;

	@Column(name = "failed_login_attempt")
	private int failedAttempt = 0;

	@Column(name = "lock_time")
	private LocalDateTime lockTime = LocalDateTime.of(1970, 1, 1, 0, 0);



	public User(@NonNull String email, @NonNull String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Set.of();
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
