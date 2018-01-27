package com.gmail.gm.jcant;

public class Triangle {

	private Double sideA;
	private Double sideB;
	private Double sideC;

	public Triangle() {
		super();
		this.sideA = 0.0;
		this.sideB = 0.0;
		this.sideC = 0.0;
	}

	public Triangle(Double sideA, Double sideB, Double sideC) {
		super();

		if (isTriangleExist(sideA, sideB, sideC)) {
			this.sideA = sideA;
			this.sideB = sideB;
			this.sideC = sideC;
		} else {
			this.sideA = 0.0;
			this.sideB = 0.0;
			this.sideC = 0.0;

			System.err.println("Such triangle(" + sideA + ", " + sideB + ", " + sideC + ") does not exist!");
		}

	}

	public Double getSquare() {

		double p = (this.sideA + this.sideB + this.sideC) / 2.0;

		return Math.sqrt(p * (p - this.sideA) * (p - this.sideB) * (p - this.sideC));

	}

	private final boolean isTriangleExist(Double sA, Double sB, Double sC) {
		return (((sA + sB) > sC) && ((sA + sC) > sB) && (sB + sC) > sA);
	}

	public Double getSideA() {
		return sideA;
	}

	public void setSideA(Double sideA) {
		this.sideA = sideA;
	}

	public Double getSideB() {
		return sideB;
	}

	public void setSideB(Double sideB) {
		this.sideB = sideB;
	}

	public Double getSideC() {
		return sideC;
	}

	public void setSideC(Double sideC) {
		this.sideC = sideC;
	}

	@Override
	public String toString() {
		return "Triangle [sideA=" + sideA + ", sideB=" + sideB + ", sideC=" + sideC + "]";
	}

}
