package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		File from = new File("/home/jcant/JC/Graphics/моя");
		File to = new File("/home/jcant/TMP/222");

		try {
			FileUtils.copy(from, to, "cdr", "jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
