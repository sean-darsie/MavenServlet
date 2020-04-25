/**
 * 
 */
package com.ss.lms.ui;

import java.util.Scanner;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;

/**
 * @author seandarsie
 *
 */
public class UserInterface {
Scanner in;
	
	public UserInterface(Scanner in)
	{
		this.in = in;
	}
	
	public void welcomeMessage()
	{
		System.out.println("Welcome to the Smoothstack library!");
	}
	public void mainMenuOptions()
	{
		System.out.println("Choose an option from the below. Type the key and press 'enter'");
		System.out.println("1) Create");
		System.out.println("2) Read");
		System.out.println("3) Update");
		System.out.println("4) Delete");
		System.out.println("q) quit");
	}
	
	public void chooseDatabase()
	{
		System.out.println("1) Publishers");
		System.out.println("2) Authors");
		System.out.println("3) Books");
		System.out.println("r) return");
	}
	
	public Publisher getPublisherInfo()
	{
		// get id, name, and address
        Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the publisher");
        String name = in.nextLine();
		System.out.println("Enter the address of the publisher");
        String address = in.nextLine();
		System.out.println("Enter the phone of the publisher");
        String phone = in.nextLine();
        return new Publisher(1,name,address,phone);
		
	}
	
	public Author getAuthorInfo()
	{
		// get id, name
        Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the Author");
        String name = in.nextLine();
        return new Author(1,name);
	}

	public Book getBookInfo()
	{
		// get id, name, author, publisher
        Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the book");
        String name = in.nextLine();
		System.out.println("Enter the author of the book");
        Integer pubId = Integer.parseInt(in.nextLine());
        return new Book(1,name,pubId,null);
	}
}
