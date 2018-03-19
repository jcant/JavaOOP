package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBlock implements Runnable {

	private BlockCopyController controller;
	private File to;

	public WriteBlock(BlockCopyController controller, File to) {
		super();
		this.controller = controller;
		this.to = to;
	}

	private void fileCopy() throws IOException {

		try (FileOutputStream fos = new FileOutputStream(to)) {

			while (!controller.isEnd()) {
				fos.write(controller.getBuffer(), 0, controller.getReadByteCount());
			}

			// last buffer after change 'controller.end' to 'true'
			fos.write(controller.getBuffer(), 0, controller.getReadByteCount());

		} catch (IOException e) {
			controller.setEnd(true);
			throw e;
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
