package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		System.out.println("-----  Testing CSV  -----");
		testCSV();

		System.out.println("-----  Testing JSON  -----");
		testJSON();

	}

	public static void testCSV() {
		// save to CSV
		Group gr2 = new Group();
		for (int i = 0; i < 8; i++) {
			gr2.addStudent(getStudent());
		}

		System.out.println(gr2);

		File groupFile = new File("/home/jcant/TMP/gr2.csv");
		try {
			DAOController.saveObject(gr2, FormatCSV::getFormatData, groupFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// load from CSV
		System.out.println("---------------------------------------");

		Group gr3 = new Group();
		try {
			DAOController.loadObject(gr3, FormatCSV::getUData, groupFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(gr3);
	}

	public static void testJSON() {
		// save to CSV
		Group gr2 = new Group();
		for (int i = 0; i < 8; i++) {
			gr2.addStudent(getStudent());
		}

		System.out.println(gr2);

		File groupFile = new File("/home/jcant/TMP/gr2.json");
		try {
			DAOController.saveObject(gr2, FormatJSON::getFormatData, groupFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// load from CSV
		System.out.println("---------------------------------------");

		Group gr3 = new Group();
		try {
			DAOController.loadObject(gr3, FormatJSON::getUData, groupFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(gr3);
	}

	public static Student getStudent() {
		Student result = new Student();
		String name = "Name" + (int) (1 + Math.random() * 100);
		String surname = "Surname" + (int) (1 + Math.random() * 100);
		boolean male = ((int) (1 + Math.random() * 100) % 2) == 0;
		double weight = 40 + Math.random() * 70;
		double height = 150 + Math.random() * 50;
		String institute = "Institute" + (int) (1 + Math.random() * 100);
		double avgScore = 1 + Math.random() * 12;

		Date dayBirth = JDate.getDate("" + (int) (1 + Math.random() * 30) + "-" + (int) (1 + Math.random() * 12) + "-"
				+ (int) (1990 + Math.random() * 15));
		Date dayIn = JDate.getDate("" + (int) (1 + Math.random() * 30) + "-" + (int) (1 + Math.random() * 12) + "-"
				+ (int) (2015 + Math.random() * 3));

		result = new Student(name, surname, dayBirth, male, weight, height, institute, dayIn, avgScore);

		return result;
	}

}
