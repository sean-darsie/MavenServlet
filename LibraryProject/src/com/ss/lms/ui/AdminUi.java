/**
 * 
 */
package com.ss.lms.ui;

import java.util.Scanner;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.AdminService;

/**
 * @author seandarsie
 *
 */
public class AdminUi {
	Scanner in;
	AdminService adminService;

	public AdminUi(Scanner in) {
		this.in = in;
		adminService = new AdminService();
	}
	
	public void start()
	{
		promptLogin();
	}
	
	public void promptLogin()
	{
		System.out.println("Enter admin username and password");
		System.out.println("username: ");
		String input = in.nextLine();
		if (input.contentEquals("r") == true)
		{
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
			return;
		}
		if (input.contentEquals("q") == true)
			System.exit(0);
		if (input.contentEquals("admin") != true)
		{
			System.out.println("user: "+input+" does not exist.");
			promptLogin();
			return;
		}
		else
		{
			System.out.println("password: ");
			input = in.nextLine();
			if (input.contentEquals("password") != true)
			{
				System.out.println("incorrect password");
				promptLogin();
			}
			else
			{
				promptChoice();
			}
		}
	}
	
	public void promptChoice()
	{
		System.out.println("choose one of the following options");
		System.out.println("1) create");
		System.out.println("2) read");
		System.out.println("3) update");
		System.out.println("4) delete");
		System.out.println("r) return to main menu");
		System.out.println("q) quit");
		
		String input = in.nextLine();
		
		switch (input)
		{
		case"1":
			promptCreate();
			break;
		case"2":
			promptRead();
			break;
		case"3":
			promptUpdate();
			break;
		case"4":
			promptDelete();
			break;
		case"r":
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
			break;
		case"q":
			System.exit(0);
			break;
		default:
			System.out.println("you have not entered a corect option");
			promptChoice();
			break;
		}

	}

	private void promptCreate() 
	{
		System.out.println("choose a table to update");
		System.out.println("1)authors");
		System.out.println("2)books");
		System.out.println("3)publishers");
		System.out.println("4)library branch");
		System.out.println("5)genres");
		System.out.println("6)borrower");
		System.out.println("r)main menu");
		System.out.println("q)quit");
		
		String input = in.nextLine();
		
		switch (input)
		{
		case"1":
			createAuthor();
			
			break;
		case"2":
			createBook();
			break;
		case"3":
			createPub();
			break;
		case"4":
			createBranch();
			break;
		case"5":
			createGenre();
			break;
		case"6":
			createBorrower();
			break;
		case"r":
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
			break;
		case"q":
			System.exit(0);
			break;
		default:
			System.out.println("you have not entered a corect option");
			promptChoice();
			break;
		}
		LibraryInterface libraryInterface = new LibraryInterface(in);
		libraryInterface.start();		
	}

	private void createBorrower() {
		System.out.println("Write the name of the new borrower");
		String name = in.nextLine();
		System.out.println("Write the address of the new borrower");
		String address = in.nextLine();
		System.out.println("Write the phone number of the new borrower");
		String phone = in.nextLine();
		adminService.saveBorrower(new Borrower(null, name, address, phone));
	}

	private void createGenre() {
		// TODO Auto-generated method stub
		System.out.println("Write the name of the genre");
		String input = in.nextLine();
		adminService.saveGenre(new Genre(null, input));
		
	}

	private void createBranch() {
		// TODO Auto-generated method stub
		System.out.println("Write the name of the library branch");
		String input = in.nextLine();
		adminService.saveBranch(new Branch(null, input, input + "address"));
		// TODO Auto-generated method stub
	}

	private void createPub() {
		// TODO Auto-generated method stub
		System.out.println("Write the name of the publisher");
		String input = in.nextLine();
		adminService.savePublisher(new Publisher(null, input, input + " address", "(123)143-5422"));
		
	}

	private void createBook() {
		System.out.println("Write the title of the book");
		String input = in.nextLine();
		adminService.saveBook(new Book(null, input, 1, null));
		// TODO Auto-generated method stub
		
	}

	private void createAuthor() {
		System.out.println("Write the name of the author");
		String input = in.nextLine();
		adminService.saveAuthor(new Author(null, input));
		// TODO Auto-generated method stub
		
	}

	private void promptRead() {
		// TODO Auto-generated method stub
		System.out.println("choose a table to read from");
		System.out.println("1)authors");
		System.out.println("2)books");
		System.out.println("3)publishers");
		System.out.println("4)library branch");
		System.out.println("5)genres");
		System.out.println("6)borrower");
		System.out.println("r)main menu");
		System.out.println("q)quit");
		
		String input = in.nextLine();
		
		switch (input)
		{
		case"1":
			readAuthor();
			break;
		case"2":
			readBook();
			break;
		case"3":
			readPub();
			break;
		case"4":
			readBranch();
			break;
		case"5":
			readGenre();
			break;
		case"6":
			readBorrower();
			break;
		case"r":
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
			break;
		case"q":
			System.exit(0);
			break;
		default:
			System.out.println("you have not entered a correct option");
			promptChoice();
			break;
		}
		LibraryInterface libraryInterface = new LibraryInterface(in);
		libraryInterface.start();		
	}

		
	private void readBorrower() {
		// TODO Auto-generated method stub
		
	}

	private void readGenre() {
		// TODO Auto-generated method stub
		adminService.readGenres();
		
	}

	private void readBranch() {
		// TODO Auto-generated method stub
		adminService.readBranchs();
		
	}

	private void readPub() {
		// TODO Auto-generated method stub
		adminService.readPublishers();
		
	}

	private void readBook() {
		// TODO Auto-generated method stub
		adminService.readBooks();
	}

	private void readAuthor() {
		adminService.readAuthors();
	}

	private void promptUpdate() {
		// TODO Auto-generated method stub
		
	}

	private void promptDelete() {
		// TODO Auto-generated method stub
		
	}
	
}
