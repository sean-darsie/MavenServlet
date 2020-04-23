package com.smoothstack.java.basics;

//Assignment 1 Day 2
public class AddingNumbers {

	public static void main(String[] args) {
		if (args.length < 3)
		{
			System.out.println("please input at least 3 numbers");
			System.exit(0);
		}
		int sum = 0;
		for (int i = 0; i < args.length; i++) 
		{
			int current = Integer.parseInt(args[i]);
			sum += current;
		}
		
		System.out.println("The sum of the numbers input is "+sum);
			
	}

}
