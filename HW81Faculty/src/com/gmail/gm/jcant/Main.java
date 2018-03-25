package com.gmail.gm.jcant;

import java.io.IOException;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Faculty ATP = new Faculty();
		fillFaculty(ATP);

		try {
			ATP.saveAllGroups();
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}

		Faculty ATP2 = new Faculty();
		loadFaculty(ATP2);

		Group grATP = ATP.getGroupByName("Group2");
		Group grATP2 = ATP2.getGroupByName("Group2");

		System.out.println("Get group from ATP: " + grATP);
		System.out.println("Get group from ATP2: " + grATP2);
		System.out.println("Group2 from ATP and Group2 from ATP2 is equals? =>" + grATP.equals(grATP2));
		System.out.println();

		grATP.getStudent(3).setName("TANYA");

		System.out.println("Get group from ATP: " + grATP);
		System.out.println("Get group from ATP2: " + grATP2);
		System.out.println("Group2 from ATP and Group2 from ATP2 is equals? =>" + grATP.equals(grATP2));
		System.out.println();

		ATP.showGroupsAvarageScore();
		System.out.println();
		showStudentsBetween(ATP, 5, 12);
	}

	public static void showStudentsBetween(Faculty f, double min, double max) {

		Student[] arr = f.getStudentsByAvScore(min, max);

		System.out.println("Students having average score between " + min + " and " + max);
		for (Student student : arr) {
			System.out.println("Group:" + student.getGroup().getGroupName() + " Student: " + student);
		}
	}

	public static void fillFaculty(Faculty f) {
		Group gr1 = new Group(
				generateStudentArray(5, "Name", "Surname", JDate.getDate("01-01-2000"), JDate.getDate("01-09-2016")));
		gr1.setGroupName("Group1");

		Group gr2 = new Group(
				generateStudentArray(9, "Sidor", "Sidorov", JDate.getDate("01-01-1990"), JDate.getDate("01-09-2009")));
		gr2.setGroupName("Group2");

		Group gr3 = gr1.clone();
		gr3.setGroupName("Group3");

		f.addGroup(gr1);
		f.addGroup(gr2);
		f.addGroup(gr3);
	}

	public static void loadFaculty(Faculty f) {
		try {
			f.loadGroupFromFile("-1504930914");
			f.loadGroupFromFile("1946458321");
			f.loadGroupFromFile("1946460243");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	public static Student[] generateStudentArray(int count, String name, String sname, Date birthday, Date in) {
		Student[] result = new Student[count];

		for (int i = 0; i < count; i++) {
			result[i] = new Student(name + i, sname + i, JDate.incYear(birthday, i), ((i % 2) == 0), 2 * i, 2 * i,
					"Inst" + i, JDate.incYear(in, i), i * 3);
		}

		return result;
	}

}
