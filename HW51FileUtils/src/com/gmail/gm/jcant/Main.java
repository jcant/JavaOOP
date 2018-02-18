package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		File from = new File("/home/Graphics");
		File to = new File("/home/TMP/222");

		try {
			FileUtils.copy(from, to, "cdr", "jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
