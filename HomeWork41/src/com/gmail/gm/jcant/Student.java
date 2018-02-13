package com.gmail.gm.jcant;

import java.util.Date;

public class Student extends Human implements Comparable {

	private static SortBy sortArrayBy = SortBy.SURNAME;

	private String institutionName;
	private JDate dateIn;
	private double averageScore;

	public Student() {
		super();
	}

	public Student(String name, String surname, Date birthday, boolean male, double weight, double height,
			String institutionName, Date dateIn, double avarageScore) {
		super(name, surname, birthday, male, weight, height);
		this.institutionName = institutionName;
		this.dateIn = new JDate(dateIn);
		this.averageScore = avarageScore;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%-8.8s %-10.10s", getName(), getSurname() + ","));
		sb.append(String.format("  %-8.8s", ((isMale()) ? ("man") : ("woman") + ",")));
		sb.append(String.format("Age: %-6.6s", getAge() + ","));
		sb.append(String.format("Inst: %-14.14s", institutionName + ","));
		sb.append(String.format("AvgScore: %2.2f \t", averageScore));
		sb.append("Course: " + getCourse() + " ");
		return sb.toString();
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public int getCourse() {
		return 1 + dateIn.getDifferenceYears(new Date());
	}

	public Date getDateIn() {
		return dateIn.getDate();
	}

	public void setDateIn(Date dateIn) {
		this.dateIn.setDate(dateIn);
	}

	public double getAvarageScore() {
		return averageScore;
	}

	public void setAvarageScore(double avarageScore) {
		this.averageScore = avarageScore;
	}

	@Override
	public int compareTo(Object o) {
		Student otherStudent = (Student) o;

		return sortArrayBy.compare(this, otherStudent);
	}

	public static void setSortArrayBy(SortBy sort) {
		sortArrayBy = sort;
	}
}
