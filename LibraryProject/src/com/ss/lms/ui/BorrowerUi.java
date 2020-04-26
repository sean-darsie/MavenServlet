/**
 * 
 */
package com.ss.lms.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.lms.doa.BorrowerDao;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.service.BorrowerService;

/**
 * @author seandarsie
 *
 */
public class BorrowerUi {
	Scanner in;
	UserInterface ui;
	BorrowerService borrowerService;
	
	public BorrowerUi(Scanner in)
	{
		ui = new UserInterface(in);
		borrowerService = new BorrowerService();
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
							promptBranchChoice();
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
	
	public void promptBranchChoice()
	{
		// basically get all branches from the branchDao and show them to the user 
		System.out.println("Choose your library branch from the below by branch id.");
		List<Branch> branches = null;
		try {
			branches = borrowerService.ListAllBranches();
		} catch (SQLException e) {
			System.out.println("problem getting branches in borrower Ui");
			e.printStackTrace();
		}
		for (Branch b: branches)
		{
			System.out.println("BranchId: "+b.getBranchId()+"Branch Name: "+b.getName());
		}
	}
	public void promptBookChoice()
	{
		System.out.println("choose a book to check out");
		// once they choose the book. Make a new record in tbl_book_loans with the cardno/bookid/branchid/todaysdate/duedate/null
		// reduce the count of that book in the database by 1
	}
	public void prompBookReturn()
	{
		System.out.println("choose a book that you currently have borrowed that you wish to return");
		// once they choose the book to return. Simply update the database with todays date, and tell the user the book has been returned.
	}
}
