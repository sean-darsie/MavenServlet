/**
 * 
 */
package com.smoothstack.training.libraryproject;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author seandarsie
 *
 */
public class PublisherService implements Reader,Writer {
	
	private static final PublisherService instance = new PublisherService();
	private int numberOfRecords = 1;
    
    //private constructor to avoid client applications to use constructor
    private PublisherService(){}

    public static PublisherService getInstance(){
        return instance;
    }
    
    /**
     * Attempts to create a record of the publisher provided in the database
     * @param publisher
     * @return
     */
	public String createPublisher(Publisher publisher)
	{
		if (publisher == null)
			return "Publisher cannot be null";
		if (publisher.getName().length() < 2 || publisher.getName().length() > 45)
			return "publisher name must be between 2 and 45 characters in length";
		
		publisher.setPublisherId(numberOfRecords++);
		String allpublishers = readFromFile("resources/libraryfiles/publishers.txt");
		if (allpublishers.contains(publisher.recordInfo()))
		{
			return "Publisher named " + publisher.getName()+ " already exists";
		}
		if (allpublishers.length() == 0)
		{
			writeToFile("resources/libraryfiles/publishers.txt",publisher.recordInfo());
		}
		else
		{
			appendToFile("resources/libraryfiles/publishers.txt","\n"+publisher.recordInfo());
		}
		return "Successfully added "+publisher.getName();
	}
	
	/**
	 * Reads all the publishers from the database and returns them in a formatted string.
	 * @return
	 */
	public String readPublisher()
	{
		StringBuilder stringBuilder = new StringBuilder();
		try(BufferedReader bufStream = new BufferedReader(new FileReader("resources/libraryfiles/publishers.txt"))){
			String line = bufStream.readLine();
			while(line!=null){
				Publisher publisher = new Publisher(1, "placeholder","placeholder");
				if (line.contains(","))
				{
					String[] records = line.split(",");
					publisher.setName(records[1]);
					publisher.setAddress(records[2]);
					publisher.setPublisherId(Integer.parseInt(records[0]));
				}
				else
				{
					line = bufStream.readLine();
					continue;
				}
				stringBuilder.append(publisher.displayInfo()+"\n");
				line = bufStream.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read from publisher file in readpublisher");
		}
		return stringBuilder.toString();

	}

	/**
	 * Updates a record in the publisher database
	 * @param oldpublisher
	 * @param newpublisher
	 * @return
	 */
	public String updatePublisher(Publisher oldpublisher, Publisher newpublisher)
	{
		if (oldpublisher == null || newpublisher == null)
			return "Publisher cannot be null";
		if (newpublisher.getName().length() < 2 || newpublisher.getName().length() > 45)
			return "publisher name must be between 2 and 45 characters in length";
		
		String allpublishers = readFromFile("resources/libraryfiles/publishers.txt");
		if (allpublishers.contains(oldpublisher.getName()) != true)
		{
			return "There is no record of "+oldpublisher.getName()+".";
		}

		// write new data to the file
		StringBuilder stringBuilder = new StringBuilder();
		try(BufferedReader bufStream = new BufferedReader(new FileReader("resources/libraryfiles/publishers.txt"))){
			String line = bufStream.readLine();
			while(line!=null){
				Publisher publisher = new Publisher(1, "placeholder","placeholder");
				if (line.contains(","))
				{
					String[] records = line.split(",");
					if (records[1] == oldpublisher.getName())
					{
						publisher.setName(newpublisher.getName());
						publisher.setAddress(newpublisher.getAddress());
					}
					else 
					{
						publisher.setName(records[1]);
						publisher.setAddress(records[2]);
					}
					publisher.setPublisherId(Integer.parseInt(records[0]));
				}
				else
				{
					line = bufStream.readLine();
					continue;
				}
				stringBuilder.append(publisher.displayInfo()+"\n");
				line = bufStream.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read from publisher file in readpublisher");
		}
		writeToFile("resources/libraryfiles/publishers.txt",stringBuilder.toString());
		return "Success. Replaced "+oldpublisher.getName()+" with "+newpublisher.getName();
	}
	
	/**
	 * Deletes the specified publisher from the database.
	 * @param publisher
	 * @return
	 */
	public String deletePublisher(Publisher publisher)
	{
		if (publisher == null)
			return "Publisher cannot be null";
		
		String allpublishers = readFromFile("resources/libraryfiles/publishers.txt");
		if (allpublishers.contains(publisher.getName()) != true)
		{
			return "There is no record of "+publisher.getName()+".";
		}
		StringBuffer stringBuffer = new StringBuffer(allpublishers);
		String toDelete = publisher.recordInfo();
		int start = stringBuffer.indexOf(toDelete);
		int end = start + toDelete.length();
		stringBuffer.delete(start, end);
		// write to file
		writeToFile("resources/libraryfiles/publishers.txt", stringBuffer.toString());
		
		return "Deleted "+publisher.getName()+" from the records";
	}
}
