package com.gmail.gm.jcant;

import java.util.Date;

public class Student extends Human {

	static SortBy sortArrayBy = SortBy.SURNAME;

	private String institutionName;
	private JDate dateIn;
	private double averageScore;

	public Student() {
		super();
		dateIn = new JDate();
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

	// -- sorting --

	public static void setSortArrayBy(SortBy sort) {
		sortArrayBy = sort;
	}

	public enum SortBy {
		NAME(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * student1.getName().compareTo(student2.getName());
			}
		},
		SURNAME(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * student1.getSurname().compareTo(student2.getSurname());
			}
		},
		AGE(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * (student1.getAge() - student2.getAge());
			}
		},
		WEIGHT(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * ((int) ((student1.getWeight() - student2.getWeight())
						/ Math.abs(student1.getWeight() - student2.getWeight())));
			}
		},
		HEIGHT(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * ((int) ((student1.getHeight() - student2.getHeight())
						/ Math.abs(student1.getHeight() - student2.getHeight())));
			}
		},
		INSTITUTE(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * student1.getInstitutionName().compareTo(student2.getInstitutionName());
			}
		},
		COURSE(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * (student1.getCourse() - student2.getCourse());
			}
		},
		AVGSCORE(true) {
			@Override
			public int compare(Student student1, Student student2) {
				if (isAnyNull(student1, student2) != NO_NULL) {
					return getDirection() * isAnyNull(student1, student2);
				}
				return getDirection() * ((int) ((student1.getAvarageScore() - student2.getAvarageScore())
						/ Math.abs(student1.getAvarageScore() - student2.getAvarageScore())));
			}
		};

		public final int NO_NULL = 17;
		private int direction;

		private SortBy(boolean ascending) {
			this.direction = (ascending) ? (1) : (-1);
		}

		public boolean isAscending() {
			return (direction > 0) ? (true) : (false);
		}

		public SortBy setAscending(boolean ascending) {
			this.direction = (ascending) ? (1) : (-1);
			return this;
		}

		public int getDirection() {
			return direction;
		}

		public void setDirection(int direction) {
			this.direction = direction;
		}

		public int isAnyNull(Student st1, Student st2) {
			if (st1 == null && st2 == null) {
				return 0;
			}
			if (st1 == null && st2 != null) {
				return 1;
			}
			if (st1 != null && st2 == null) {
				return -1;
			}
			return NO_NULL;
		}

		public abstract int compare(Student student1, Student student2);
	}

}
