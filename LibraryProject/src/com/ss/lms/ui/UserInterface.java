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
		System.out.println("Welcome to the GCIT library management system. Which category of user are you?");
	}
	public void mainMenuOptions()
	{
		System.out.println("Choose an option from the below. Type the key and press 'enter'");
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
		System.out.println("q) quit");
	}
	
	public void chooseLibrarianOptions()
	{
		System.out.println("Provide your library branch");
		System.out.println("r) return");
	}
	
	public void librarianOptions()
	{
		System.out.println("1) Update your branch details");
		System.out.println("2) Add book copies to your branch");
		System.out.println("r) return to main menu");
	}
	
	public void chooseAdminOptions()
	{
		System.out.println("Provide your library branch");
		System.out.println("r) return");
	}
	public void borrowerLoginPrompt()
	{
		System.out.println("Enter your library card number or 'r' to return");
	}
	
	public void borrowerOptions()
	{
		System.out.println("1) Check out a book");
		System.out.println("2) Return a book");
		System.out.println("r) Main menu");
	}
	
	
	public void updateBranchDetails(int branchId, String branchName)
	{
		System.out.print("You have chosen to update the Branch with Branch Id: "+branchId+" and Branch Name: "+branchName+". Enter ‘r’ at any prompt to cancel operation.\n");
	}
	
	
	
	
	
	
	
	
	
	
	
	// ------------------- OLD CODE ----------------------------//
	
	
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
