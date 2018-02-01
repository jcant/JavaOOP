package com.gmail.gm.jcant;

public class Point {

	private double x;
	private double y;

	public Point() {
		super();
	}

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getDistanceTo(Point p) {
		return Math.sqrt(Math.pow((p.getX() - x), 2) + Math.pow((p.getY() - y), 2));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}
