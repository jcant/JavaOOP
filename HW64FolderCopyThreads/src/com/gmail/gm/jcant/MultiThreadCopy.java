package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MultiThreadCopy implements Restart {

	private List<SingleFileCopy> list = new LinkedList<SingleFileCopy>();
	private File[] files;
	private File from;
	private File to;
	private int workers;
	private int index = 0;

	private long timeStart;
	private long timeFinish;

	private MultiThreadCopy(File from, File to, int workers) {
		this.from = from;
		this.to = to;
		this.workers = workers;

		try {
			this.files = fileSearch(from);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		timeStart = System.currentTimeMillis();
		fillList();
	}

	@Override
	public void restart(SingleFileCopy sfc) {
		list.remove(sfc);

		fillList();

		int filesLeft = (files.length - index + list.size());
		System.out.println("Files left: " + filesLeft);

		if (filesLeft == 0) {
			timeFinish = System.currentTimeMillis();
			System.out.println("Time: " + (timeFinish - timeStart) + " ms");
		}
	}

	private void fillList() {
		while (list.size() < workers) {
			if (index >= files.length) {
				break;
			}
			list.add(new SingleFileCopy(files[index], getFileTo(files[index]), this));
			index += 1;
		}
	}

	private File getFileTo(File fileFrom) {
		String sss = fileFrom.getAbsolutePath().substring(from.getAbsolutePath().length(),
				fileFrom.getAbsolutePath().length());
		File fileTo = new File(to, sss);
		return fileTo;
	}

	private File[] fileSearch(File startPoint) throws IOException {

		if (!startPoint.exists()) {
			throw new IOException();
		}

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

	public static void copy(File from, File to, int workers) {

		MultiThreadCopy task = new MultiThreadCopy(from, to, workers);

	}

}
