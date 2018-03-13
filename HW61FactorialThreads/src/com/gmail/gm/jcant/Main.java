package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {

		int count = 100;

		Thread[] threads_array = new Thread[count];

		for (int i = 0; i < count; i++) {
			threads_array[i] = new Thread(new CalculateFactorial(i));
			threads_array[i].start();
		}

	}

}
