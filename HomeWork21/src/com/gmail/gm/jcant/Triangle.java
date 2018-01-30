package com.gmail.gm.jcant;

public class Triangle extends Shape {

	private Point pA;
	private Point pB;
	private Point pC;

	public Triangle() {
		super();
	}

	public Triangle(Point pA, Point pB, Point pC) {
		super();
		this.pA = pA;
		this.pB = pB;
		this.pC = pC;
	}

	@Override
	public double getPerimetr() {

		return getSide1() + getSide2() + getSide3();
	}

	@Override
	public double getArea() {
		double halfP = getPerimetr() / 2;
		double area = Math.sqrt(halfP * (halfP - getSide1()) * (halfP - getSide2()) * (halfP - getSide3()));

		return area;
	}

	private double getSide1() {
		return Math.sqrt(Math.pow((pB.getX() - pA.getX()), 2) + Math.pow((pB.getY() - pA.getY()), 2));
	}

	private double getSide2() {
		return Math.sqrt(Math.pow((pC.getX() - pB.getX()), 2) + Math.pow((pC.getY() - pB.getY()), 2));
	}

	private double getSide3() {
		return Math.sqrt(Math.pow((pA.getX() - pC.getX()), 2) + Math.pow((pA.getY() - pC.getY()), 2));
	}

	public Point getpA() {
		return pA;
	}

	public void setpA(Point pA) {
		this.pA = pA;
	}

	public Point getpB() {
		return pB;
	}

	public void setpB(Point pB) {
		this.pB = pB;
	}

	public Point getpC() {
		return pC;
	}

	public void setpC(Point pC) {
		this.pC = pC;
	}

	@Override
	public String toString() {
		return "Triangle [pA=" + pA + ", pB=" + pB + ", pC=" + pC + "](side1=" + getSide1() + ", side2=" + getSide2()
				+ ", side3=" + getSide3() + ")";
	}

}
