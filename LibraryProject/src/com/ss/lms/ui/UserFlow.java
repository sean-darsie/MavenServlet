package com.ss.lms.ui;

import java.util.Scanner;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;

public class UserFlow {
	UserInterface ui;
	Scanner in;
	
	public UserFlow(Scanner in)
	{
		this.in = in;
		ui = new UserInterface(this.in);
	}
	
	public void startLibrarySystem()
	{
		ui.welcomeMessage();
		beginQuerying();
	}
	
	public void beginQuerying()
	{
		ui.mainMenuOptions();
        String input = in.nextLine();
        switch(input) {
        case "1":
        	create();
        	break;
        case "2":
        	read();
        	break;
        case "3":
        	update();
        	break;
        case "4": 
        	delete();
        	break;
        case "q": // quit the application
        	System.out.println("Thank you for using the libray system");
        	in.close();
        	break;
        default:
        	
        	System.out.println("you have not entered one of the correct options.");
        	beginQuerying();
        	break;
        }
        
		
	}
	
	public void create() {
		ui.chooseDatabase();
		String input = in.nextLine();
		switch(input){
		case"1":
			// create publisher record
			Publisher newPublisher = ui.getPublisherInfo();
			System.out.println(PublisherService.getInstance().createPublisher(newPublisher));
			
			beginQuerying();
			break;
		case"2":
			// create author record
			Author newAuthor = ui.getAuthorInfo();
			System.out.println(AuthorService.getInstance().createAuthor(Constants.authorFile,newAuthor));
			
			beginQuerying();
			break;
		case"3":
			// create book record
			Book newBook = ui.getBookInfo();
			System.out.println(BookService.getInstance().createBook(newBook));
			
			beginQuerying();
			break;
		case"r":
	        // return to main menu
			beginQuerying();
			break;
		default:
			// incorrect input
        	System.out.println("you have not entered one of the correct options.");
        	create();
			break;
		}
	}
	
	public void read() {
		ui.chooseDatabase();
		String input = in.nextLine();
		switch(input){
		case"1":
			//read publisher records
			
			System.out.println(PublisherService.getInstance().readPublisher());
			beginQuerying();
			break;
		case"2":
			// read author records
			
			System.out.println(AuthorService.getInstance().readAuthor(Constants.authorFile));
			beginQuerying();
			break;
		case"3":
			// read book records		
			
			System.out.println(BookService.getInstance().readBook());
			beginQuerying();
			break;
		case"r":
			beginQuerying();
			break;
		default:
			
        	System.out.println("you have not entered one of the correct options.");
        	read();
			break;
		}
        

	}
	
	public void update() {
		ui.chooseDatabase();
		String input = in.nextLine();
		switch(input){
		case"1":
			//update publisher record
			
			System.out.println("Current publisher record");
			Publisher oldRecord = ui.getPublisherInfo();
			System.out.println("New publisher record");
			Publisher newRecord = ui.getPublisherInfo();
			System.out.println(PublisherService.getInstance().updatePublisher(oldRecord,newRecord));
			beginQuerying();
			break;
		case"2":
			// update author record
			
			System.out.println("Current author record");
			Author oldAuthor = ui.getAuthorInfo();
			System.out.println("New publisher record");
			Author newAuthor = ui.getAuthorInfo();
			System.out.println(AuthorService.getInstance().updateAuthor(Constants.authorFile,oldAuthor,newAuthor));			beginQuerying();
			break;
		case"3":
			// update book record		
			
			System.out.println("Current publisher record");
			Book oldBook = ui.getBookInfo();
			System.out.println("New publisher record");
			Book newBook = ui.getBookInfo();
			System.out.println(BookService.getInstance().updateBook(oldBook,newBook));
			beginQuerying();
			break;
		case"r":
			beginQuerying();
			break;
		default:
			
        	System.out.println("you have not entered one of the correct options.");
        	update();
			break;
		}
        

	}
	
	public void delete() {
		ui.chooseDatabase();
		String input = in.nextLine();
		switch(input){
		case"1":
			//delete publisher record
			Publisher pubRecord = ui.getPublisherInfo();
			
			System.out.println(PublisherService.getInstance().deletePublisher(pubRecord));
			beginQuerying();
			break;
		case"2":
			// delete author record	
			Author authRecord = ui.getAuthorInfo();
			
			System.out.println(AuthorService.getInstance().deleteAuthor(Constants.authorFile,authRecord));
			beginQuerying();
			break;
		case"3":
			// delete book record			
			Book bookRecord = ui.getBookInfo();
			System.out.println(BookService.getInstance().deleteBook(bookRecord));
			
			beginQuerying();
			break;
		case"r":
			
			beginQuerying();
			break;
		default:
        	System.out.println("you have not entered one of the correct options.");
        	
        	read();
			break;
		}
}
