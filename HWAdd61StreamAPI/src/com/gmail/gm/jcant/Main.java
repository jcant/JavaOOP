package com.gmail.gm.jcant;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		Student[] st1 = generateStudentArray(8, "Name", "urname", JDate.getDate("01-01-1995"),
				JDate.getDate("01-09-2009"));

		Group gr1 = new Group("Group One", st1);

		System.out.println(gr1);

		List<Student> filteredList = gr1.filterBySurnameLetter('B');

		printList(filteredList);
	}

	public static Student[] generateStudentArray(int count, String name, String sname, Date birthday, Date in) {
		Student[] result = new Student[count];

		Random rr = new Random();

		for (int i = 0; i < count; i++) {
			result[i] = new Student(name + i, (char) ('A' + rr.nextInt(3)) + sname, JDate.incYear(birthday, i),
					((i % 2) == 0), 2 * i, 2 * i, "Inst" + i, JDate.incYear(in, i), i * 3);
		}

		return result;
	}

	public static void printList(List<?> L) {
		for (Object object : L) {
			System.out.println(object);
		}
	}

}
