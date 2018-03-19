package com.gmail.gm.jcant;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		File from = new File("/home/Video/film.avi");
		File to = new File("/home/TMP/film.avi");

		FileUtils.fileCopy(from, to);

	}

}
