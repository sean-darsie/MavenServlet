/**
 * 
 */
package com.smoothstack.training.libraryproject.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smoothstack.training.libraryproject.Author;
import com.smoothstack.training.libraryproject.AuthorService;
import com.smoothstack.training.libraryproject.Constants;

/**
 * @author seandarsie
 *
 */
public class AuthorServiceTest {
	
	@Test
	public void authorCannotBeNull()
	{
		
		assertEquals(AuthorService.getInstance().createAuthor(Constants.testAuthorFile,null), "Author cannot be null");
		assertEquals(AuthorService.getInstance().deleteAuthor(Constants.testAuthorFile,null), "Author cannot be null");
		assertEquals(AuthorService.getInstance().updateAuthor(Constants.testAuthorFile,null, null), "Author cannot be null");
	}
	
	@Test
	public void createAuthorTest()
	{
		String data = "1,author1\n2,author2";
		AuthorService.getInstance().writeToFile(Constants.testAuthorFile, data);
		
		Author author = new Author(1, "George RR Martin");
		String result = AuthorService.getInstance().createAuthor(Constants.testAuthorFile,author);
		
		assertEquals(result,"Successfully added "+author.getName());
		result = AuthorService.getInstance().createAuthor(Constants.testAuthorFile,author);

		assertEquals(result,"Author named " + author.getName()+ " already exists");
	}
	
	@Test
	public void authorNameMustFitRequirements()
	{
		Author author = new Author(1, "e");
		Author author2 = new Author(1, "this is more than 45 characters so it will not be added to the database");
		
		String result = AuthorService.getInstance().createAuthor(Constants.testAuthorFile,author);
		String result2 = AuthorService.getInstance().createAuthor(Constants.testAuthorFile,author2);

		assertEquals(result,"Author name must be between 2 and 45 characters in length");
		assertEquals(result2,"Author name must be between 2 and 45 characters in length");
		
		String result3 = AuthorService.getInstance().updateAuthor(Constants.testAuthorFile,author,author2);
		String result4 = AuthorService.getInstance().updateAuthor(Constants.testAuthorFile,author2,author);
		
		assertEquals(result3,"Author name must be between 2 and 45 characters in length");
		assertEquals(result4,"Author name must be between 2 and 45 characters in length");
	}
	
	
	public void cannotDeleteOrUpdateNonexistingRecord()
	{
		String data = "1,george\n2,evan\n3,paul";
		AuthorService.getInstance().writeToFile(Constants.testAuthorFile, data);
		
		Author author = new Author(1, "george");
		
		String result = AuthorService.getInstance().updateAuthor(Constants.testAuthorFile,author,new Author(1,"replacement"));
		String result1 = AuthorService.getInstance().deleteAuthor(Constants.testAuthorFile,author);
		
		assertEquals(result,"There is no record of "+author.getName()+".");
		assertEquals(result1,"There is no record of "+author.getName()+".");

	}
	
	
	@Test
	public void canReadAuthors()
	{
		// make sure the file has known values in it. 
		
		AuthorService.getInstance().createAuthor(Constants.testAuthorFile,new Author(1,"brett"));
		
		assertEquals(AuthorService.getInstance().readAuthor(Constants.testAuthorFile).contains("brett"), true);
		
	}
	
	
	@Test
	public void canDeleteAuthor()
	{
		
		Author author = new Author(1, "test");
		AuthorService.getInstance().createAuthor(Constants.testAuthorFile,author);
		
		assertEquals(AuthorService.getInstance().deleteAuthor(Constants.testAuthorFile,author),"Deleted "+author.getName()+" from the records");
	}
	
	
	
}
