package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileFinder {

	private ExecutorService service;
	private int workers = 10;
	private File folder;
	private String target;
	private boolean strict;
	private List<Future<List<File>>> threadsResult = new LinkedList<>();
	private List<File> foundFiles = new LinkedList<>();

	private FileFinder(String target, File folder, boolean strict) {
		super();
		this.target = target;
		this.folder = folder;
		this.strict = strict;
		service = Executors.newFixedThreadPool(workers);
	}

	private void fileFinderWork() {
		try {
			processFolder(folder);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (Future<List<File>> future : threadsResult) {
			try {
				foundFiles.addAll(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		service.shutdown();
	}

	private void printFoundFiles() {
		for (File file : foundFiles) {
			System.out.println("File: " + file);
		}
	}

	private void processFolder(File startPoint) throws FileNotFoundException {

		if (!startPoint.exists()) {
			throw new FileNotFoundException();
		}

		if (startPoint.isDirectory()) {
			File[] files = startPoint.listFiles();

			for (File file : files) {
				processFolder(file);
			}

			threadsResult.add(service.submit(new ScanFolderForFile(target, startPoint, strict)));
		}
	}

	public static void findFile(String fname, String where) {
		findFile(fname, where, true);
	}

	public static void findFile(String fname, String where, boolean strict) {
		FileFinder fu = new FileFinder(fname, new File(where), strict);
		fu.fileFinderWork();
		fu.printFoundFiles();
	}

}
