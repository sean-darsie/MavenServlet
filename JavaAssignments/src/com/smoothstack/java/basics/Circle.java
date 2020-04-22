package com.smoothstack.java.basics;

// Assignment 3 day 2
public class Circle implements Shape {

	int radius;
	
	public Circle(int radius)
	{
		this.radius = radius;
	}
	
	@Override
	public void calculateArea() {
		double area = Math.PI * Math.pow(radius, 2);
		System.out.printf("The area of this circle is %.2f\n",area);

	}

	@Override
	public void display() {
		System.out.println("the area of this circle is calculated by taking the radius: "+radius+", squared and multiplying it by PI");
	}

}
