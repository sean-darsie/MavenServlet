/**
 * 
 */
package com.smoothstack.training.libraryproject;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @book seandarsie
 *
 */
public class BookService implements Reader,Writer{
	
	private static final BookService instance = new BookService();
	private int numberOfRecords = 1;
    
    //private constructor to avoid client applications to use constructor
    private BookService(){}

    public static BookService getInstance(){
        return instance;
    }
    
    /**
     * Attempts to create a record in the database with the book provided
     * @param book
     * @return
     */
	public String createBook(Book book)
	{
		if (book == null)
			return "Book cannot be null";
		if (book.getName().length() < 2 || book.getName().length() > 45)
			return "Book name must be between 2 and 45 characters in length";
		
		book.setBookId(numberOfRecords++);
		String allBooks = readFromFile("resources/libraryfiles/books.txt");
		if (allBooks.contains(","+book.getName()+","))
		{
			return "Book named " + book.getName()+ " already exists";
		}
		if (allBooks.length() == 0)
		{
			writeToFile("resources/libraryfiles/books.txt",book.recordInfo());
		}
		else
		{
			appendToFile("resources/libraryfiles/books.txt","\n"+book.recordInfo());
		}
		return "Successfully added "+book.getName();
	}
	
	/**
	 * Reads and returns a formatted list of all the books in the database
	 * @return
	 */
	public String readBook()
	{
		StringBuilder stringBuilder = new StringBuilder();
		try(BufferedReader bufStream = new BufferedReader(new FileReader("resources/libraryfiles/books.txt"))){
			String line = bufStream.readLine();
			while(line!=null){
				Book book = new Book(1, "placeholder","placeholdler","placeholder");
				if (line.contains(","))
				{
					String[] records = line.split(",");
					book.setName(records[1]);
					book.setAuthor(records[2]);
					book.setPublisher(records[3]);
					book.setBookId(Integer.parseInt(records[0]));
				}
				else
				{
					line = bufStream.readLine();
					continue;
				}
				stringBuilder.append(book.displayInfo()+"\n");
				line = bufStream.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read from book file in readbook");
		}
		return stringBuilder.toString();

	}
	
	/**
	 * 
	 * @param oldBook
	 * @param newBook
	 * @return the result of attempting to update the book specified
	 */
	public String updateBook(Book oldBook, Book newBook)
	{
		if (oldBook == null || newBook == null)
			return "Book cannot be null";
		if (newBook.getName().length() < 2 || newBook.getName().length() > 45)
			return "Book name must be between 2 and 45 characters in length";
		
		String allBooks = readFromFile("resources/libraryfiles/books.txt");
		if (allBooks.contains(","+oldBook.getName()+",") != true)
		{
			return "There is no record of "+oldBook.getName();
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		try(BufferedReader bufStream = new BufferedReader(new FileReader("resources/libraryfiles/books.txt"))){
			String line = bufStream.readLine();
			while(line!=null){
				Book book = new Book(1, "placeholder","placeholder","placeholder");
				if (line.contains(","))
				{
					String[] records = line.split(",");
					if (records[1] == oldBook.getName())
					{
						book.setName(newBook.getName());
						book.setAuthor(newBook.getAuthor());
						book.setPublisher(newBook.getPublisher());
					}
					else 
					{
						book.setName(records[1]);
						book.setAuthor(records[2]);
						book.setPublisher(records[3]);
					}
					book.setBookId(Integer.parseInt(records[0]));
				}
				else
				{
					line = bufStream.readLine();
					continue;
				}
				stringBuilder.append(book.recordInfo()+"\n");
				line = bufStream.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to read from book file in update book");
		}
		writeToFile("resources/libraryfiles/books.txt",stringBuilder.toString());
		return "Success. Replaced "+oldBook.getName()+" with "+newBook.getName();
	}
	
	/**
	 * 
	 * @param book
	 * @return the result of trying to delete the book from the database
	 */
	public String deleteBook(Book book)
	{
		if (book == null)
			return "Book cannot be null";
		
		String allBooks = readFromFile("resources/libraryfiles/books.txt");
		if (allBooks.contains(book.getName()) != true)
		{
			return "There is no record of "+book.getName();
		}
		StringBuffer stringBuffer = new StringBuffer(allBooks);
		String toDelete = book.getBookId()+","+book.getName()+","+book.getAuthor()+","+book.getPublisher();
		int start = stringBuffer.indexOf(toDelete);
		int end = start + toDelete.length();
		stringBuffer.delete(start, end);
		// write to file
		writeToFile("resources/libraryfiles/books.txt", stringBuffer.toString());
		
		return "Deleted "+book.getName()+" from the records";
	}

}
