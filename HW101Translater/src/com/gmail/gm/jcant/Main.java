package com.gmail.gm.jcant;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.setTranslateMode(Languages.ENGLISH, Languages.RUSSIAN);
		
		Word hello = new Word();
		Word world = new Word();
		Word wrd = new Word();
		
		hello.addValue(Languages.RUSSIAN, "������");
		hello.addValue(Languages.ENGLISH, "hello");
		
		world.addValue(Languages.RUSSIAN, "���");
		world.addValue(Languages.ENGLISH, "world");
		world.addValue(Languages.UKRAINIAN, "���");
		
		wrd.addValue(Languages.RUSSIAN, "�������");
		wrd.addValue(Languages.ENGLISH, "big");
		
		dict.addWord(hello);
		dict.addWord(world);
		dict.addWord(wrd);
		
		//System.out.println(dict);
		

		String txt = "Hellou bigg wordd";
		System.out.println(dict.translate(txt));
		
	}
	
	public static void loadFile(File file) {
		
	}

}
