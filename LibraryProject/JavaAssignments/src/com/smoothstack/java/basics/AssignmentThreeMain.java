package com.smoothstack.java.basics;

// Demonstration of the three classes that inherit from the shape interface
public class AssignmentThreeMain {

	public static void main(String[] args) {
		Rectangle rect = new Rectangle(5,10);
		Circle circ = new Circle(2);
		Triangle tri = new Triangle(6,9);
		
		investigateShape(rect);
		investigateShape(circ);
		investigateShape(tri);
	}
	
	public static void investigateShape(Shape shape)
	{
		shape.display();
		shape.calculateArea();
	}

}
