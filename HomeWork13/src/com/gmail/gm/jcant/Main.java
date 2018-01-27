package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {	
		Vector3d v1 = new Vector3d();
		Vector3d v2 = new Vector3d(1.5, 2.8, 9.9);
		Vector3d v3 = new Vector3d(v2);
		
		v1.setX(-2.2);
		v1.setY(4.5);
		v1.setZ(5.5);
		
		System.out.println("V1: "+v1);
		System.out.println("V2: "+v2);
		System.out.println("V3: "+v3);
		
		v1.addVector(4.44, 0, -5.5);
		System.out.println("*V1: "+v1);
		v1.vectorMultiplication(2.4, -3.45, 0);
		System.out.println("*V1: "+v1);
		
		v2.addVector(0, 0, -9.9);
		System.out.println("*V2: "+v2);
		v3.setVector(v2);
		System.out.println("*V3: "+v3);
		v2.setVector(new Vector3d(-1 * v2.getY(), v2.getX(), 0));
		System.out.println("*V2: "+v2);
		
		System.out.println("Scalar multiplication of vectors v2 "+v2+" and v3 "+v3); 
		System.out.println(v2.scalarMultiplication(v3) + " - null, because they are orthogonal");
	}

}
