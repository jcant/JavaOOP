package com.gmail.gm.jcant;

public class Link {
	private String ref;
	private String text;
	private String raw;

	public Link() {
		super();
	}

	public Link(String ref, String text, String raw) {
		super();
		this.ref = ref;
		this.text = text;
		this.raw = raw;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	@Override
	public String toString() {
		return "Link [ref=" + ref + ", text=" + text + ", raw=" + raw + "]";
	}

}
