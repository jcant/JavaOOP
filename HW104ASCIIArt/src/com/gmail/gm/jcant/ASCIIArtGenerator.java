package com.gmail.gm.jcant;

import java.util.HashMap;
import java.util.Map;

public class ASCIIArtGenerator {

	private Map<String, LetterMask> storage = new HashMap<>();
	private int width = 8;
	private int height = 8;

	public ASCIIArtGenerator() {
		super();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Map<String, LetterMask> getStorage() {
		return storage;
	}

	public void setStorage(Map<String, LetterMask> storage) {
		this.storage = storage;
	}

	public void addLetter(String letter, LetterMask mask) {
		if ((letter != null) && (mask != null)) {
			if ((mask.getHeight() != this.height) || (mask.getWidht() != this.width)) {
				throw new IllegalArgumentException("mask has wrong sizes");
			}

			storage.put(letter, mask);

		} else {
			throw new IllegalArgumentException("null arguments!");
		}
	}

	public void printText(String text) {
		StringBuilder row = new StringBuilder();
		char[] letters = text.toCharArray();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < letters.length; j++) {
				if (letters[j] == ' ') {
					int k = 0;
					while (k++ < width) {
						row.append(" ");
					}
				} else {
					row.append(storage.get("" + letters[j]).getRow(i));
				}
				row.append(" ");
			}
			row.append(System.lineSeparator());
		}

		System.out.println(row.toString());

	}

}
