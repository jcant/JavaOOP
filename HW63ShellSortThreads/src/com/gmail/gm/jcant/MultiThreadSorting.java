package com.gmail.gm.jcant;

public class MultiThreadSorting {

	static void sort(int[] array, int threadNumber) {
		long timeStart = System.currentTimeMillis();

		while (array.length / threadNumber < 2) {
			threadNumber = threadNumber - 1;
			System.out.println("Reduce thread numbers to " + threadNumber);
		}
		
		SingleThreadSorting[] threadarray = new SingleThreadSorting[threadNumber];
		for (int i = 0; i < threadarray.length; i++) {
			int size = array.length / threadNumber;
			int begin = size * i;
			int end = ((array.length % threadNumber != 0) && ((i + 1) == threadNumber)) ? (array.length) : (i * size + size);
			threadarray[i] = new SingleThreadSorting(array, begin, end);
		}

		for (int i = 0; i < threadarray.length; i++) {
			try {
				threadarray[i].getThr().join();
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}

		System.arraycopy(mergeArrays(array, threadarray), 0, array, 0, array.length);

		long timeFinish = System.currentTimeMillis();
		System.out.println("Time: " + (timeFinish - timeStart) + " ms");
	}

	private static int[] mergeArrays(int[] array, SingleThreadSorting[] threadarray) {
		int[] arr = new int[array.length];
		for (int i = 0; i < arr.length; i++) {
			int min = Integer.MAX_VALUE;
			int k = -1;
			for (int j = 0; j < threadarray.length; j++) {
				if (!threadarray[j].isStop() && min > threadarray[j].peekElement()) {
					min = threadarray[j].peekElement();
					k = j;
				}
			}
			if (k != -1) {
				arr[i] = threadarray[k].pollElement();
			}
		}
		return arr;
	}
}
