package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		File txt1 = new File("/home/jcant/TMP/text1.txt");
		File txt2 = new File("/home/jcant/TMP/text2.txt");
		File result = new File("/home/jcant/TMP/res.txt");

		try {
			FileUtils.textAndText(txt1, txt2, result);
			System.out.println("Everysing Ok!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
