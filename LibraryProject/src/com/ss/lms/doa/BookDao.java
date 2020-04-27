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

	public void addBook(Book book) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_book (title,pubId) VALUES (?,?)", new Object[] {book.getName()});
	}

	public void updateBook(Book book)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_book SET title = ?,pubId = ?, WHERE bookId = ?", new Object[] {book.getName(), book.getPubId(), book.getBookId()});
	}

	public void deleteBook(Book book)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[]{book.getBookId()});
	}
	
	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_book", null);
	}
	
	public List<Book> getBookById(int bookId) throws ClassNotFoundException, SQLException
	{
		return read("SELECT tbl_book.bookId, title from tbl_book WHERE bookid = ?",new Object[] {bookId});
	}
	
	public List<Book> getAvailableBooksFromBranch(int branchId) throws ClassNotFoundException, SQLException
	{
		return read("SELECT tbl_book.bookId,title From tbl_book\n" + 
				"INNER JOIN tbl_book_copies\n" + 
				"ON tbl_book_copies.bookId = tbl_book.bookId\n" + 
				"INNER JOIN tbl_library_branch\n" + 
				"on tbl_book_copies.branchId = tbl_library_branch.branchId\n" + 
				"WHERE tbl_library_branch.branchId = ? AND tbl_book_copies.noOfCopies > 0;", new Object[] {branchId});
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
