/**
 * 
 */
package com.smoothstack.training.libraryproject;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author sean darsie
 *
 */
public class AuthorService implements Reader, Writer {

	private static final AuthorService instance = new AuthorService();
	private int numberOfRecords = 1;
    
    //private constructor to avoid client applications using the constructor
    private AuthorService(){}

    public static AuthorService getInstance(){
        return instance;
    }
    
    /**
     * 
     * @param path
     * @param author
     * @return the result of attempting to create an author with the object provided
     */
	public String createAuthor(String path,Author author)
	{
		if (author == null)
			return "Author cannot be null";
		if (author.getName().length() < 2 || author.getName().length() > 45)
			return "Author name must be between 2 and 45 characters in length";
		
		// set the id of the new record behind the scenes
		author.setAuthorId(numberOfRecords++);
		
		// read the file to make check if the author already exists
		String allAuthors = readFromFile(path);
		
		if (allAuthors.contains(","+author.getName()+"\n"))
		{
			return "Author named " + author.getName()+ " already exists";
		}
		if (allAuthors.length() == 0)
		{
			writeToFile(path,author.getAuthorId()+ ","+author.getName());
		}
		else
		{
			appendToFile(path,"\n"+author.recordInfo());
		}
		
		return "Successfully added "+author.getName();
	}
	
	/**
	 * 
	 * @param path to the file that contains the author info
	 * @return the formatted list of all the authors in the database
	 */
	public String readAuthor(String path)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		try(BufferedReader bufStream = new BufferedReader(new FileReader(path))){
			String line = bufStream.readLine();
			while(line!=null){
				Author author = new Author(1, "placeholder");
				if (line.contains(","))
				{
					author.setName(line.substring(line.indexOf(",")+1, line.length()));
					author.setAuthorId(Integer.parseInt(line.substring(0, line.indexOf(","))));
				}
				else
				{
					line = bufStream.readLine();
					continue;
				}
				stringBuilder.append(author.displayInfo()+"\n");
				line = bufStream.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read from author file in readAuthor");
		}
		return stringBuilder.toString();

	}
	
	/**
	 * 
	 * @param path
	 * @param oldAuthor
	 * @param newAuthor
	 * @return the result of the attempt to update author
	 */
	public String updateAuthor(String path, Author oldAuthor, Author newAuthor)
	{
		
		if (oldAuthor == null || newAuthor == null)
			return "Author cannot be null";
		if (newAuthor.getName().length() < 2 || newAuthor.getName().length() > 45)
			return "Author name must be between 2 and 45 characters in length";
		
		// read authors from text file
		String allAuthors = readFromFile(path);
		
		// ensure just one 
		if (allAuthors.contains(","+oldAuthor.getName()+"\n") != true)
		{
			return "There is no record of "+oldAuthor.getName()+".";
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		try(BufferedReader bufStream = new BufferedReader(new FileReader(path))){
			String line = bufStream.readLine();
			while(line!=null){
				Author author = new Author(1, "placeholder");
				if (line.contains(","))
				{
					if (line.contains(oldAuthor.getName()))
					{
						author.setName(newAuthor.getName());
					}
					else
					{
						author.setName(line.substring(line.indexOf(",")+1, line.length()));
					}
					author.setAuthorId(Integer.parseInt(line.substring(0, line.indexOf(","))));
				}
				else
				{
					line = bufStream.readLine();
					continue;
				}
				stringBuilder.append(author.getAuthorId()+"," +author.getName()+"\n");
				line = bufStream.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read from author file in readAuthor");
		}

		// write new data to the file
		writeToFile(path,stringBuilder.toString());
		return "Success. Replaced "+oldAuthor.getName()+" with "+newAuthor.getName();
	}
	
	/**
	 * 
	 * @param path
	 * @param author
	 * @return the result of attempting to delete the author from the database
	 */
	public String deleteAuthor(String path, Author author)
	{
		if (author == null)
			return "Author cannot be null";
		
		String allAuthors = readFromFile(path);
		
		if (allAuthors.contains(","+author.getName()+"\n") != true)
		{
			return "There is no record of "+author.getName()+".";
		}
		StringBuffer stringBuffer = new StringBuffer(allAuthors);
		int start = stringBuffer.indexOf(author.getAuthorId()+","+author.getName());
		int end = start + (author.getAuthorId()+","+author.getName()).length();
		stringBuffer.delete(start, end);
		// write to file
		writeToFile(path, stringBuffer.toString());
		
		return "Deleted "+author.getName()+" from the records";
	}

}
