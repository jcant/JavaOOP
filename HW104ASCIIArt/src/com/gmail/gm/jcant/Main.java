package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class Main {

	public static void main(String[] args) {

		ASCIIArtGenerator gen = new ASCIIArtGenerator();
		gen.setHeight(6);
		gen.setWidth(7);

		gen.setStorage(getJSONLetters(new File("letters.json")));

		gen.printText("HELLO WORLD");
		gen.printText("HAVE A NICE DAY!");
	}

	public static Map<String, LetterMask> getJSONLetters(File file) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map<String, LetterMask> map = null;
		Type type = new TypeToken<Map<String, LetterMask>>() {}.getType();
		
		try {
			map = gson.fromJson(new FileReader(file), type);
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}

		return map;
	}

}
