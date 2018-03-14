package com.gmail.gm.jcant;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		File from = new File("/home/111/");
		File to = new File("/home/TMP/111/");

		MultiThreadCopy.copy(from, to, 10);

	}

}
