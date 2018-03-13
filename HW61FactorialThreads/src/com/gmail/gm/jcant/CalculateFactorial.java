package com.gmail.gm.jcant;

import java.math.BigInteger;

public class CalculateFactorial implements Runnable {

	private int number = 0;

	public CalculateFactorial(int number) {
		super();
		this.number = number;
	}

	private String factorial(int x) {
		if (x == 0) {
			return "0";
		}

		BigInteger f = new BigInteger("1");

		for (int i = 1; i <= x; i += 1) {

			f = f.multiply(new BigInteger("" + i));
		}

		return f.toString();
	}

	@Override
	public void run() {

		Thread th = Thread.currentThread();

		System.out.println("Thread: " + th.getName() + "\t " + this.number + "!=" + factorial(this.number));

	}

}
