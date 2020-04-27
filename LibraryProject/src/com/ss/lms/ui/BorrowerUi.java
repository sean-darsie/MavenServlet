/**
 * 
 */
package com.ss.lms.ui;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ss.lms.doa.BorrowerDao;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.service.BorrowerService;
import com.ss.lms.service.UtilService;

/**
 * @author seandarsie
 *
 */
public class BorrowerUi {
	Scanner in;
	UserInterface ui;
	BorrowerService borrowerService;
	UtilService utilService;
	
	public BorrowerUi(Scanner in)
	{
		ui = new UserInterface(in);
		borrowerService = new BorrowerService();
		utilService = new UtilService();
		this.in = in;
	}
	
	
	public void start()
	{
		ui.borrowerLoginPrompt();
		String input = in.nextLine();
		
		switch(input)
		{
			case "r":
				
				break;
			default:
				Integer cardNo = Integer.parseInt(input);
				if (cardNo != null)
				{
					try {
						if (borrowerService.validateUser(cardNo) != false)
						{
							returnOrCheckout(cardNo);
						}
						else // return to main menu
						{
							LibraryInterface libraryInterface = new LibraryInterface(in);
							libraryInterface.start();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						// return to main
						System.out.println("problem in prompt login");
						e.printStackTrace();
					}
				}
				break;
		}
	}
	
	public void returnOrCheckout(int cardNo)
	{
		System.out.println("Would you like to return or check out a book?");
		System.out.println("1) Check out.");
		System.out.println("2) Return.");
		System.out.println("r) back to main menu");
		System.out.println("q) quit");
		String input = in.nextLine();
		
		switch(input)
		{
		case"1":
			promptBranchChoice(cardNo);
			break;
		case"2":
			promptBookReturn(cardNo);
			break;
		case"r":
			LibraryInterface lms = new LibraryInterface(in);
			lms.start();
			break;
		default:
			System.out.println("you have not entered one of the options.");
			returnOrCheckout(cardNo);
			break;
		}
	}
	
	public void promptBookReturn(int cardNo)
	{
		System.out.println("please choose a book to return by number");
		BookLoan[] loans = borrowerService.getLoansByUser(cardNo);

		if (loans.length < 1)
		{
			System.out.println("you have no loans at the moment.");
			LibraryInterface lms = new LibraryInterface(in);
			lms.start();
		}


		for (int i = 0; i < loans.length; i++)
		{
			String bookName = utilService.getBookById((loans[i].getBookId())).getName();
			System.out.println(i+") book: "+bookName+" due date: "+((BookLoan) loans[i]).getDateDue());
		}
		String input = in.nextLine();
		
		if (input =="r")
		{
			LibraryInterface lms = new LibraryInterface(in);
			lms.start();
		} else if (input =="q")
			System.exit(0);
		else
		{
			Integer index = Integer.parseInt(input);
			if (index >= loans.length)
			{
				System.out.println("you have not chosen a record that exists. Please enter an existing loan");
				promptBookReturn(cardNo);
			}
			else {
				loans[index].setDateIn(new Date());
				borrowerService.returnABook((BookLoan) loans[index]);
				System.out.println("you have successfully returned the book. Thank you");
				LibraryInterface lms = new LibraryInterface(in);
				lms.start();
			}
		}
			
			
		
	}
	
	public void promptBranchChoice(int cardNo)
	{
		// basically get all branches from the branchDao and show them to the user 
		System.out.println("Choose your library branch from the below by branch id.");
		List<Branch> branches = null;
		branches = borrowerService.ListAllBranches();
		for (Branch b: branches)
		{
			System.out.println("BranchId: "+b.getBranchId()+" Branch Name: "+b.getName());
		}
		String input = in.nextLine();
		
		if (input == "r")
		{
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
		}
		else
		{
			Integer branch = Integer.parseInt(input);
			promptBookChoice(branch, cardNo);
		}
	}
	public void promptBookChoice(int branch, int cardNo)
	{
		
		System.out.println("choose a book to check out");		
		try 
		{
			borrowerService.showAvailableBooks(branch);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Something went wrong in promptBookChoice");
		}
		// once they choose the book. Make a new record in tbl_book_loans with the cardno/bookid/branchid/todaysdate/duedate/null
		// reduce the count of that book in the database by 1
		String input = in.nextLine();
		if (input == "r")
		{
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
		} else if (input == "q")
		{
			System.exit(0);
		}
		else
		{
			Integer bookId = Integer.parseInt(input);
			borrowerService.checkOutABook(bookId, branch, cardNo);
			System.out.println("you have checked out your book. Returning to main menu");
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
		}
	}
}
