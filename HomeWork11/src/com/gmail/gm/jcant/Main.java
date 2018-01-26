package com.gmail.gm.jcant;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date day = new Date();

		Cat tom = new Cat();
		tom.setColor(new Color(255, 0, 0));
		tom.setName("Tom");
		tom.setSex("man");
		tom.setWeight(4.3);

		try {
			day = sdf.parse("25/11/2002");
		} catch (ParseException e) {
			System.err.println(e);
		}

		tom.setBirthday(day);
		tom.setColor(new Color(200, 200, 200));

		try {
			day = sdf.parse("01/04/2014");
		} catch (ParseException e) {
			System.err.println(e);
		}

		Cat fima = new Cat("Fima", "man", 3.2, day, new Color(255, 255, 0));

		System.out.println(tom.toString());
		System.out.println(fima.toString());

		System.out.println("Cat 1: " + tom.getName() + " age: " + tom.getAge());
		System.out.println("Cat 2: " + fima.getName() + " age: " + fima.getAge());

	}

}
