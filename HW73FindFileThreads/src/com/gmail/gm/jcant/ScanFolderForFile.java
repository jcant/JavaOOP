package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class ScanFolderForFile implements Callable<List<File>> {

	private File folder;
	private String target;
	private boolean strict;

	private List<File> result = new LinkedList<File>();

	public ScanFolderForFile(String target, File folder, boolean strict) {
		super();
		this.folder = folder;
		this.target = target;
		this.strict = strict;
	}

	public ScanFolderForFile(String target, File folder) {
		super();
		this.folder = folder;
		this.target = target;
		this.strict = true;
	}

	@Override
	public List<File> call() throws Exception {
		scan();
		return result;
	}

	private void scan() throws FileNotFoundException {
		if (!folder.exists() || folder.isFile()) {
			throw new FileNotFoundException();
		}

		File[] arr = folder.listFiles();
		for (File file : arr) {
			if (file.isFile()) {
				analise(file);
			}
		}
	}

	private void analise(File file) {
		boolean eq = false;
		if (strict) {
			eq = target.equals(file.getName());
		} else {
			eq = (file.getName().toLowerCase().indexOf(target.toLowerCase()) != -1);
		}

		if (eq == true) {
			result.add(file);
		}
	}

}
