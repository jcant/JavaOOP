package com.gmail.gm.jcant;

public class Board {

	private final int BOARDSIZE = 4;
	private Shape[] figures;

	public Board() {
		super();
		figures = new Shape[BOARDSIZE];
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

	public boolean addToCell(int cellNum, Shape shape) {
		if ((cellNum >= 0) && (cellNum < BOARDSIZE)) {
			figures[cellNum] = shape;
			return true;
		} else {
			return false;
		}
	}

	public boolean clearBoardCell(int cellNum) {
		if ((cellNum >= 0) && (cellNum < BOARDSIZE)) {
			figures[cellNum] = null;
			return true;
		} else {
			return false;
		}
	}

	public void clearBoard() {
		figures = new Shape[4];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("*** Board status *** " + System.lineSeparator());
		for (int i = 0; i < figures.length; i++) {
			sb.append("[" + i + "] ");
			String info = "";
			if (figures[i] != null) {
				info = String.format("%-10.10s Per=%-5.5s Area=%.2f", figures[i].getClass().getSimpleName(),
						figures[i].getPerimetr(), figures[i].getArea());
			} else {
				info = "NULL";
			}
			sb.append(info + System.lineSeparator());
		}
		sb.append("--------------------" + System.lineSeparator());
		sb.append(String.format("Summ Perimetr = %.2f", getSummPerimetr()) + System.lineSeparator());
		sb.append(String.format("Summ Area     = %.2f", getSummArea()) + System.lineSeparator());
		sb.append("*** ************ ***" + System.lineSeparator());
		return sb.toString();
	}

}
