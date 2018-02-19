package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		File from = new File("/home/jcant/JC/Graphics");
		File to = new File("/home/jcant/TMP/222");
		long size = 0;
		long sizeFiltered = 0;

		System.out.println("Copying png,jpg,bmp,ico files from " + from + " to " + to);

		try {
			FileUtils.copy(from, to, new MyFileFilter("png", "jpg", "bmp", "ico"));
			size = FileUtils.getSize(to);
			sizeFiltered = FileUtils.getSize(to, new MyFileFilter("ico"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Full size of " + to + " is " + size + " bytes");
		System.out.println("ICO files in " + to + " is " + sizeFiltered + " bytes");
		System.out.println("Deleting ICO files in " + to);

		try {
			FileUtils.delete(to, new MyFileFilter("ico"));
			size = FileUtils.getSize(to);
			sizeFiltered = FileUtils.getSize(to, new MyFileFilter("ico"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Full size of " + to + "after delete ICO is " + size + " bytes");
		System.out.println("ICO files in " + to + " is " + sizeFiltered + " bytes");
	}

}
