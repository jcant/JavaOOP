package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		// Group gr2 = new Group();
		// for (int i = 0; i < 8; i++) {
		// gr2.addStudent(getStudent());
		// }
		//
		// System.out.println(gr2);
		//
		// File groupFile = new File("/home/jcant/TMP/gr3.csv");
		// try {
		// DAOController.saveObject(gr2, FormatJSON::getFormatData, groupFile);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// System.out.println("---------------------------------------");
		//
		// Group gr3 = new Group();
		// try {
		// DAOController.loadObject(gr3, FormatCSV::getUData, groupFile);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// System.out.println(gr3);

		Pattern p = Pattern.compile("(\\{.*(\\{.*\\}+).*\\})");
		Matcher m = p.matcher("{{1qqqq1 1wwww1} {1eeee1 1rrrr1}} {{1tttt1 1yyyy1} {2qqqq2 2wwww2}} {{2eeee2 2rrrr2} {2tttt2 2yyyy2}}");
		System.out.println("Start finding...");
		System.out.println();
		while(m.find()) {
			System.out.println(m.group());
		}
		System.out.println();
		System.out.println("end finding");
		

	}

	public static boolean test(String testString) {
		Pattern p = Pattern.compile("^(25[0-5])\\.");
		Matcher m = p.matcher(testString);
		return m.matches();
	}

	public static void printUData(DataUnificator[] data) {
		for (DataUnificator dItem : data) {
			System.out.println(dItem);
			for (DataUnificator du : dItem.getUData()) {
				System.out.println("\t" + du);
			}
		}

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
