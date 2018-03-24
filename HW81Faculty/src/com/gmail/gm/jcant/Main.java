package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {
		
		Student[] sts = new Student[5];

		for(int i = 0; i<sts.length; i++) {
			sts[i] = new Student("Name"+i, "Surname"+i, null, ((i%2)==0), 2*i, 2*i, "Institute"+i, null, i);
		}
		
		Group gr1 = new Group(sts);
		Group gr2 = new Group(sts);
		Group gr3 = gr1.clone();
		
		System.out.println("gr1 equals gr2 = "+ gr1.equals(gr2));
		System.out.println("gr1 equals gr3 = "+ gr1.equals(gr3));
		System.out.println("gr2 equals gr3 = "+ gr2.equals(gr3));
		
		System.out.println();
		gr1.getStudent(0).setName("Vasya");
		
		System.out.println("gr1 equals gr2 = "+ gr1.equals(gr2));
		System.out.println("gr1 equals gr3 = "+ gr1.equals(gr3));
		System.out.println("gr2 equals gr3 = "+ gr2.equals(gr3));
		
		System.out.println(gr1);
		System.out.println();
		System.out.println(gr2);
		System.out.println();
		System.out.println(gr3);
	}

}
