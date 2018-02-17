package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {


		 File from = new File("/home/jcant/Видео/Танцы");
		 File to = new File("/media/jcant/A00012170011F54E/TMP");
		
		 try {
		 FileUtils.copy(from, to);
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }

	}

}
