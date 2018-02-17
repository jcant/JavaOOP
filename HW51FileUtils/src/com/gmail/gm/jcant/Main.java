package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

//		File from = new File("/home/jcant/JC/Graphics");
//		File to = new File("/home/jcant/TMP/222");
//
//		try {
//			FileUtils.copy(from, to);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		File delFile = new File("/home/jcant/TMP/222");
//		try {
//			FileUtils.delete(delFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//get size:
		File file = new File("/home/jcant/JC/Graphics");
		System.out.println("Size of "+file+ " is "+ FileUtils.getSize(file)+" bytes");

	}

}
