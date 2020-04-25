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

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while(rs.next()){
			Book book = new Book(rs.getInt("bookId"),rs.getString("bookName"), null, null);
			books.add(book);
		}
		return books;
	}

}
