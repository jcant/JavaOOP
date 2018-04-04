package com.gmail.gm.jcant;

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

		while (key.length() > 0) {
			for (String entry : enset) {
				if (entry.startsWith(key)) {
					return entry;
				}
			}
			key = key.substring(0, key.length() - 1);
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
