package com.smoothstack.java.basics;

// Assignment 3 day 2
public class Rectangle implements Shape {
	
	int width;
	int height;
	
	public Rectangle(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void calculateArea() {
		int area = width * height;
		System.out.println("The area of this rectangle is: "+area);
	}

	@Override
	public void display() {
		System.out.println("This rectangle has a width of "+width+" and a height of "+height);
		System.out.println("the area is calculated by multiplying the width by the height");
	}

}
