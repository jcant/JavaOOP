package com.gmail.gm.jcant;

import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Group gr1 = new Group();
		for (int i = 0; i < 2; i++) {
			System.out.println("---------------------------------------------");
			System.out.println("Enter student information");
			gr1.interactiveAddStudent();
			System.out.println();
		}

		System.out.println(gr1);
		System.out.println();

		System.out.println("---- Test Array Sorting ----");

		Group gr2 = new Group();

		for (int i = 0; i < 10; i++) {
			gr2.addStudent(getStudent());
		}

		testSorting(gr2);

		Voenkom vk = gr2;
		Student[] arrSt = vk.conscription();

		System.out.println("Voenkom:");
		for (Student student : arrSt) {
			System.out.println(student);
		}
	}

	public static void testSorting(Group group) {
		System.out.println("Unsorted:");
		System.out.println(group);
		// sort by string field:
		System.out.println("Sort by Name(asc):");
		group.sortGroup(SortBy.NAME);
		System.out.println(group);
		System.out.println("Sort by Name(desc):");
		group.sortGroup(SortBy.NAME.setAscending(false));
		System.out.println(group);
		// sort by int field:
		System.out.println("Sort by Age(asc):");
		group.sortGroup(SortBy.AGE);
		System.out.println(group);
		System.out.println("Sort by Age(desc):");
		group.sortGroup(SortBy.AGE.setAscending(false));
		System.out.println(group);
		// sort by double field:
		System.out.println("Sort by AverageScore(asc):");
		group.sortGroup(SortBy.AVGSCORE);
		System.out.println(group);
		System.out.println("Sort by AverageScore(desc):");
		group.sortGroup(SortBy.AVGSCORE.setAscending(false));
		System.out.println(group); 
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
