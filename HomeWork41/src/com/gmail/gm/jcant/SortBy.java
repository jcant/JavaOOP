package com.gmail.gm.jcant;

public enum SortBy {
	NAME(true) {
		@Override
		public int compare(Student student1, Student student2) {
			return getDirection() * student1.getName().compareTo(student2.getName());
		}
	},
	SURNAME(true) {
		@Override
		public int compare(Student student1, Student student2) {
			return getDirection() * student1.getSurname().compareTo(student2.getSurname());
		}
	},
	AGE(true) {
		@Override
		public int compare(Student student1, Student student2) {
			return getDirection() * (student1.getAge() - student2.getAge());
		}
	},
	WEIGHT(true) {
		@Override
		public int compare(Student student1, Student student2) {
			return getDirection() * ((int) ((student1.getWeight() - student2.getWeight())
					/ Math.abs(student1.getWeight() - student2.getWeight())));
		}
	},
	HEIGHT(true) {
		@Override
		public int compare(Student student1, Student student2) {
			return getDirection() * ((int) ((student1.getHeight() - student2.getHeight())
					/ Math.abs(student1.getHeight() - student2.getHeight())));
		}
	},
	INSTITUTE(true) {
		@Override
		public int compare(Student student1, Student student2) {
			return getDirection() * student1.getInstitutionName().compareTo(student2.getInstitutionName());
		}
	},
	COURSE(true) {
		@Override
		public int compare(Student student1, Student student2) {
			return getDirection() * (student1.getCourse() - student2.getCourse());
		}
	},
	AVGSCORE(true) {
		@Override
		public int compare(Student student1, Student student2) {
			return getDirection() * ((int) ((student1.getAvarageScore() - student2.getAvarageScore())
					/ Math.abs(student1.getAvarageScore() - student2.getAvarageScore())));
		}
	};

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

	public abstract int compare(Student student1, Student student2);
}
