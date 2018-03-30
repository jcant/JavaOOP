package com.gmail.gm.jcant;

public class BBTPers implements Cloneable{
	private static int counter = 0;
	private String name;
	private int generation;
	
	public BBTPers() {
		super();
		generation = counter;
		counter++;
	}

	public BBTPers(String name) {
		super();
		generation = counter;
		this.name = name;
		counter++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGeneration() {
		return generation;
	}

	@Override
	public String toString() {
		return "\t" +name + " generation=" + generation;
	}

	@Override
	public BBTPers clone() {
		return new BBTPers(this.name);
	}
	
	
	
}
