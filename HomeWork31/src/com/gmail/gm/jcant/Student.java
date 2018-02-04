package com.gmail.gm.jcant;

import java.util.Date;

public class Student extends Human {

	private String institutionName;
	private JDate dateIn;
	private double avarageScore;

	public Student() {
		super();
	}

	public Student(String name, String surname, Date birthday, boolean male, double weight, double height,
			String institutionName, Date dateIn, double avarageScore) {
		super(name, surname, birthday, male, weight, height);
		this.institutionName = institutionName;
		// this.courseNum = courseNum;
		this.dateIn = new JDate(dateIn);
		this.avarageScore = avarageScore;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%-10.10s %-10.10s", getName(), getSurname() + ","));
		sb.append(String.format("  %-8.8s", ((isMale()) ? ("man") : ("woman") + ",")));
		sb.append(String.format("Age: %-8.8s", getAge() + ","));
		sb.append(String.format("Institute: %-10.10s", institutionName + ","));
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
		return avarageScore;
	}

	public void setAvarageScore(double avarageScore) {
		this.avarageScore = avarageScore;
	}

}
