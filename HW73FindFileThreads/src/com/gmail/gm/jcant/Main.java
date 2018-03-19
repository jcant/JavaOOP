package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {

		System.out.println("Find files by part of name:");
		String target = "jpg";
		String folder = "/home/Pictures";
		FileFinder.findFile(target, folder, false);

		System.out.println("Find files by name:");
		target = "picture.jpg";
		folder = "/home/Pictures";
		FileFinder.findFile(target, folder);

	}

}
