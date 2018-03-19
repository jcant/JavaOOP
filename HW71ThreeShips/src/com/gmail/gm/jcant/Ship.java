package com.gmail.gm.jcant;

public class Ship implements Runnable {

	private String name;
	private int cargoNum;
	private long delay;

	public Ship(String name, int cargoNum, long delay) {
		super();
		this.name = name;
		this.cargoNum = cargoNum;
		this.delay = delay;
	}

	public String getName() {
		return name;
	}

	private void boxUnload() {
		try {
			Thread.sleep(delay);
			cargoNum -= 1;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(name + ": boxes left: " + cargoNum);
	}

	private void unloadAll() {
		while (cargoNum > 0) {
			boxUnload();
		}

		System.out.println("*** " + name + " is draw off");
	}

	@Override
	public void run() {
		System.out.println(name + " start unload...");
		unloadAll();
	}

}
