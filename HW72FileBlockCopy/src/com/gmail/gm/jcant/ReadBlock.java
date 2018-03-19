package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBlock implements Runnable {

	private BlockCopyController controller;
	private File from;
	private byte[] buffer;
	private int buffSize = 1024 * 1024;

	public ReadBlock(BlockCopyController controller, File from) {
		super();
		this.controller = controller;
		this.from = from;
		this.buffer = new byte[buffSize];
	}

	private void fileCopy() throws IOException {

		int readByte = 0;

		try (FileInputStream fis = new FileInputStream(from)) {

			while ((readByte = fis.read(buffer)) > 0) {
				controller.setReadedInfo(buffer, readByte);
			}

		} catch (IOException e) {
			throw e;
		} finally {
			controller.setEnd(true);
		}

	}

	@Override
	public void run() {
		try {
			fileCopy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
