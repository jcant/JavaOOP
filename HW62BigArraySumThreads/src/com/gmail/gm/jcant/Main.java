package com.gmail.gm.jcant;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		int size = 100000000;

		int[] testArray = new int[size];

		for (int i = 0; i < size; i++) {
			// testArray[i] = new Random().nextInt(500);
			testArray[i] = 2; // sum will be size*2
		}

		Thread th;

		for (int i = 1; i <= 100000; i = i * 10) {
			System.out.println("workers: " + i);

			th = new Thread(new BigArraySum(testArray, i));
			th.start();

			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
