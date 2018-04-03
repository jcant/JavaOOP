package com.gmail.gm.jcant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		int result = 0;

		try {
			result = Files.lines(Paths.get("numbers.txt"))
					.flatMap(n -> Arrays.stream(n.replaceAll("[^\\d]{1,}", " ").split(" ")).map(String::trim))
					.map(n -> Integer.parseInt(n))
					.max((n, m) -> n - m)
					.get();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("max number: " + result);

	}
}
