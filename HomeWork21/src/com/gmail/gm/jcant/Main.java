package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {

		Triangle tr1 = new Triangle(new Point(1, 1), new Point(1, 3), new Point(4, 1));
		Ellipse el1 = new Ellipse(new Point(0, 0), 3, 5);
		Rectangle rec1 = new Rectangle(new Point(-3, -3), new Point(3, 3));
		Rectangle rec2 = new Rectangle(new Point(-2, 0), new Point(0, 2));
		Board board = new Board();

		board.addToCell(0, tr1);
		board.addToCell(2, el1);
		board.addToCell(3, rec1);
		System.out.println(board);
		System.out.println(String.format("Perimetr summ: %.2f;  Area summ: %.2f;", board.getSummPerimetr(), board.getSummArea()));
		System.out.println();

		board.addToCell(1, rec2);
		System.out.println(board);
		System.out.println(String.format("Perimetr summ: %.2f;  Area summ: %.2f;", board.getSummPerimetr(), board.getSummArea()));
		System.out.println();

		board.clearBoardCell(1);
		board.clearBoardCell(2);
		System.out.println(board);
		System.out.println(String.format("Perimetr summ: %.2f;  Area summ: %.2f;", board.getSummPerimetr(), board.getSummArea()));
		System.out.println();

		board.clearBoard();
		System.out.println(board);
		System.out.println(String.format("Perimetr summ: %.2f;  Area summ: %.2f;", board.getSummPerimetr(), board.getSummArea()));
		System.out.println();
	}

}
