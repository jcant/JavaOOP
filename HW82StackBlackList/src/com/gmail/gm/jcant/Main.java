package com.gmail.gm.jcant;

import java.io.File;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Stack st = new Stack();
		
		st.push(new Integer(15));
		st.push(new Double(15));
		st.push(new String("*** some string ***"));
		st.push(new File("/home/file.txt"));
		
		st.addToBlackList(Date.class);
		
		//st.push(new Date());

		st.removeFromBlackList(Date.class);
		
		st.push(new Date());
		
		System.out.println(st);
		System.out.println();
		
		while(!st.isEmpty()) {
			System.out.println();
			System.out.println(st.pop());
			System.out.println(st);
		}
	}

}
