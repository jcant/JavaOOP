package com.gmail.gm.jcant;

import java.util.Date;

public class Human {

	private String name;
	private String surname;
	private JDate birthday;
	private boolean male;
	private double weight;
	private double height;

	public Human() {
		super();
	}

	public Human(String name, String surname, Date birthday, boolean male, double weight, double height) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = new JDate(birthday);
		this.male = male;
		this.weight = weight;
		this.height = height;
	}

	public int getAge() {
		return birthday.getDifferenceYears(new Date());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + " " + surname + System.lineSeparator());
		sb.append(((male) ? ("man") : ("woman")) + System.lineSeparator());
		sb.append("Age: " + getAge() + System.lineSeparator());
		sb.append("height=" + height + System.lineSeparator());
		sb.append("weight=" + weight + System.lineSeparator());
		sb.append("birthday: " + birthday + System.lineSeparator());
		sb.append("------------------");
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthday() {
		return birthday.getDate();
	}

	public void setBirthday(Date birthday) {
		this.birthday.setDate(birthday);
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setDateFormat(String format) {
		this.birthday.setFormat(format);
	}

}
