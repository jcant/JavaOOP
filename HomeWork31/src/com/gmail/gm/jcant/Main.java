package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {

		Student stud1 = new Student("Jenya", "Isaev", JDate.getDate("25-11-2002"), true, 60.5, 170.2, "KPI", JDate.getDate("01-09-2016"), 10.5);
		Student stud2 = new Student("Tanya", "Isaeva", JDate.getDate("16-03-1979"), false, 48.5, 151, "KPI", JDate.getDate("01-09-2014"), 9.6);
		
		System.out.println("Student1: " + stud1);
		System.out.println("Student2: " + stud2);
		
		Student[] arrSt = {stud1, stud2,stud1, stud2,stud1, stud2,stud1, stud2,stud1, stud2};
		
		Group gr1 = new Group(arrSt);
		
		
		System.out.println("Group1:");
		System.out.println(gr1);


	}

}
