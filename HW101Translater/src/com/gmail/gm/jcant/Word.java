package com.gmail.gm.jcant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Word implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private int probability = 100;
	private Map<Languages, String> values = new HashMap<>();

	public Word() {
		super();
	}

	public Word(Map<Languages, String> values) {
		super();
		this.values = values;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Word (probability = " + probability + "%)" + System.lineSeparator());
		Set<Map.Entry<Languages, String>> enset = values.entrySet();
		for (Map.Entry<Languages, String> entry : enset) {
			sb.append(entry.getKey() + ": " + entry.getValue() + System.lineSeparator());
		}
		return sb.toString();
	}

	public void addValue(Languages lang, String value) {
		values.put(lang, value);
	}

	public void removeValue(Languages lang) {
		values.remove(lang);
	}

	public String getValue(Languages lang) {
		return values.get(lang);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + probability;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		Word other = (Word) obj;
		if (probability != other.probability)
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	@Override
	protected Word clone() {

		Word clone = new Word();
		clone.probability = this.probability;

		Set<Map.Entry<Languages, String>> enset = values.entrySet();

		for (Map.Entry<Languages, String> entry : enset) {
			clone.values.put(entry.getKey(), new String(entry.getValue()));
		}

		return clone;

	}

}
