package com.gmail.gm.jcant;

import java.io.File;

public class FileUtils {

	public static void fileCopy(File from, File to) {
		BlockCopyController bcc = new BlockCopyController();

		ReadBlock read = new ReadBlock(bcc, from);
		WriteBlock write = new WriteBlock(bcc, to);
		ProgressPrint prn = new ProgressPrint(bcc, from);

		Thread readThr = new Thread(read);
		Thread writeThr = new Thread(write);
		Thread printThr = new Thread(prn);

		readThr.start();
		writeThr.start();
		printThr.start();
	}

}
