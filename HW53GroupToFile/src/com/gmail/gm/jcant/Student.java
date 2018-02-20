package com.gmail.gm.jcant;

import java.util.Date;

public class Student extends Human implements ObjectToDAO, DAOToObject {

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

	// -- DAO --
	@Override
	public DataUnificator[] objectUnify() {
		DataUnificator name = new DataUnificator("name", getName(), DataUnificator.STRING);
		DataUnificator surname = new DataUnificator("surname", getSurname(), DataUnificator.STRING);
		DataUnificator birthday = new DataUnificator("birthday", JDate.getDate(getBirthday()), DataUnificator.DATE);
		DataUnificator male = new DataUnificator("male", (isMale()) ? ("true") : ("false"), DataUnificator.BOOLEAN);
		DataUnificator weight = new DataUnificator("weight", "" + getWeight(), DataUnificator.DOUBLE);
		DataUnificator height = new DataUnificator("height", "" + getHeight(), DataUnificator.DOUBLE);
		DataUnificator institutionName = new DataUnificator("institutionName", this.institutionName,
				DataUnificator.STRING);
		DataUnificator dateIn = new DataUnificator("dateIn", JDate.getDate(getDateIn()), DataUnificator.DATE);
		DataUnificator averageScore = new DataUnificator("averageScore", "" + this.averageScore, DataUnificator.DOUBLE);

		DataUnificator[] result = new DataUnificator[] { name, surname, birthday, male, weight, height, institutionName,
				dateIn, averageScore };

		return result;
	}

	@Override
	public void unifyToObject(DataUnificator[] data) throws IllegalArgumentException {
		for (DataUnificator item : data) {
			if (item.getName().equals("name") && item.getType() == DataUnificator.STRING) {
				this.setName(item.getData());
			} else if (item.getName().equals("surname") && item.getType() == DataUnificator.STRING) {
				this.setSurname(item.getData());
			} else if (item.getName().equals("birthday") && item.getType() == DataUnificator.DATE) {
				this.setBirthday(JDate.getDate(item.getData()));
			} else if (item.getName().equals("male") && item.getType() == DataUnificator.BOOLEAN) {
				this.setMale(item.getData().equals("true") ? (true) : false);
			} else if (item.getName().equals("weight") && item.getType() == DataUnificator.DOUBLE) {
				this.setWeight(Double.parseDouble(item.getData()));
			} else if (item.getName().equals("height") && item.getType() == DataUnificator.DOUBLE) {
				this.setHeight(Double.parseDouble(item.getData()));
			} else if (item.getName().equals("institutionName") && item.getType() == DataUnificator.STRING) {
				this.setInstitutionName(item.getData());
			} else if (item.getName().equals("dateIn") && item.getType() == DataUnificator.DATE) {
				this.setDateIn(JDate.getDate(item.getData()));
			} else if (item.getName().equals("averageScore") && item.getType() == DataUnificator.DOUBLE) {
				this.setAvarageScore(Double.parseDouble(item.getData()));
			} else {
				throw new IllegalArgumentException();
			}
		}

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
