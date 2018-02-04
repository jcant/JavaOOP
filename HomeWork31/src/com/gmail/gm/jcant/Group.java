package com.gmail.gm.jcant;

import java.util.Arrays;

public class Group {

	private final int GROUPSIZE = 10;
	private int studentsCount = 0;
	private Student[] students;

	public Group() {
		students = new Student[GROUPSIZE];
	}

	public Group(Student[] students) {
		initGroupArray(students);
	}

	private final void initGroupArray(Student[] students) throws TooBigInitArrayStudentGroupException {
		if (students.length > GROUPSIZE) {
			throw new TooBigInitArrayStudentGroupException();
		} else {
			this.students = Arrays.copyOf(students, GROUPSIZE);
			this.studentsCount = students.length;
		}
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

	public void addStudent(Student student) throws TooManyStudentsStudentGroupException {
		if (studentsCount >= GROUPSIZE) {
			throw new TooManyStudentsStudentGroupException();
		} else {
			students[studentsCount++] = student;
		}
	}

	public void deleteStudent(int studentNumber) throws InvalidStudentNumberStudentGroupException {
		if ((studentNumber >= studentsCount) || (studentNumber < 0) || (students[studentNumber] == null)) {
			throw new InvalidStudentNumberStudentGroupException();
		} else {
			students[studentNumber] = null;
			consolidateArray();
			studentsCount--;
		}
	}

	public void clearGroup() {
		for (int i = 0; i < students.length; i++) {
			students[i] = null;
		}

		studentsCount = 0;
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

	private Student[] sortArray(Student[] array) {
		Student[] tmpArr = Arrays.copyOf(array, array.length);
		Student[] result = new Student[GROUPSIZE];
		int cnt = 0;
		boolean flag = true;
		while (flag) {
			Student st = findFirst(tmpArr);
			if (st != null) {
				result[cnt++] = st;
			} else {
				flag = false;
			}
		}

		return result;

	}

	private Student findFirst(Student[] array) {
		Student result = array[0];
		int index = 0;
		for (int i = 1; i < array.length; i++) {
			if ((array[i]) != null) {
				if ((result == null) || (compareStrings(array[i].getSurname(), result.getSurname()) <= 0)) {
					result = array[i];
					index = i;
				}
			}
		}

		array[index] = null;
		return result;
	}

	private int compareStrings(String str1, String str2) {
		int result = 0;
		int minLength = (str1.length() < str2.length()) ? (str1.length()) : (str2.length());
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();

		for (int i = 0; i < minLength; i++) {
			if (str1.charAt(i) < str2.charAt(i)) {
				result = -1;
				break;
			} else if (str1.charAt(i) > str2.charAt(i)) {
				result = 1;
				break;
			}
		}

		if ((result == 0) && (str1.length() > str2.length())) {
			result = 1;
		} else if ((result == 0) && (str1.length() < str2.length())) {
			result = -1;
		}

		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Student[] sorted = sortArray(students);
		for (int i = 0; i < sorted.length; i++) {
			sb.append(i + ") ");
			sb.append((sorted[i] != null) ? (sorted[i]) : ("NULL"));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

}
