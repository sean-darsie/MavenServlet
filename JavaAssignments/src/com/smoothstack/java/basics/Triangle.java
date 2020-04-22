package com.smoothstack.java.basics;

// Assignment 3 day 2
public class Triangle implements Shape {
	
	int base;
	int height;
	
	public Triangle(int base, int height)
	{
		this.base = base;
		this.height = height;
	}
	
	@Override
	public void calculateArea() {
		float area = base * height / 2;
		System.out.println("the area of this triangle is "+area);

	}

	@Override
	public void display() {
		System.out.println("the area of a triangle is calcluated by multiplying the base by the height and dividing by 2");
		System.out.println("this triangle has a base of "+base+" and a height of "+height+".");

	}

}
