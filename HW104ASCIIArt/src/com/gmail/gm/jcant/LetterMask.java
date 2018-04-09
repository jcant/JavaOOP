package com.gmail.gm.jcant;

public class LetterMask {

	private char letter;
	private String[] mask;
	private int widht;
	private int height;

	public LetterMask() {
		super();
	}

	public LetterMask(char letter, String[] mask) {
		super();
		if (mask == null) {
			throw new IllegalArgumentException("mask is null");
		}

		this.letter = letter;
		this.mask = mask;

		this.height = mask.length;
		this.widht = mask[0].length();
	}

	public String getRow(int num) {
		if ((num < 0)||(num > mask.length)) {
			throw new IllegalArgumentException("num is wrong");
		}
		
		return mask[num];
	}
	
	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public String[] getMask() {
		return mask;
	}

	public void setMask(String[] mask) {
		this.mask = mask;
	}

	public int getWidht() {
		return widht;
	}

	public int getHeight() {
		return height;
	}
	
	

}
