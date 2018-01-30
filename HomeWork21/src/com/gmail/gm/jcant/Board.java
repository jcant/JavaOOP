package com.gmail.gm.jcant;

import java.util.Arrays;

public class Board {

	private Shape[] figures;

	public Board() {
		super();
		figures = new Shape[4];
	}

	public void printStatus() {
		System.out.println("*** Board status *** ");
		for (int i = 0; i < figures.length; i++) {
			System.out.print("[" + i + "] ");
			String info = "";
			if (figures[i] != null) {
				info = String.format(figures[i].getClass().getSimpleName() + " Per=%.2f Area=%.2f",
						figures[i].getPerimetr(), figures[i].getArea());
			} else {
				info = "NULL";
			}
			System.out.println(info);
		}
		System.out.println("--------------------");
		System.out.println(String.format("Summ Perimetr = %.2f", getSummPerimetr()));
		System.out.println(String.format("Summ Area     = %.2f", getSummArea()));
		System.out.println("*** ************ ***");
		System.out.println();
	}

	public double getSummPerimetr() {
		double result = 0;
		for (int i = 0; i < figures.length; i++) {
			if (figures[i] != null) {
				result += figures[i].getPerimetr();
			}
		}
		return result;
	}

	public double getSummArea() {
		double result = 0;
		for (int i = 0; i < figures.length; i++) {
			if (figures[i] != null) {
				result += figures[i].getArea();
			}
		}
		return result;
	}

	public void addToCell(int cellNum, Shape shape) {
		figures[cellNum] = shape;
	}

	public void clearBoard() {
		figures = new Shape[4];
	}

	public void clearBoardCell(int cellNum) {
		figures[cellNum] = null;
	}

	@Override
	public String toString() {
		return "Board [figures=" + Arrays.toString(figures) + "]";
	}

}
