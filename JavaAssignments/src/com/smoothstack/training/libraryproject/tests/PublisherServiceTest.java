/**
 * 
 */
package com.smoothstack.training.libraryproject.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smoothstack.training.libraryproject.Constants;
import com.smoothstack.training.libraryproject.Publisher;
import com.smoothstack.training.libraryproject.PublisherService;


/**
 * @author seandarsie
 *
 */
public class PublisherServiceTest {

	@Test
	public void publisherCannotBeNull()
	{		
		assertEquals(PublisherService.getInstance().createPublisher(null), "Publisher cannot be null");
		assertEquals(PublisherService.getInstance().deletePublisher(null), "Publisher cannot be null");
		assertEquals(PublisherService.getInstance().updatePublisher(null,null), "Publisher cannot be null");
	}
	
	@Test
	public void createPublisherTest()
	{
		PublisherService.getInstance().writeToFile(Constants.testPublisherFile,"asdf");
		Publisher publisher = new Publisher(1, "publisher","Address");
		String result = PublisherService.getInstance().createPublisher(publisher);
		
		
		assertEquals(result,"Successfully added "+publisher.getName());
		result = PublisherService.getInstance().createPublisher(publisher);

		assertEquals(result,"Publisher named " + publisher.getName()+ " already exists");
	}
//	
	@Test
	public void publisherNameMustFitRequirements()
	{
		Publisher publisher = new Publisher(1, "e","address");
		Publisher publisher2 = new Publisher(1, "this is more than 45 characters so it will not be added to the database","address");
		
		String result = PublisherService.getInstance().createPublisher(publisher);
		String result2 = PublisherService.getInstance().createPublisher(publisher2);

		assertEquals(result,"publisher name must be between 2 and 45 characters in length");
		assertEquals(result2,"publisher name must be between 2 and 45 characters in length");
		
		String result3 = PublisherService.getInstance().updatePublisher(publisher,publisher2);
		String result4 = PublisherService.getInstance().updatePublisher(publisher2,publisher);
		
		assertEquals(result3,"publisher name must be between 2 and 45 characters in length");
		assertEquals(result4,"publisher name must be between 2 and 45 characters in length");
	}
	
	public void cannotDeleteOrUpdateNonexistingRecord()
	{
		String data = "1,george,publisher,publisher\n2,evan,publisher,publisher\n3,paul,publisher,publisher";
		PublisherService.getInstance().writeToFile(Constants.testPublisherFile, data);
		
		Publisher publisher = new Publisher(1,"name not in database","address");
		
		String result = PublisherService.getInstance().updatePublisher(publisher,new Publisher(1,"publisher","address"));
		String result1 = PublisherService.getInstance().deletePublisher(publisher);
		
		assertEquals(result,"There is no record of "+publisher.getName()+".");
		assertEquals(result1,"There is no record of "+publisher.getName()+".");

	}
	
	@Test
	public void canReadPublishers()
	{
		// make sure the file has known values in it. 
		String data = "1,george,publisher,publisher\n2,evan,publisher,publisher\n3,paul,publisher,publisher";
		PublisherService.getInstance().writeToFile(Constants.testPublisherFile, data);
		
		assertEquals(PublisherService.getInstance().readPublisher().contains("publisher"), true);
		
	}
	
	@Test
	public void canDeletePublisher()
	{
		
		Publisher publisher = new Publisher(1, "publisher","address");
		PublisherService.getInstance().createPublisher(publisher);
		
		assertEquals(PublisherService.getInstance().deletePublisher(publisher),"Deleted "+publisher.getName()+" from the records");
	}
}
