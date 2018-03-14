package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SingleFileCopy implements Runnable {
	private File from;
	private File to;
	private Thread thr;
	private Restart link;

	public SingleFileCopy(File from, File to, Restart link) {
		super();
		this.from = from;
		this.to = to;
		this.link = link;
		thr = new Thread(this);
		thr.start();
	}

	@Override
	public void run() {

		try {
			fileCopy();
		} catch (IOException e) {
			e.printStackTrace();
		}

		link.restart(this);
	}

	private void fileCopy() throws IOException {
		createToPath();
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
		try (FileInputStream fis = new FileInputStream(from); FileOutputStream fos = new FileOutputStream(to)) {
			while ((readByte = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, readByte);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	private void createToPath() {
		File path = new File(to.getAbsolutePath().substring(0, to.getAbsolutePath().length() - to.getName().length()));
		path.mkdirs();
	}

	public Thread getThread() {
		return thr;
	}

}
