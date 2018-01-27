package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {

		Triangle trOne   = new Triangle();
		Triangle trTwo   = new Triangle(5.5, 4.1, 3.1);
		Triangle trThree = new Triangle(1.0, 2.0, 3.0);

		trOne.setSideA(2.3);
		trOne.setSideB(2.4);
		trOne.setSideC(2.1);

		System.out.println("The first  triangle: " + trOne);
		System.out.println("The second triangle: " + trTwo);
		System.out.println("The third  triangle: " + trThree);
		System.out.println();
		System.out.println("The first  triangle square: " + trOne.getSquare());
		System.out.println("The second triangle square: " + trTwo.getSquare());
		System.out.println("The third  triangle square: " + trThree.getSquare());
	}

}
