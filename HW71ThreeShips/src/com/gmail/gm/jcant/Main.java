package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {

		SeaPort port = new SeaPort(2);

		Ship ship1 = new Ship("Parabellum", 10, 500);
		Ship ship2 = new Ship("Isabel", 10, 500);
		Ship ship3 = new Ship("Brigantina", 10, 500);

		port.dockShip(ship1);
		port.dockShip(ship2);
		port.dockShip(ship3);

	}

}
