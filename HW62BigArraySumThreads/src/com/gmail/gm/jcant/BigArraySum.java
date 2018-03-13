package com.gmail.gm.jcant;

import java.util.*;

public class BigArraySum implements Runnable, AddResult {
	// private List<Long> sumParts = new LinkedList<Long>();
	private int[] array;
	private int threadParts = 1;
	private long[] sumArr;

	public BigArraySum(int[] array, int threadParts) {
		super();
		this.array = array;
		setPartsNum(threadParts);
		sumArr = new long[threadParts];
	}

	private final void setPartsNum(int parts) {
		while (array.length / parts < 2) {
			parts = parts - 1;
			System.out.println("Reduce thread numbers to " + parts);
		}
		threadParts = parts;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	private void getArraySum(int parts) {
		long timeStart = new Date().getTime();
		Thread[] worker = new Thread[parts];
		int delta = array.length / parts;

		for (int i = 0; i < parts; i += 1) {
			int from = i * delta;
			int to = ((array.length % parts != 0) && ((i + 1) == parts)) ? (i * delta + delta)	: (i * delta + delta - 1);
			worker[i] = new Thread(new ArrayPartSum(from, to, array, this, i));
			worker[i].start();
		}

		for (int i = 0; i < parts; i++) {
			try {
				worker[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.print("SUMM=" + resultSum() + "\t");

		long timeEnd = new Date().getTime();
		System.out.println("time: " + (timeEnd - timeStart) + "ms");

	}

	private long resultSum() {
		long result = 0;
		// ListIterator<Long> it = sumParts.listIterator();
		// while (it.hasNext()) {
		// System.out.println(it.next());
		// }

		for (long i : sumArr) {
			result += i;
		}

		return result;
	}

	@Override
	public void addResult(long result, int num) {
		// summParts.add(result);
		sumArr[num] = result;
	}

	@Override
	public void run() {
		getArraySum(threadParts);
	}

}
