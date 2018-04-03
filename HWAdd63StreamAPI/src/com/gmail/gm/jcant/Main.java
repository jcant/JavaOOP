package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {
		String text = "Some text - hello world!";

		printArr(getASCIIArray(text));
	}

	public static int[] getASCIIArray(String str) {
		return str.chars().toArray();
	}

	public static void printArr(int[] arr) {
		for (int i : arr) {
			System.out.println(i + "\t " + (char) i);
		}
	}
}
