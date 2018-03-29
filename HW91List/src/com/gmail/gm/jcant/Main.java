package com.gmail.gm.jcant;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

	public static void main(String[] args) {
		listOperations();
	}

	public static void listOperations() {

		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < 1000; i += 100) {
			list.add(i);
		}

		System.out.println(list);

		ListIterator<Integer> lit = list.listIterator();

		lit.next();
		lit.remove();
		lit.next();
		lit.remove();

		while (lit.hasNext()) {
			lit.next();
		}

		lit.remove();

		System.out.println(list);
	}

}
