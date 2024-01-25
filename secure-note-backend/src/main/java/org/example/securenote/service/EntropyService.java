package org.example.securenote.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EntropyService {

	public double calculateEntropy(String password) {
		if (password == null || password.isEmpty()) {
			return 0.0;
		}

		Set<Character> uniqueCharacters = new HashSet<>();
		for (char c : password.toCharArray()) {
			uniqueCharacters.add(c);
		}

		double entropy = 0.0;
		int passwordLength = password.length();

		for (char uniqueChar : uniqueCharacters) {
			double probability = (double) countOccurrences(password, uniqueChar) / passwordLength;
			entropy -= probability * log2(probability);
		}
		System.out.println("Entropy: " + entropy);
		return entropy;
	}

	private int countOccurrences(String input, char target) {
		int count = 0;
		for (char c : input.toCharArray()) {
			if (c == target) {
				count++;
			}
		}
		return count;
	}

	private double log2(double value) {
		if (value == 0.0) {
			return 0.0;
		} else {
			return Math.log(value) / Math.log(2);
		}
	}

}
