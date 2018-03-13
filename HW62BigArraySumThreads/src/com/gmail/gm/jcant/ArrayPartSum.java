package com.gmail.gm.jcant;

public class ArrayPartSum implements Runnable {

	private int from = 0;
	private int to = 0;
	private int[] array;
	private AddResult saveLink;
	private int partNum;

	public ArrayPartSum(int from, int to, int[] array, AddResult saveLink, int partNum) {
		super();
		this.from = from;
		this.to = to;
		this.array = array;
		this.saveLink = saveLink;
		this.partNum = partNum;
	}

	private long calcSum() {
		if (array == null) {
			throw new IllegalArgumentException();
		}
		if ((from < 0) || (from >= to) || (from > array.length)) {
			throw new IllegalArgumentException();
		}
		if (to > array.length) {
			throw new IllegalArgumentException();
		}

		long result = 0;
		for (int i = from; i <= to; i += 1) {
			result += array[i];
		}
		return result;
	}

	@Override
	public void run() {
		saveLink.addResult(calcSum(), partNum);
	}

}
