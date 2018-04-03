package com.gmail.gm.jcant;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		Random rr = new Random();
		Integer[] array = new Integer[10];
		for (int i = 0; i < array.length; i++) {
			array[i] = -10 + rr.nextInt(20);
		}

		// array = new Integer[] { 2, -2, 2, -2, 2, -2, 2, -2, 2, -2, 2 };

		printArr(array);

		System.out.println("The nearest to zero is: " + findNullAbove(array));

	}

	public static void printArr(Integer[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	public static int findNullAbove(Integer[] arr) {
		int result = 0;

		Comparator<Integer> comp = (p1, p2) -> {

			if ((Math.abs(p1) == Math.abs(p2)) && (p2 > 0)) {
				return 1;
			}
			return Integer.compare(Math.abs(p1), Math.abs(p2));

		};

		result = Arrays.stream(arr).min(comp).get();

		return result;
	}

}
