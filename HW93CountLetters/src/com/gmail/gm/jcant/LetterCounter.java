package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LetterCounter {

	private StringBuilder text = new StringBuilder();
	private List<Letter> result = new ArrayList<>();

	public LetterCounter() {
		super();
	}

	public LetterCounter(File file) {
		super();
		analizeText(file);
		count();
	}

	public void analizeText(String text) {
		this.text = new StringBuilder();
		this.text.append(text);
		count();
	}

	public void analizeText(File file) {
		text = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String txt = "";
			while ((txt = reader.readLine()) != null) {
				text.append(txt.toLowerCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		count();
	}

	public void count() {
		result.clear();
		if (text.length() != 0) {
			for (char ch : text.toString().toCharArray()) {
				addLetter(ch);
			}
		} else {
			throw new NullPointerException("There is no text to analize");
		}

		Collections.sort(result);
	}

	private void addLetter(char letter) {
		Letter lt = new Letter(letter);
		Letter present = findLetter(lt);
		if (present != null) {
			present.inc();
		} else {
			result.add(lt);
		}
	}

	private Letter findLetter(Letter lt) {
		for (Letter letter : result) {
			if (letter.equals(lt)) {
				return letter;
			}
		}
		return null;
	}

	// letter class
	private class Letter implements Comparable<Letter> {
		char letter;
		int count = 1;

		private Letter(char letter) {
			super();
			this.letter = letter;
		}

		private void inc() {
			count += 1;
		}

		@Override
		public int compareTo(Letter o) {

			return (o.count - this.count);
		}

		@Override
		public String toString() {
			return "'" + letter + "' count=" + count;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + letter;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Letter other = (Letter) obj;
			if (letter != other.letter)
				return false;
			return true;
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Letter letter : result) {
			sb.append(letter + System.lineSeparator());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LetterCounter other = (LetterCounter) obj;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}

}
