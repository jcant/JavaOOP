package com.gmail.gm.jcant;

public class Rectangle extends Shape {

	private Point fromPoint;
	private Point toPoint;

	public Rectangle() {
		super();
	}

	public Rectangle(Point leftTop, Point rightBottom) {
		super();
		this.fromPoint = leftTop;
		this.toPoint = rightBottom;
	}

	@Override
	public double getPerimetr() {
		return (getWidth() + getHeight()) * 2;
	}

	@Override
	public double getArea() {
		return (getWidth() * getHeight());
	}

	private double getWidth() {
		return Math.abs(toPoint.getX() - fromPoint.getX());
	}

	private double getHeight() {
		return Math.abs(toPoint.getY() - fromPoint.getY());
	}

	public Point getLeftTop() {
		return fromPoint;
	}

	public void setLeftTop(Point leftTop) {
		this.fromPoint = leftTop;
	}

	public Point getRightBottom() {
		return toPoint;
	}

	public void setRightBottom(Point rightBottom) {
		this.toPoint = rightBottom;
	}

	@Override
	public String toString() {
		return "Rectangle [fromPoint=" + fromPoint + ", toPoint=" + toPoint + "]";
	}

}
