package com.gmail.gm.jcant;

public class BlockCopyController {

	private int readByteCount;
	private long progress = 0;
	private byte[] buffer;

	private boolean read = true;
	private boolean canGetProgress = false;
	private boolean end = false;

	public BlockCopyController() {
		super();
	}

	public int getReadByteCount() {
		return readByteCount;
	}

	public synchronized void setReadedInfo(byte[] buffer, int readByteCount) {

		while (!read) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.buffer = buffer.clone();
		this.readByteCount = readByteCount;
		this.progress += readByteCount;

		read = false;
		notifyAll();
	}

	public synchronized byte[] getBuffer() {

		while (read) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		read = true;
		canGetProgress = true;
		notifyAll();

		return buffer.clone();
	}

	public synchronized long getProgress() {
		while (!canGetProgress) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		canGetProgress = false;

		return progress;
	}

	public synchronized void setEnd(boolean end) {
		this.end = end;
		read = false;
		canGetProgress = true;
		notifyAll();
	}

	public boolean isEnd() {
		return end;
	}

}
