/**
 * 
 */
package com.ss.lms.ui;

import java.util.Scanner;

/**
 * @author seandarsie
 *
 */
public class AdminUi {
	Scanner in;

	public AdminUi(Scanner in) {
		this.in = in;
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
		if (input == "r")
		{
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
		}
		if (input == "q")
			System.exit(0);
		if (input != "admin")
		{
			System.out.println("user does not exist.");
			promptLogin();
			return;
		}
		else
		{
			System.out.println("password: ");
			input = in.nextLine();
			if (input != "password")
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

	private void promptCreate() {
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

		// TODO Auto-generated method stub
		
	}

	private void promptRead() {
		// TODO Auto-generated method stub
		
	}

	private void promptUpdate() {
		// TODO Auto-generated method stub
		
	}

	private void promptDelete() {
		// TODO Auto-generated method stub
		
	}
	
}
