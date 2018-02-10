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
		this.students = new Student[GROUPSIZE];
		try {
			initGroupArray(students);
		} catch (StudentGroupExceptions e) {
			e.printStackTrace();
		}
	}

	private final void initGroupArray(Student[] students) throws StudentGroupExceptions {

		if (students.length > GROUPSIZE) {
			throw new TooBigInitArrayStudentGroupException();
		} else {
			for (Student student : students) {
				addStudent(student);
			}
		}
	}

	public void addStudent(Student student) throws TooManyStudentsStudentGroupException {
		if (student == null) {
			throw new IllegalArgumentException("student is null");
		}

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
				if ((result == null) || (array[i].getSurname().compareTo(result.getSurname()) <= 0)) {
					result = array[i];
					index = i;
				}
			}
		}

		array[index] = null;
		return result;
	}

	public int getStudentsCount() {
		return studentsCount;
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
