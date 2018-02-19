package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileUtils {

	public static void copy(File from, File to) throws IOException {
		copy(from, to, f -> true);
	}

	public static void copy(File from, File to, FileFilter ff) throws IOException {

		Date start = new Date();

		fileSearch(from, f -> {

			if (f.isFile()) {
				File fileTo = new File(to,
						f.getAbsolutePath().substring(from.getAbsolutePath().length(), f.getAbsolutePath().length()));

				File path = new File(fileTo.getAbsolutePath().substring(0,
						fileTo.getAbsolutePath().length() - fileTo.getName().length()));

				path.mkdirs();

				try {

					fileCopy(f, fileTo);

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}, ff);

		Date end = new Date();

		System.out.println("Copying files end. Time in msec: " + (end.getTime() - start.getTime()));
	}

	public static void delete(File file) throws IOException {
		delete(file, f -> true);
	}

	public static void delete(File file, FileFilter ff) throws IOException {

		Date start = new Date();

		fileSearch(file, f -> f.delete(), ff);

		Date end = new Date();

		System.out.println("Deleting files end. Time in msec: " + (end.getTime() - start.getTime()));
	}

	public static long getSize(File file) throws IOException {
		return getSize(file, f -> true);
	}

	public static long getSize(File file, FileFilter ff) throws IOException {
		long[] result = new long[1];

		fileSearch(file, f -> {
			if (f.isFile()) {
				result[0] = result[0] + f.length();
			}
		}, ff);
		return result[0];
	}

	// recursion method to run through all files and directories
	private static void fileSearch(File startPoint, FileOperation fo, FileFilter ff) throws IOException {

		if (!startPoint.exists()) {
			throw new IOException();
		}

		if (startPoint.isFile()) {

			// printFileInfo(startPoint);
			fo.fileOperation(startPoint);
			return;

		} else {

			File[] files = startPoint.listFiles(ff);

			for (File file : files) {

				fileSearch(file, fo, ff);
			}

			fo.fileOperation(startPoint);
			return;
		}

	}

	private static void fileCopy(File from, File to) throws IOException {
		long fileSize = from.length() / 4;
		int buffSize = 0;
		if (fileSize > 1024 * 1024 * 10) {
			buffSize = 1024 * 1024 * 10;
		} else if (fileSize < 1024) {
			buffSize = 1024;
		} else {
			buffSize = (int) fileSize;
		}

		byte[] buffer = new byte[buffSize];
		int readByte = 0;
		// long count = 0;
		try (FileInputStream fis = new FileInputStream(from); FileOutputStream fos = new FileOutputStream(to)) {

			while ((readByte = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, readByte);
				// count += readByte;
				// System.out.println("file progress: " + (100 * count / from.length()) + "%");
			}

		} catch (IOException e) {
			throw e;
		}
	}

	private static void printFileInfo(File file) {
		System.out.println(((file.isFile()) ? ("file") : ("<DIR>")) + "\t name: " + file.getAbsolutePath());
	}
}
