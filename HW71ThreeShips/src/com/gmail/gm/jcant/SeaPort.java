package com.gmail.gm.jcant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SeaPort {

	private int docksNum = 2;
	private ExecutorService service;

	public SeaPort() {
		super();
		service = Executors.newFixedThreadPool(docksNum);
	}

	public SeaPort(int docksNum) {
		super();
		this.docksNum = docksNum;
		service = Executors.newFixedThreadPool(docksNum);
	}

	public void dockShip(Ship ship) {
		System.out.println("Ship '" + ship.getName() + "' in queue");
		service.submit(ship);
	}
	
	public void endWork() {
		service.shutdown();
	}

}
