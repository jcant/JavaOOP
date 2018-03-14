package com.gmail.gm.jcant;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		File f1 = new File("/home/tmp/111/");
		
		Thread monitor = new Thread(new FolderMonitor(f1));
		
		System.out.println("Start monitoring folder: "+f1);
		
		monitor.start();
	}

}
