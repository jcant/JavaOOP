package com.gmail.gm.jcant;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Student[] st1 = generateStudentArray(8, "Ivan", "Ivanov", JDate.getDate("01-01-1995"), JDate.getDate("01-09-2009"));
		
		Group gr1 = new Group("Group One");
		for (Student student : st1) {
			gr1.addStudent(student);
		}
		
		Group gr2 = new Group("Group Two", st1);
		
		System.out.println(gr1);
		System.out.println(gr2);
		System.out.println("is equals gr1 and gr2 =>"+gr1.equals(gr2));
		
		gr2.sortGroup(Student.SortBy.SURNAME.setAscending(false));
		
		System.out.println();
		System.out.println(gr2);
		
		Student[] army = gr1.conscription();
		System.out.println();
		for (Student student : army) {
			System.out.println(student);
		}
		

	}

	public static Student[] generateStudentArray(int count, String name, String sname, Date birthday, Date in) {
		Student[] result = new Student[count];

		Random rr = new Random();
		
		for (int i = 0; i < count; i++) {
			result[i] = new Student(name + i, sname + rr.nextInt(count*2), JDate.incYear(birthday, i), ((i % 2) == 0), 2 * i, 2 * i,
					"Inst" + i, JDate.incYear(in, i), i * 3);
		}

		return result;
	}
}
