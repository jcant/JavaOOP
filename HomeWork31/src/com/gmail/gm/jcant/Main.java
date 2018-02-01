package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {
		
		Human hum1 = new Human("Anton", "Isaev", JDate.getDate("07-11-1978"), true, 80, 185);
		Human hum2 = new Human("Jenya", "Isaev", JDate.getDate("25-11-2002"), true, 60.5, 170.2);
		
		
		//hum1.setName();
		
		System.out.println("HUMAN1: "+hum1);
		System.out.println("HUMAN2: "+hum2);
		
	}

}
