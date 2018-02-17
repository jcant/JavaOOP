package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class FileUtils {

	public static void fileCopy(File from, File to) throws IOException {
		long fileSize = from.length() / 4;
		int buffSize = 0;
		if (fileSize > 1024 * 1024 *10) {
			buffSize = 1024 * 1024 *10;
		} else if (fileSize < 1024) {
			buffSize = 1024;
		} else {
			buffSize = (int) fileSize;
		}

		byte[] buffer = new byte[buffSize];
		int readByte = 0;
		long count = 0;
		try (FileInputStream fis = new FileInputStream(from); FileOutputStream fos = new FileOutputStream(to)) {

			while ((readByte = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, readByte);
				count += readByte;
				System.out.println("file progress: " + (100 * count / from.length()) + "%");
			}

		} catch (IOException e) {
			throw e;
		}
	}

	public static void copy(File from, File to) throws IOException {

		Date start = new Date();
		
		File[] files = fileSearch(from);

		for (int i = 0; i < files.length; i++) {

			System.out.println("progress: " + (100 * i / files.length) + "%");

			File fileTo = new File(to, files[i].getAbsolutePath().substring(from.getAbsolutePath().length(),
					files[i].getAbsolutePath().length()));

			File path = new File(fileTo.getAbsolutePath().substring(0,
					fileTo.getAbsolutePath().length() - fileTo.getName().length()));

			path.mkdirs();

			fileCopy(files[i], new File(to, files[i].getAbsolutePath().substring(from.getAbsolutePath().length(),
					files[i].getAbsolutePath().length())));
		}

		System.out.println("100%");
		
		Date end = new Date();
		
		System.out.println("Time in msec: "+(end.getTime() - start.getTime()));
	}

	public static File[] fileSearch(File startPoint) {

		File[] result = new File[0];

		if (startPoint.isFile()) {

			result = new File[] { startPoint };

			return result;

		} else {

			File[] files = startPoint.listFiles();

			for (File file : files) {

				File[] tmp = fileSearch(file);
				File[] newArr = Arrays.copyOf(result, result.length + tmp.length);
				System.arraycopy(tmp, 0, newArr, result.length, tmp.length);
				result = newArr;
			}

			return result;
		}

	}

	public static void printFileInfo(File file) {
		System.out.println(((file.isFile()) ? ("file") : ("<DIR>")) + "\t name: " + file.getAbsolutePath());
	}
}
