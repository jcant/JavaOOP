package com.gmail.gm.jcant;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		
		LetterCounter lcount = new LetterCounter();
		
		//lcount.count();
		
		lcount.analizeText(
				  "a b c d e f g h"
				+ "a b c d e f g "
				+ "a b c d e f "
				+ "a b c d e "
				+ "a b c d "
				+ "a b c "
				+ "a b"
				+ "a");
		
		System.out.println(lcount);
		System.out.println("-------------------------------");
		
		lcount.analizeText(new File("d:\\TMP\\alice.txt"));
		
		System.out.println(lcount);
	}

}
