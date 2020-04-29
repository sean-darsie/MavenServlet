/**
 * 
 */
package com.ss.lms.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.Branch;

/**
 * @book seandarsie
 *
 */
public class BookDao extends BaseDao<Book>{

	public BookDao(Connection conn) {
		super(conn);
	}

	/**
	 * 
	 * @param book
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addBook(Book book) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_book (title,pubId) VALUES (?,?)", new Object[] {book.getName(),book.getPubId()});
	}

	/**
	 * 
	 * @param book
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateBook(Book book)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_book SET title = ?,pubId = ?, WHERE bookId = ?", new Object[] {book.getName(), book.getPubId(), book.getBookId()});
	}

	/**
	 * 
	 * @param book
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteBook(Book book)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[]{book.getBookId()});
	}
	
	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_book", null);
	}
	
	/**
	 * 
	 * @param bookId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Book> getBookById(int bookId) throws ClassNotFoundException, SQLException
	{
		return read("SELECT tbl_book.bookId, title from tbl_book WHERE bookid = ?",new Object[] {bookId});
	}
	
	/**
	 * 
	 * @param authorId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Book> getBooksByAuthorId(int authorId) throws ClassNotFoundException, SQLException
	{
		return read("SELECT tbl_book.bookId, title from tbl_book\n" + 
				"INNER JOIN tbl_book_authors\n" + 
				"ON tbl_book_authors.bookId = tbl_book.bookId\n" + 
				"WHERE authorId = ?;", new Object[] {authorId});
	}
	
	/**
	 * 
	 * @param genreId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Book> getBooksByGenreId(int genreId) throws ClassNotFoundException, SQLException
	{
		return read("SELECT tbl_book.bookId, title from tbl_book\n" + 
				"INNER JOIN tbl_book_genres\n" + 
				"ON tbl_book_genres.bookId = tbl_book.bookId\n" + 
				"WHERE genre_id = ?;", new Object[] {genreId});
	}
	
	/**
	 * 
	 * @param branchId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Book> getAvailableBooksFromBranch(int branchId) throws ClassNotFoundException, SQLException
	{
		return read("SELECT tbl_book.bookId,title From tbl_book\n" + 
				"INNER JOIN tbl_book_copies\n" + 
				"ON tbl_book_copies.bookId = tbl_book.bookId\n" + 
				"INNER JOIN tbl_library_branch\n" + 
				"on tbl_book_copies.branchId = tbl_library_branch.branchId\n" + 
				"WHERE tbl_library_branch.branchId = ? AND tbl_book_copies.noOfCopies > 0;", new Object[] {branchId});
	}
	
	/**
	 * 
	 * @param bookId
	 * @param authorId
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void removeRelationBetweenBookAndAuthor(int bookId, int authorId) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_book_authors WHERE bookId = ? AND authorId = ?;", new Object[] {bookId,authorId});
	}
	
	/**
	 * 
	 * @param bookId
	 * @param genreId
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void removeRelationBetweenBookAndGenre(int bookId, int genreId) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_book_genres WHERE bookId = ? AND genre_id = ?;", new Object[] {bookId,genreId});
	}
	
	/**
	 * 
	 * @param bookId
	 * @param authorId
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void addRelationBetweenBookAndAuthor(int bookId, int authorId) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_book_authors(bookId,authorId) VALUES(?,?);", new Object[] {bookId, authorId});
	}
	
	/**
	 * 
	 * @param bookId
	 * @param genreId
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void addRelationBetweenBookAndGenre(int bookId, int genreId) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_book_genres(genre_id,bookId) VALUES(?,?);", new Object[] {genreId, bookId});
	}
	
	public List<Book> returnLastBookAdded() throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_book ORDER BY bookId DESC LIMIT 1;",null);
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			System.out.println("bookdao");

			Book book = new Book(rs.getInt("bookId"),rs.getString("title"), null, null);
			books.add(book);
		}
		return books;
	}

}
