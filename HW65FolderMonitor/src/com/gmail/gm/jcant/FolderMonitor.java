package com.gmail.gm.jcant;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class FolderMonitor implements Runnable {

	private File target;
	private File[] content;

	public FolderMonitor(File target) {
		super();
		this.target = target;
		fillContent();
	}

	private final void fillContent() {
		if (!target.exists()) {
			throw new IllegalArgumentException();
		}
		content = target.listFiles();
	}

	private void compare() {
		File[] current = target.listFiles();
		compareArrays(content, current);

		content = current;
	}

	private void compareArrays(File[] was, File[] be) {
		List<File> oldList = new LinkedList<File>(Arrays.asList(was));
		List<File> newList = new LinkedList<File>(Arrays.asList(be));

		ListIterator<File> oldIt = oldList.listIterator();
		ListIterator<File> newIt = newList.listIterator();

		while (oldIt.hasNext()) {
			File oldFile = oldIt.next();
			newIt = newList.listIterator();
			while (newIt.hasNext()) {
				File newFile = newIt.next();

				if (newFile.equals(oldFile)) {
					oldList.remove(oldFile);
					newList.remove(newFile);
					oldIt = oldList.listIterator();
					newIt = newList.listIterator();
					break;
				}
			}
		}

		if (!oldList.isEmpty()) {
			for (File file : oldList) {
				System.out.println("Deleted file: " + file);
			}
		}

		if (!newList.isEmpty()) {
			for (File file : newList) {
				System.out.println("New file: " + file);
			}
		}
	}

	@Override
	public void run() {
		Thread th = Thread.currentThread();

		while (!th.isInterrupted()) {
			
			compare();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
