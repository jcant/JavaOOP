package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {
		
		RefParser rp = new RefParser("https://prog.kiev.ua/forum/");
		
		System.out.println(rp.getSiteContent());
	}

}
