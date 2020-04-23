/**
 * 
 */
package com.smoothstack.training.libraryproject.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smoothstack.training.libraryproject.Book;
import com.smoothstack.training.libraryproject.BookService;
import com.smoothstack.training.libraryproject.Constants;
import com.smoothstack.training.libraryproject.Book;
import com.smoothstack.training.libraryproject.BookService;
import com.smoothstack.training.libraryproject.Book;
import com.smoothstack.training.libraryproject.BookService;
import com.smoothstack.training.libraryproject.Book;
import com.smoothstack.training.libraryproject.BookService;
import com.smoothstack.training.libraryproject.Book;
import com.smoothstack.training.libraryproject.BookService;


/**
 * @book seandarsie
 *
 */
public class BookServiceTest {

	@Test
	public void bookCannotBeNull()
	{		
		assertEquals(BookService.getInstance().createBook(null), "Book cannot be null");
		assertEquals(BookService.getInstance().deleteBook(null), "Book cannot be null");
		assertEquals(BookService.getInstance().updateBook(null,null), "Book cannot be null");
	}
	
	@Test
	public void createBookTest()
	{
		// clear file.
		BookService.getInstance().writeToFile(Constants.testBookFile,"asdf");
		
		Book book = new Book(1, "game of thrones","George RR Martin","publisher");
		String result = BookService.getInstance().createBook(book);
		
		assertEquals(result,"Successfully added "+book.getName());
		
		result = BookService.getInstance().createBook(book);
		assertEquals(result,"Book named " + book.getName()+ " already exists");
	}
	
	@Test
	public void bookNameMustFitRequirements()
	{
		Book book = new Book(1, "e","authro","publisher");
		Book book2 = new Book(1, "this is more than 45 characters so it will not be added to the database","Book","publisher");
		
		String result = BookService.getInstance().createBook(book);
		String result2 = BookService.getInstance().createBook(book2);

		assertEquals(result,"Book name must be between 2 and 45 characters in length");
		assertEquals(result2,"Book name must be between 2 and 45 characters in length");
		
		String result3 = BookService.getInstance().updateBook(book,book2);
		String result4 = BookService.getInstance().updateBook(book2,book);
		
		assertEquals(result3,"Book name must be between 2 and 45 characters in length");
		assertEquals(result4,"Book name must be between 2 and 45 characters in length");
	}
	
	public void cannotDeleteOrUpdateNonexistingRecord()
	{
		String data = "1,george,book,publisher\n2,evan,book,publisher\n3,paul,book,publisher";
		BookService.getInstance().writeToFile(Constants.testBookFile, data);
		
		Book book = new Book(1, "nonexisting","book","publisher");
		
		String result = BookService.getInstance().updateBook(book,new Book(1,"replacement","book","publisher"));
		String result1 = BookService.getInstance().deleteBook(book);
		
		assertEquals(result,"There is no record of "+book.getName()+".");
		assertEquals(result1,"There is no record of "+book.getName()+".");

	}
	
	@Test
	public void canReadBooks()
	{
		// make sure the file has known values in it. 
		String data = "1,george,book,publisher\n2,evan,book,publisher\n3,paul,book,publisher";
		BookService.getInstance().writeToFile(Constants.testBookFile, data);
		
		BookService.getInstance().createBook(new Book(1,"brett","book","publisher"));
		
		assertEquals(BookService.getInstance().readBook().contains("brett"), true);
		
	}
	
	@Test
	public void canDeleteBook()
	{
		String data = "1,george,book,publisher\n2,evan,book,publisher\n3,paul,book,publisher";
		BookService.getInstance().writeToFile(Constants.testBookFile, data);

		Book book = new Book(1, "hello","author","publisher");
		BookService.getInstance().createBook(book);
		
		assertEquals(BookService.getInstance().deleteBook(book),"Deleted "+book.getName()+" from the records");
	}
//	
}
