package com.gmail.gm.jcant;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

public class Cat {

	private String name;
	private String sex;
	private double weight;
	private Date birthday;
	private Color color;

	public Cat() {
		super();
	}

	public Cat(String name, String sex, double weight, Date birthday, Color color) {
		super();
		this.name = name;
		this.sex = sex;
		this.weight = weight;
		this.birthday = birthday;
		this.color = color;
	}

	public int getAge() {
		int diff = 0;
		Calendar day = Calendar.getInstance();

		day.setTime(new Date());
		diff = day.get(Calendar.YEAR);
		day.setTime(this.birthday);
		diff = diff - day.get(Calendar.YEAR);
		return diff;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + ", sex=" + sex + ", weight=" + weight + ", birthday=" + birthday + ", color="
				+ color + "]";
	}

}
