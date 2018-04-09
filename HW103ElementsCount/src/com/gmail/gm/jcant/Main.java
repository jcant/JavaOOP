package com.gmail.gm.jcant;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		Map<Item, Integer> itemsCount = count(getItemArray(10000, 3));
		printMap(itemsCount);

		System.out.println();

		Map<Integer, Integer> integerCount = count(getIntegerArray(10000));
		printMap(integerCount);

	}

	public static <K> Map<K, Integer> count(K[] arr) {
		Map<K, Integer> map = new HashMap<>();

		for (K element : arr) {
			Integer num = map.get(element);
			if (num != null) {
				map.put(element, num + 1);
			} else {
				map.put(element, 1);
			}
		}

		return map;
	}

	public static Integer[] getIntegerArray(int size) {
		Integer[] array = new Integer[size];
		Random rd = new Random();

		for (int i = 0; i < array.length; i++) {
			array[i] = new Integer(rd.nextInt(10));
		}

		return array;
	}

	public static Item[] getItemArray(int size, int stringLenght) {
		Item[] array = new Item[size];

		for (int i = 0; i < array.length; i++) {
			array[i] = new Item(getString(stringLenght));
		}

		return array;
	}

	private static String getString(int size) {
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();

		for (int i = 0; i < size; i++) {
			int code = ('A' + rd.nextInt('D' - 'A'));
			sb.append((char) code);
		}

		return sb.toString();
	}

	private static <K, V> void printMap(Map<K, V> map) {
		Set<K> keySet = map.keySet();

		for (K key : keySet) {
			System.out.println(key + " -> " + map.get(key));
		}
	}

}
