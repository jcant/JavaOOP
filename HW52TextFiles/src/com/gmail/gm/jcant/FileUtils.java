package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class FileUtils {

	public static void textAndText(File source1, File source2, File target) throws IOException {

		try {
			saveToFile(target, mergeArrays(readTextFile(source1), readTextFile(source2)));

		} catch (IOException e) {
			throw e;
		}
	}

	private static String[] readTextFile(File file) throws IOException {

		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = "";

			while ((line = br.readLine()) != null) {
				sb.append(line + " ");
			}

		} catch (IOException e) {
			throw e;
		}

		return removePunctuation(sb).split(" ");

	}

	private static void saveToFile(File file, String str) throws IOException {

		try (PrintWriter pw = new PrintWriter(file)) {

			pw.print(str);

		} catch (IOException e) {
			throw e;
		}
	}

	private static String mergeArrays(String[] arr1, String[] arr2) {
		StringBuilder sb = new StringBuilder();
		String[] clone = Arrays.copyOf(arr2, arr2.length);

		for (String word1 : arr1) {
			for (int i = 0; i < clone.length; i++) {
				if (word1.equals(clone[i])) {
					sb.append(word1 + " ");
					clone[i] = "";
					break;
				}
			}
		}

		return sb.toString();
	}

	private static String removePunctuation(StringBuilder sb) {

		return sb.toString().replaceAll("[.|,|:|;|!|?|\"|\\(|\\)|'|\\{|\\}|\\s+]", " ").replaceAll("\\s+", " ");

	}
}
