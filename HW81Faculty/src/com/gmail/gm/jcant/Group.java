package com.gmail.gm.jcant;

import java.io.Serializable;
import java.util.Arrays;

public class Group implements Voenkom, Cloneable, Serializable {

	private static final long serialVersionUID = 3L;

	private final int GROUPSIZE = 10;
	private String groupName;
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
		if (students == null) {
			throw new IllegalArgumentException("students array is null. Cant't init");
		}

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
			student.setGroup(this);
		}
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
		if (surname == null) {
			throw new IllegalArgumentException("Surname is null");
		}

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getStudentsCount() {
		return studentsCount;
	}

	private void setStudentsCount(int studentsCount) {
		this.studentsCount = studentsCount;
	}

	private void setStudents(Student[] students) {
		this.students = students.clone();
	}

	public Student[] getStudentsArray() {
		Student[] result = new Student[studentsCount];
		for (int i = 0; i < studentsCount; i++) {
			result[i] = students[i];
		}
		return result;
	}

	public Student getStudent(int number) {
		if ((number < 0) || (number >= studentsCount)) {
			throw new IndexOutOfBoundsException();
		}

		return students[number];
	}

	public double getAverageScore() {
		double sum = 0;
		for (int i = 0; i < studentsCount; i++) {
			sum += students[i].getAvarageScore();
		}

		return sum / studentsCount;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Group: " + groupName + System.lineSeparator());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + GROUPSIZE;
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + Arrays.hashCode(students);
		result = prime * result + studentsCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Group other = (Group) obj;

		if (GROUPSIZE != other.GROUPSIZE) {
			return false;
		}

		if (groupName == null) {
			if (other.groupName != null) {
				return false;
			}
		} else if (!groupName.equals(other.groupName)) {
			return false;
		}

		if (!Arrays.equals(students, other.students)) {
			return false;
		}

		if (studentsCount != other.studentsCount) {
			return false;
		}

		return true;
	}

	@Override
	protected Group clone() {

		Group result = new Group();

		if (groupName != null) {
			result.setGroupName(new String(groupName));
		}

		result.setStudentsCount(studentsCount);

		if (students != null) {
			Student[] newArray = new Student[students.length];
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null) {
					newArray[i] = students[i].clone();
				}
			}
			result.setStudents(newArray);

			result.setGroupRef();
		}

		return result;
	}

	private void setGroupRef() {
		if (students != null) {
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null) {
					students[i].setGroup(this);
				}
			}
		}
	}

}
