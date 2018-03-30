package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {

		DoubleColaAutomat cola = new DoubleColaAutomat();
		fillAutomatQueue(cola);
		System.out.println("START:");
		System.out.println(cola);

		//for (int i = 0; i < 5; i++) {
			cola.processSell(1);
		//}

		System.out.println("1 step:");
		System.out.println(cola);

	}

	public static void fillAutomatQueue(DoubleColaAutomat dcau) {
		dcau.addToQueue(new BBTPers("Sheldon"));
		dcau.addToQueue(new BBTPers("Leonard"));
		dcau.addToQueue(new BBTPers("Volovitc"));
		dcau.addToQueue(new BBTPers("Kutrapalli"));
		dcau.addToQueue(new BBTPers("Penny"));
	}

}
