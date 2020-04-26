package com.ss.lms.ui;

import java.util.Scanner;

public class LibraryInterface {

	Scanner in;
	LibrarianUi librarianUi;
	AdminUi adminUi;
	BorrowerUi borrowerUi;
	
	public LibraryInterface(Scanner in) {
		this.in = in;
		librarianUi = new LibrarianUi(in);
		adminUi = new AdminUi(in);
		borrowerUi = new BorrowerUi(in);
	}

	
	
	public void start()
	{
		System.out.println("Choose an option from the below. Type the key and press 'enter'");
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
		System.out.println("q) quit");
		chooseOption();
	}
	
	public void chooseOption()
	{
		String input = in.nextLine();
		// based on input we decide which UI we're going to show the user. 
		switch (input)
		{
		case "1":
			librarianUi.start();
			break;
		case "2":
			adminUi.start();
			break;
		case "3":
			borrowerUi.start();
			break;
		case "q":
			break;
		default:
			break;
		}
	}
	

}
