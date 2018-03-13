package com.gmail.gm.jcant;

import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		int[] array = new int[70000];
		Random rn = new Random();
		for (int i = 0; i < array.length; i++) {
			// array[i] = rn.nextInt(20);
			array[i] = array.length - 1 - i;
		}

		System.out.println("START");

		int[] array1 = array.clone();
		int[] array2 = array.clone();
		int[] array3 = array.clone();

		System.out.println("Static sort method:");
		sort(array);

		System.out.println("Static ShellSort method:");
		ShellSort(array1);

		System.out.println("MultiThread sort:");
		for (int i = 1; i <= 10; i++) {
			System.out.println("Threads number: " + i);
			int[] tmpArray = array2.clone();
			MultiThreadSorting.sort(tmpArray, i);
		}

		long tstart = System.currentTimeMillis();
		Arrays.sort(array3);
		long tend = System.currentTimeMillis();
		System.out.println();
		System.out.println((tend - tstart) + " ms - Arrays sort");
	}

	static void sort(int[] array) {
		long timeStart = System.currentTimeMillis();
		int temp;
		for (int i = 1; i < array.length; i++) {
			int k = i - 1;
			temp = array[i];
			while ((k >= 0) && (array[k] > temp)) {
				array[k + 1] = array[k];
				array[k] = temp;
				k--;
			}
		}
		long timeFinish = System.currentTimeMillis();
		System.out.println("Time: " + (timeFinish - timeStart) + " ms");
	}

	public static void ShellSort(int mass[]) {
		long timeStart = System.currentTimeMillis();
		int begin = 0;
		int end = mass.length;
		int tmp;

		for (int step = (end - begin) / 2; step > 0; step /= 2) {
			for (int i = step + begin; i < end; i++) {
				tmp = mass[i];
				int j;
				for (j = i; j >= step + begin; j -= step) {
					if (tmp < mass[j - step]) {
						mass[j] = mass[j - step];
					} else {
						break;
					}
				}
				mass[j] = tmp;
			}
		}

		long timeFinish = System.currentTimeMillis();
		System.out.println("Time: " + (timeFinish - timeStart) + " ms");
	}

}
