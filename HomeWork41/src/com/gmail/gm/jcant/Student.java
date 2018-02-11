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
		// this.courseNum = courseNum;
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
		int result = 0;

		if (sortArrayBy == SortBy.NAME) {
			result = this.getName().compareTo(otherStudent.getName());
		} else if (sortArrayBy == SortBy.SURNAME) {
			result = this.getSurname().compareTo(otherStudent.getSurname());
		} else if (sortArrayBy == SortBy.INSTITUTE) {
			result = this.getInstitutionName().compareTo(otherStudent.getInstitutionName());
		} else if (sortArrayBy == SortBy.AGE) {
			result = this.getAge() - otherStudent.getAge();
		} else if (sortArrayBy == SortBy.COURSE) {
			result = this.getCourse() - otherStudent.getCourse();
		} else if (sortArrayBy == SortBy.WEIGHT) {
			result = (int) ((this.getWeight() - otherStudent.getWeight())
					/ Math.abs(this.getWeight() - otherStudent.getWeight()));
		} else if (sortArrayBy == SortBy.HEIGHT) {
			result = (int) ((this.getHeight() - otherStudent.getHeight())
					/ Math.abs(this.getHeight() - otherStudent.getHeight()));
		} else if (sortArrayBy == SortBy.AVGSCORE) {
			result = (int) ((this.getAvarageScore() - otherStudent.getAvarageScore())
					/ Math.abs(this.getAvarageScore() - otherStudent.getAvarageScore()));
		}

		if (!sortArrayBy.isAscending()) {
			result *= -1;
		}

		return result;
	}

	public enum SortBy {
		NAME(true), SURNAME(true), AGE(true), WEIGHT(true), HEIGHT(true), INSTITUTE(true), COURSE(true), AVGSCORE(true);

		private boolean ascending;

		private SortBy(boolean ascending) {
			this.ascending = ascending;
		}

		public boolean isAscending() {
			return ascending;
		}

		public SortBy setAscending(boolean ascending) {
			this.ascending = ascending;
			return this;
		}

	}

	public static void setSortArrayBy(SortBy sort) {
		sortArrayBy = sort;
	}

}
