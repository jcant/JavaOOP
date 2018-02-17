package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		File from = new File("/home/jcant/JC/Graphics/моя");
		File to = new File("/home/jcant/TMP/222");

		System.out.println("Copying png,jpg,bmp,ico files from " + from + " to " + to);

		try {
			FileUtils.copy(from, to, new MyFileFilter("png", "jpg", "bmp", "ico"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		File delFile = new File("/home/jcant/TMP/222");
		System.out.println("Full size of " + delFile + " is " + FileUtils.getSize(delFile) + " bytes");
		System.out.println(
				"ICO files in " + delFile + " is " + FileUtils.getSize(delFile, new MyFileFilter("ico")) + " bytes");

		System.out.println("Deleting ICO files in " + delFile);

		try {
			FileUtils.delete(delFile, new MyFileFilter("ico"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Full size of " + delFile + "after delete ICO is " + FileUtils.getSize(delFile) + " bytes");
		System.out.println(
				"ICO files in " + delFile + " is " + FileUtils.getSize(delFile, new MyFileFilter("ico")) + " bytes");

	}

}
