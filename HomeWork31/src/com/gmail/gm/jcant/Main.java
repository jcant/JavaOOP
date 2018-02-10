package com.gmail.gm.jcant;

import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Group[] groupsArray = new Group[3];

		testArrayInit(groupsArray);
		printGroups(groupsArray);

		testAddStudents(groupsArray[0], 5);
		testAddStudents(groupsArray[1], 10);
		printGroups(groupsArray);

		testDeleteStudents(groupsArray[0], new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		testDeleteStudents(groupsArray[2], new int[] { 0, 5, 12 });
		printGroups(groupsArray);

		// test find by surname:
		Group gr2 = new Group();
		for (int i = 0; i < 5; i++) {
			try {
				gr2.addStudent(new Student("Sveta" + i, "Ivanova1", JDate.getDate("16-03-1999"), false, 45, 160, "SVMI",
						JDate.getDate("01-09-2015"), 11.5));
				gr2.addStudent(new Student("Sveta" + 2 * (i + 1), "Ivanova2", JDate.getDate("16-03-1999"), false, 45,
						160, "SVMI", JDate.getDate("01-09-2015"), 11.5));
			} catch (TooManyStudentsStudentGroupException e) {
				e.printStackTrace();
			}

		}

		System.out.println(gr2);

		Student[] students = gr2.findStudentBySurname("Ivanova1");
		for (Student student : students) {
			System.out.println(student);
		}

		// try add null student:
		try {
			gr2.addStudent(null);
		} catch (TooManyStudentsStudentGroupException e) {
			e.printStackTrace();
		}

	}

	public static void testArrayInit(Group[] groups) {
		for (int i = 0; i < groups.length; i++) {
			// get random students array:
			Student[] arrSt = getStudentsArray((int) (1 + Math.random() * 15));

			groups[i] = new Group(arrSt);
		}
	}

	public static void testAddStudents(Group gr, int howMany) {
		Student[] stArray = getStudentsArray(howMany);
		for (int i = 0; i < stArray.length; i++) {
			System.out.println("Add student " + (i + 1) + " from " + howMany);
			try {
				gr.addStudent(stArray[i]);
			} catch (TooManyStudentsStudentGroupException e) {
				System.err.println("Error add student");
				System.err.println(e);
				break;
			}

		}
	}

	public static void testDeleteStudents(Group gr, int[] inds) {
		for (int i = 0; i < inds.length; i++) {
			System.out.println("Delete student " + inds[i] + " from group");
			try {
				gr.deleteStudent(inds[i]);
			} catch (InvalidStudentNumberStudentGroupException e) {
				System.err.println("Error delete student");
				System.err.println(e);
			}
		}
	}

	public static void printGroups(Group[] groups) {
		for (int i = 0; i < groups.length; i++) {
			System.out.println("Group " + i + ":");
			System.out.println(groups[i]);
		}
	}

	public static Student[] getStudentsArray(int size) {
		Student[] result = new Student[size];
		for (int i = 0; i < size; i++) {
			Date dayBirth = JDate.getDate("" + (int) (1 + Math.random() * 30) + "-" + (int) (1 + Math.random() * 12)
					+ "-" + (int) (1995 + Math.random() * 15));
			Date dayIn = JDate.getDate("" + (int) (1 + Math.random() * 30) + "-" + (int) (1 + Math.random() * 12) + "-"
					+ (int) (2015 + Math.random() * 3));

			result[i] = new Student(getWord(), getWord(), dayBirth, ((i % 2) == 0), 60.5, 170.2, "KPI", dayIn, 10.5);
		}

		return result;
	}

	public static String getWord() {
		int length = (int) (3 + Math.random() * 3);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char ch = (i == 0) ? ((char) (65 + Math.random() * 25)) : ((char) (97 + Math.random() * 25));
			sb.append(ch);
		}

		return sb.toString();
	}

}
