/**
 * 
 */
package com.ss.lms.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.AdminService;
import com.ss.lms.service.BorrowerService;
import com.ss.lms.service.UtilService;

/**
 * @author seandarsie
 *
 */
public class AdminUi {
	Scanner in;
	AdminService adminService;
	UtilService utilService;
	BorrowerService borrowerService;

	public AdminUi(Scanner in) {
		this.in = in;
		adminService = new AdminService();
		utilService = new UtilService();
		borrowerService = new BorrowerService();
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
		promptChoice();		
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
		String name = in.nextLine();

		System.out.println("Please provide a comma separated list of authorIds to associate with this book");
		String[] authors = in.nextLine().split(",");
		List<Author> authorList = new ArrayList<Author>();
		for (String s: authors)
		{
			authorList.add(utilService.getAuthorById(Integer.parseInt(s)));
		}
		
		System.out.println("Please provide a comma separated list of genreIds to associate with this book");
		String[] genres = in.nextLine().split(",");
		List<Genre> genreList = new ArrayList<Genre>();
		for (String s: genres)
		{
			genreList.add(utilService.getGenreById(Integer.parseInt(s)));
		}
		
		System.out.println("Please provide the publisherId of the publisher for this book");
		
		Integer publisher = Integer.parseInt(in.nextLine());
		Book bookToSave = new Book(null, name, publisher, authorList);
		bookToSave.setGenres(genreList);
		adminService.saveBook(bookToSave);
		adminService.createBookRelations(bookToSave);
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
		promptChoice();
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
			updateAuthor();
			break;
		case"2":
			updateBook();
			break;
		case"3":
			updatePub();
			break;
		case"4":
			updateBranch();
			break;
		case"5":
			updateGenre();
			break;
		case"6":
			updateBorrower();
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
		promptChoice();
		
	}

	private void updateAuthor() {
		// TODO Auto-generated method stub
		System.out.println("Choose a author to update by id");
		readAuthor();
		String input = in.nextLine();
		Integer authorId = Integer.parseInt(input);
		Author author = utilService.getAuthorById(authorId);

		if (author != null)
		{	
			System.out.println("enter a new name for this author or press enter to leave it the same");
			String name = in.nextLine();
			if (name.length() > 1)
				author.setName(name);
			adminService.saveAuthor(author);
		}
	}

	private void updateGenre() {
		// TODO Auto-generated method stub
		
		System.out.println("Choose a genre to update by id");
		readGenre();
		String input = in.nextLine();
		Integer genreId = Integer.parseInt(input);
		Genre genre = utilService.getGenreById(genreId);

		if (genre != null)
		{	
			System.out.println("enter a new name for this genre or press enter to leave it the same");
			String name = in.nextLine();
			if (name.length() > 1)
				genre.setName(name);
			adminService.saveGenre(genre);
		}
	}

	private void updateBranch() {
		// TODO Auto-generated method stub
		System.out.println("Choose a branch to update by id");
		readBranch();
		String input = in.nextLine();
		Integer branchId = Integer.parseInt(input);
		Branch branch = utilService.getBranchById(branchId);

		if (branch != null)
		{	
			System.out.println("enter a new name for this branch or press enter to leave it the same");
			String name = in.nextLine();
			if (name.length() > 1)
				branch.setName(name);
			System.out.println("enter a new address for this branch or press enter to leave it the same");
			String address = in.nextLine();
			if (name.length() > 1)
				branch.setName(address);
			adminService.saveBranch(branch);
		}
		
	}

	private void updatePub() {
		// TODO Auto-generated method stub
		System.out.println("Choose a publisher to update by id");
		readPub();
		String input = in.nextLine();
		Integer publisherId = Integer.parseInt(input);
		Publisher publisher = utilService.getPublisherById(publisherId);

		if (publisher != null)
		{	
			System.out.println("enter a new name for this publisher or press enter to leave it the same");
			String name = in.nextLine();
			if (name.length() > 1)
				publisher.setName(name);
			System.out.println("enter a new address for this publisher or press enter to leave it the same");
			String address = in.nextLine();
			if (name.length() > 1)
				publisher.setName(address);
			System.out.println("enter a new phone for this publisher or press enter to leave it the same");
			String phone = in.nextLine();
			if (name.length() > 1)
				publisher.setName(phone);
			adminService.savePublisher(publisher);
		}
		
	}

	private void updateBook() {
		// TODO Auto-generated method stub
		System.out.println("Choose a book to update by id");
		readBook();
		String input = in.nextLine();
		Integer bookId = Integer.parseInt(input);
		Book book = utilService.getBookById(bookId);

		if (book != null)
		{	
			System.out.println("enter a new title for this book or press enter to leave it the same");
			String name = in.nextLine();
			if (name.length() > 1)
				book.setName(name);
			adminService.saveBook(book);
		}
		
	}

	private void updateBorrower() {
		// TODO Auto-generated method stub
		System.out.println("Choose a borrower to update by id");
		readBorrower();
		String input = in.nextLine();
		Integer borrowerId = Integer.parseInt(input);
		Borrower borrower = utilService.getBorrowerById(borrowerId);
		
		System.out.println("Would you like to update the details of the borrower or give the borrower an extension on a book loan?");
		System.out.println("1) extend book loan");
		System.out.println("2) update details");
		System.out.println("r) main menu");
		System.out.println("q) quit");
		
		String choice = in.nextLine();

		switch(choice)
		{
		case"1":
			BookLoan[] loans = borrowerService.getLoansByUser(borrowerId);
			System.out.println("Please choose a loan to extend by choosing from the loan numbers on the left");
			for (int i = 0; i < loans.length; i++)
			{
				System.out.println(i+") bookId: "+ loans[i].getBookId()+" due date: "+ loans[i].getDateDue());
			}
			input = in.nextLine();
			adminService.giveLoanExtension(loans[Integer.parseInt(input)]);
			
			break;
		case "2":
			if (borrower != null)
			{	
				System.out.println("enter a new name for this borrower or press enter to leave it the same");
				String name = in.nextLine();
				if (name.length() > 1)
					borrower.setName(name);
				System.out.println("enter a new address for this borrower or press enter to leave it the same");
				String address = in.nextLine();
				if (address.length() > 1)
					borrower.setAddress(address);
				System.out.println("enter a new phone for this borrower or press enter to leave it the same");
				String phone = in.nextLine();
				if (phone.length() > 1)
					borrower.setPhone(phone);
				adminService.saveBorrower(borrower);
			}
			break;
		case"r":
			LibraryInterface libraryInterface = new LibraryInterface(in);
			libraryInterface.start();
			break;
		case "q":
			System.exit(0);
			break;
		default:
			System.out.println("please choose one of the options");
			updateBorrower();
			break;
		}
		promptChoice();
	}

	private void promptDelete() {
		// TODO Auto-generated method stub
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
			deleteAuthor();
			
			break;
		case"2":
			deleteBook();
			break;
		case"3":
			deletePub();
			break;
		case"4":
			deleteBranch();
			break;
		case"5":
			deleteGenre();
			break;
		case"6":
			deleteBorrower();
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
		promptChoice();	
	}

	private void deleteBorrower() {
		// TODO Auto-generated method stub
		System.out.println("Choose an borrower to delete by id");
		readBorrower();
		String input = in.nextLine();
		Integer borrowerId = Integer.parseInt(input);
		Borrower borrower = utilService.getBorrowerById(borrowerId);
		if (borrower != null)
		{
			borrower.setName(null);
			adminService.saveBorrower(borrower);
		}
	}

	private void deleteGenre() {
		// TODO Auto-generated method stub
		
		System.out.println("Choose an genre to delete by id");
		readGenre();
		String input = in.nextLine();
		Integer genreId = Integer.parseInt(input);
		Genre genre = utilService.getGenreById(genreId);
		if (genre != null)
		{
			genre.setName(null);
			adminService.saveGenre(genre);
		}
	}

	private void deleteBranch() {
		// TODO Auto-generated method stub
		System.out.println("Choose an branch to delete by id");
		readBranch();
		String input = in.nextLine();
		Integer branchId = Integer.parseInt(input);
		Branch branch = utilService.getBranchById(branchId);
		if (branch != null)
		{
			branch.setName(null);
			adminService.saveBranch(branch);
		}
		
	}

	private void deletePub() {
		// TODO Auto-generated method stub
		System.out.println("Choose an publisher to delete by id");
		readPub();
		String input = in.nextLine();
		Integer publisherId = Integer.parseInt(input);
		Publisher publisher = utilService.getPublisherById(publisherId);
		if (publisher != null)
		{
			publisher.setName(null);
			adminService.savePublisher(publisher);
		}
		
	}

	private void deleteBook() {
		// TODO Auto-generated method stub
		System.out.println("Choose an book to delete by id");
		readBook();
		String input = in.nextLine();
		Integer bookId = Integer.parseInt(input);
		Book book = utilService.getBookById(bookId);
		if (book != null)
		{
			book.setName(null);
			adminService.saveBook(book);
		}
		
	}

	private void deleteAuthor() {
		// TODO Auto-generated method stub
		System.out.println("Choose an author to delete by id");
		readAuthor();
		String input = in.nextLine();
		Integer authorId = Integer.parseInt(input);
		Author author = utilService.getAuthorById(authorId);
		if (author != null)
		{
			author.setName(null);
			adminService.saveAuthor(author);
		}
		
	}
	
}
