package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class Dictionary {
	private Set<Word> mainList = new HashSet<>();
	private NavigableMap<String, String> modeList = new TreeMap<>();
	private Languages from = Languages.ENGLISH;
	private Languages to = Languages.UKRAINIAN;
	private int wordSimplifyLenght = 3;

	public Dictionary() {
		super();
	}

	public void addWord(Word word) {
		mainList.add(word.clone());
		prepareMap();
	}

	public String translate(String text) {
		StringBuilder sb = new StringBuilder();

		String[] wordsArr = text.split(" ");
		for (String wrd : wordsArr) {
			String tmp = getNearKey(wrd.toLowerCase());
			if (tmp == null) {
				tmp = "/" + wrd + "/";
			} else {
				tmp = modeList.get(tmp);
			}
			sb.append(tmp);
			sb.append(" ");
		}

		return sb.toString();
	}

	private String getNearKey(String key) {
		Set<String> enset = modeList.keySet();
		
		//first test for short words and strict equals:

		while (true) {
			for (String entry : enset) {
				if (entry.startsWith(key)) {
					return entry;
				}
			}
			key = key.substring(0, key.length() - 1);
			if (key.length() < wordSimplifyLenght) {
				break;
			}
		}
		return null;
	}

	public void setTranslateMode(Languages from, Languages to) {
		this.from = from;
		this.to = to;
		prepareMap();
	}

	private void prepareMap() {
		modeList.clear();
		for (Word word : mainList) {
			String fromLang = word.getValue(from);
			String toLang = word.getValue(to);
			if ((fromLang != null) && (toLang != null)) {
				modeList.put(fromLang, toLang);
			}
		}
	}

	public int getCountPairs() {
		return modeList.size();
	}

	public void saveDictionary(File file) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(mainList);
		} catch (IOException e) {
			throw e;
		}
	}

	public void loadDictionary(File file) throws IOException, ClassNotFoundException {

		mainList.clear();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			mainList = (HashSet<Word>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw e;
		}
		prepareMap();
	}

	public String taskTranslate(File from, File to, boolean returnText) {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(from))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				String translated = translate(line);
				sb.append(translated);
				sb.append(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(to))) {
			bw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		String result = null;
		if (returnText) {
			result = sb.toString();
		}

		return result;
	}
	
	public void clearDictionary() {
		mainList.clear();
		modeList.clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dictionary" + System.lineSeparator());
		sb.append("Translate mode: from " + from + " to " + to + System.lineSeparator());
		sb.append("Known words: " + getCountPairs() + System.lineSeparator());

		if (getCountPairs() > 0) {
			Set<Map.Entry<String, String>> enset = modeList.entrySet();
			for (Map.Entry<String, String> entry : enset) {
				sb.append("\t" + entry.getKey() + "\t" + entry.getValue() + System.lineSeparator());
			}
		}

		return sb.toString();
	}

}
