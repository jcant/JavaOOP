package com.gmail.gm.jcant;

import java.io.File;

public class ProgressPrint implements Runnable {

	private BlockCopyController controller;
	private File from;

	public ProgressPrint(BlockCopyController controller, File from) {
		super();
		this.controller = controller;
		this.from = from;
	}

	@Override
	public void run() {
		while (!controller.isEnd()) {
			long percents = (controller.getProgress() * 100) / from.length();
			System.out.println("Progress: " +percents + "%");
		}

	}

}
