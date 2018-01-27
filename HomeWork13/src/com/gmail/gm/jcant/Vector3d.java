package com.gmail.gm.jcant;

public class Vector3d {

	private double x;
	private double y;
	private double z;

	public Vector3d() {
		super();

		x = 0;
		y = 0;
		z = 0;
	}

	public Vector3d(double x, double y, double z) {
		super();

		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3d(Vector3d v) {
		super();

		setVector(v);
	}
	
	public void setVector(Vector3d v) {

		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();
	}

	public Vector3d addVector(double x, double y, double z) {

		this.x += x;
		this.y += y;
		this.z += z;

		return this;
	}

	public Vector3d addVector(Vector3d v) {

		return addVector(v.getX(), v.getY(), v.getZ());
	}

	public double scalarMultiplication(double x, double y, double z) {

		return (this.x * x + this.y * y + this.z * z);
	}

	public double scalarMultiplication(Vector3d v) {

		return scalarMultiplication(v.getX(), v.getY(), v.getZ());
	}

	public Vector3d vectorMultiplication(double x, double y, double z) {

		Vector3d result = new Vector3d();

		result.setX(this.y * z - this.z * y);
		result.setY(this.x * z - this.z * x);
		result.setZ(this.x * y - this.y * x);

		this.setVector(result);

		return result;
	}

	public Vector3d vectorMultiplication(Vector3d v) {

		return vectorMultiplication(v.getX(), v.getY(), v.getZ());
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

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Vector3d [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
