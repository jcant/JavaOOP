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
		return pB.getDistanceTo(pA);
	}

	private double getSide2() {
		return pC.getDistanceTo(pB);
	}

	private double getSide3() {
		return pA.getDistanceTo(pC);
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
