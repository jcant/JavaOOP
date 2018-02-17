package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter{
	private String[] extArr;
	
	public MyFileFilter(String...extArr) {
		super();
		this.extArr = extArr;
	}
	
	private boolean check(String ext) {
		for (String item : extArr) {
			if (item.equalsIgnoreCase(ext)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean accept(File pathname) {
		
		if (pathname.isDirectory()) {
			return true;
		}
		
		int pointerIndex = pathname.getName().lastIndexOf(".");
		if (pointerIndex == -1) {
			return false;
		}
		String ext = pathname.getName().substring(pointerIndex+1);
		
		return check(ext);
	}
}
