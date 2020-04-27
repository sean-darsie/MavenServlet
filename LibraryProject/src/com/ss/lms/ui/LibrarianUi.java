/**
 * 
 */
package com.ss.lms.ui;

import java.util.List;
import java.util.Scanner;

import com.ss.lms.entity.Branch;
import com.ss.lms.service.LibrarianService;

/**
 * @author seandarsie
 *
 */
public class LibrarianUi {
	Scanner in;
	LibrarianService librarianService = new LibrarianService();

	public LibrarianUi(Scanner in) {
		this.in = in;
	}
	public void start() 
	{
		// pick your branch
		promptBranchChoice();
	}
	
	public void promptBranchChoice()
	{
		// basically get all branches from the branchDao and show them to the user 
		System.out.println("Choose your library branch from the below by branch id.");
		List<Branch> branches = null;
		branches = librarianService.ListAllBranches();
		for (Branch b: branches)
		{
			System.out.println("BranchId: "+b.getBranchId()+" Branch Name: "+b.getName());
		}
		String input = in.nextLine();
		
		if (input == "r")
		{
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
		} else if (input == "q")
			System.exit(0);
		else
		{
			Integer branch = Integer.parseInt(input);
			if (branch > branches.size())
			{
				System.out.println("That branch does not exist");
				LibraryInterface libraryInterface = new LibraryInterface(in);
				libraryInterface.start();
			}
			else
			{
				promptChangeDetailsOrAddBooks(branch);
			}
		}
	}
	
	public void promptChangeDetailsOrAddBooks(int branch)
	{
		System.out.println("Please choose one of the following");
		System.out.println("1) change library details");
		System.out.println("2) add books to the branch");
		System.out.println("r) return to main menu");
		System.out.println("q) quit the library management system");
		
		String input = in.nextLine();
		switch(input)
		{
		case"1":
			changeLibraryDetails(branch);
			break;
		case"2":
			addBooks(branch);
			break;
		case"r":
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
			break;
		case"q":
			System.exit(0);
			break;
		}
	}
	
	public void changeLibraryDetails(int branch)
	{
		System.out.println("Input new library name");
		String name = in.nextLine();
		System.out.println("Input new branch address");
		String address = in.nextLine();
		librarianService.changeLibraryDetails(branch,  name, address);
		System.out.println("Library info updated. returning to main menu");
		LibraryInterface libraryInterface = new LibraryInterface(in);
		libraryInterface.start();
		
	}
	
	public void addBooks(int branch)
	{
		System.out.println("Choose a book to add by bookId.");
		librarianService.readAllBooks();
		String input = in.nextLine();
		Integer bookId = Integer.parseInt(input);
		System.out.println("How many copies would you like to add");
		input = in.nextLine();
		Integer bookCount = Integer.parseInt(input);
		librarianService.addBooksToBranch(branch,bookId,bookCount);
		System.out.println("books have been added to your branch. Returning to main menu");
		LibraryInterface libraryInterface = new LibraryInterface(in);
		libraryInterface.start();
	}
}
