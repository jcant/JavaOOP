package com.gmail.gm.jcant;

import java.text.ParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Group implements Voenkom, ObjectToDU, DUToObject {

	private final int GROUPSIZE = 10;
	private int studentsCount = 0;
	private Student[] students;

	public Group() {
		students = new Student[GROUPSIZE];
	}

	public Group(Student[] students) {
		this.students = new Student[GROUPSIZE];
		initGroupArray(students);
	}

	private final void initGroupArray(Student[] students) {

		if (students.length > GROUPSIZE) {
			throw new IndexOutOfBoundsException("Too big init array!");
		} else {
			for (Student student : students) {
				addStudent(student);
			}
		}
	}

	public void addStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("student is null");
		}

		if (studentsCount >= GROUPSIZE) {
			throw new IndexOutOfBoundsException("Too many students - group is full!");
		} else {
			students[studentsCount++] = student;
		}
	}

	public void interactiveAddStudent() {

		try {
			String name = getStringFromScanner("Name:");
			String surname = getStringFromScanner("Surname:");
			JDate birthday = getJDateFromScanner("Birthday(dd-mm-yyyy):");
			boolean male = getMaleFromScanner();
			double weight = getDoubleFromScanner("Weight:");
			double height = getDoubleFromScanner("Height:");
			String instName = getStringFromScanner("Institute name:");
			JDate dateIn = getJDateFromScanner("Institute date entered(dd-mm-yyyy):");
			double averageScore = getDoubleFromScanner("Average score:");

			addStudent(new Student(name, surname, birthday.getDate(), male, weight, height, instName, dateIn.getDate(),
					averageScore));
		} catch (InputMismatchException e) {
			System.err.println("wrong 'double' value! (" + e.getMessage() + ")");
		} catch (ParseException e) {
			System.err.println("wrong 'date' value! (" + e.getMessage() + ")");
		} catch (IllegalArgumentException e) {
			System.err.println("wrong value! (" + e.getMessage() + ")");
		}
	}

	private String getStringFromScanner(String message) throws IllegalArgumentException {
		String result = "";
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		result = sc.nextLine();
		if (result.equals("")) {
			throw new IllegalArgumentException("empty string argument");
		}

		return result;
	}

	private JDate getJDateFromScanner(String message) throws ParseException {
		JDate result = null;
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		result = new JDate(sc.nextLine(), "dd-MM-yyyy");
		return result;
	}

	private boolean getMaleFromScanner() throws IllegalArgumentException {
		boolean result = false;
		String input = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Is student a man? (yes/no):");
		input = sc.nextLine().toLowerCase();
		if (input.equals("yes")) {
			result = true;
		} else if (input.equals("no")) {
			result = false;
		} else {
			throw new IllegalArgumentException("wrong 'yes/no' answer");
		}
		return result;
	}

	private double getDoubleFromScanner(String message) throws InputMismatchException {
		Scanner sc = new Scanner(System.in);
		double result;
		System.out.println(message);
		result = sc.nextDouble();
		return result;
	}

	public void deleteStudent(int studentNumber) {
		if ((studentNumber >= studentsCount) || (studentNumber < 0) || (students[studentNumber] == null)) {
			throw new IllegalArgumentException("Wrong student number - can't delete!");
		} else {
			students[studentNumber] = null;
			consolidateArray();
			studentsCount--;
		}
	}

	private void consolidateArray() {
		int cnt2 = 0;
		for (int i = 0; i < students.length; i++) {
			if (students[i] != null) {
				students[cnt2] = students[i];
				cnt2++;
			}
		}

		while (cnt2 < students.length) {
			students[cnt2++] = null;
		}
	}

	public void clearGroup() {
		for (int i = 0; i < students.length; i++) {
			students[i] = null;
		}

		studentsCount = 0;
	}

	public Student[] findStudentBySurname(String surname) {
		Student[] result = new Student[0];

		for (int i = 0; i < studentsCount; i++) {
			if (surname.equals(students[i].getSurname())) {
				result = Arrays.copyOf(result, result.length + 1);
				System.arraycopy(new Student[] { students[i] }, 0, result, result.length - 1, 1);
			}
		}

		return result;
	}

	public void sortGroup(Student.SortBy field) {
		Student.setSortArrayBy(field);
		Arrays.sort(students, (s1, s2) -> Student.sortArrayBy.compare(s1, s2));
	}

	public int getStudentsCount() {
		return studentsCount;
	}

	public Student[] getStudentsArray() {
		Student[] result = new Student[studentsCount];
		for (int i = 0; i < studentsCount; i++) {
			result[i] = students[i];
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < students.length; i++) {
			sb.append(i + ") ");
			sb.append((students[i] != null) ? (students[i]) : ("NULL"));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	// Voenkom:
	@Override
	public Student[] conscription() {
		Group tmp = new Group();

		for (Student student : students) {
			if (student != null) {
				if (student.isMale() && (student.getAge() >= 18)) {
					tmp.addStudent(student);
				}
			}
		}

		return tmp.getStudentsArray();
	}

	// ObjectToDAO
	@Override
	public DataUnificator[] objectUnify() {

		DataUnificator[] result = new DataUnificator[studentsCount];

		for (int i = 0; i < studentsCount; i++) {
			result[i] = new DataUnificator("Student", students[i].objectUnify(), DataUnificator.OBJECT);
		}

		return result;
	}

	// DAOToObject
	@Override
	public void unifyToObject(DataUnificator[] data) throws IllegalArgumentException {

		clearGroup();
		for (DataUnificator du : data) {
			Student st = new Student();
			try {
				st.unifyToObject(du.getUData());
				this.addStudent(st);
			} catch (IllegalArgumentException e) {
				throw e;
			}

		}

	}

}
