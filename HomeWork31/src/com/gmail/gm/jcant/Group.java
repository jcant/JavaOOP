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
	
	private final void initGroupArray(Student[] students) {
		if (students.length > GROUPSIZE) {
			throw new IndexOutOfBoundsException();
		} else {
			this.students = Arrays.copyOf(students, GROUPSIZE);
			this.studentsCount = students.length;
		}
	}

	// FIX: list of students must be sorted...
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<students.length; i++) {
			sb.append((i+1)+") ");
			sb.append((students[i]!=null)?(students[i]):("NULL"));
			sb.append(System.lineSeparator());	
		}
		
		return sb.toString();
	}
	
		

}
