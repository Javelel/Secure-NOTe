package org.example.securenote.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EntropyService {
	private static final Pattern[] CHAR_SETS = new Pattern[]{
			Pattern.compile("[a-z]"),
			Pattern.compile("[A-Z]"),
			Pattern.compile("[0-9]"),
			Pattern.compile("\\p{Punct}")
	};

	private static final int[] CHAR_SET_SIZES = new int[]{
			26,
			26,
			10,
			32
	};

	public int calculateEntropy(String password) {
		int entropy = 0;

		for (int i = 0; i < CHAR_SETS.length; i++) {
			if (CHAR_SETS[i].matcher(password).find()) {
				entropy += CHAR_SET_SIZES[i];
			}
		}
		return (int) Math.round(Math.log(entropy) / Math.log(2));
	}
}
