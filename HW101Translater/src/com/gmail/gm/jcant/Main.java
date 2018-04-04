package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.setTranslateMode(Languages.ENGLISH, Languages.RUSSIAN);

		// loadFile(dict, new File("500words_unicode.txt"));
		// saveDictionary(dict, new File("dictionary.bin"));
		loadDictionary(dict, new File("dictionary.bin"));

		System.out.println(dict.getCountPairs());

		String txt = dict.taskTranslate(new File("english.in"), new File("russian.out"), true);
		System.out.println(txt);

		dict.setTranslateMode(Languages.ENGLISH, Languages.UKRAINIAN);
		testHandWordsAdd(dict);
		txt = "Hello big world";
		System.out.println(dict.translate(txt));

	}

	public static void testHandWordsAdd(Dictionary dict) {
		dict.clearDictionary();
		Word wrd = new Word();
		wrd.addValue(Languages.ENGLISH, "hello");
		wrd.addValue(Languages.UKRAINIAN, "привіт");
		dict.addWord(wrd);

		wrd = new Word();
		wrd.addValue(Languages.ENGLISH, "big");
		wrd.addValue(Languages.UKRAINIAN, "великий");
		dict.addWord(wrd);

		wrd = new Word();
		wrd.addValue(Languages.ENGLISH, "world");
		wrd.addValue(Languages.UKRAINIAN, "світ");
		dict.addWord(wrd);
	}

	public static void saveDictionary(Dictionary dict, File file) {
		try {
			dict.saveDictionary(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void loadDictionary(Dictionary dict, File file) {
		try {
			dict.loadDictionary(file);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void loadFile(Dictionary dict, File file) {

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = "";

			while ((line = br.readLine()) != null) {
				String[] arr = line.split(";");
				Word wrd = new Word();
				wrd.addValue(Languages.ENGLISH, arr[0]);
				wrd.addValue(Languages.RUSSIAN, arr[1]);
				dict.addWord(wrd);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
