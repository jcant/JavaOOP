package com.gmail.gm.jcant;

public class Ellipse extends Shape {

	private Point centre;
	private double radius1;
	private double radius2;

	public Ellipse() {
		super();
	}

	public Ellipse(Point centre, double radius1, double radius2) {
		super();
		this.centre = centre;
		this.radius1 = radius1;
		this.radius2 = radius2;
	}

	@Override
	public double getPerimetr() {
		// an approximate algorithm for calculating the Perimeter of ellipse
		// when radius1==radius2 (a circle) the method gives exact value
		return 2 * Math.PI * Math.sqrt((radius1 * radius1 + radius2 * radius2) / 2);
	}

	@Override
	public double getArea() {

		return Math.PI * radius1 * radius2;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public double getRadius1() {
		return radius1;
	}

	public void setRadius1(double radius1) {
		this.radius1 = radius1;
	}

	public double getRadius2() {
		return radius2;
	}

	public void setRadius2(double radius2) {
		this.radius2 = radius2;
	}

	@Override
	public String toString() {
		return "Ellipse [centre=" + centre + ", radius1=" + radius1 + ", radius2=" + radius2 + "]";
	}

}
