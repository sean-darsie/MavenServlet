/**
 * 
 */
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.doa.BookDao;
import com.ss.lms.doa.BorrowerDao;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Borrower;

/**
 * @author seandarsie
 *
 */
public class UtilService {
	public ConnectionUtil connUtil = new ConnectionUtil();
	
	public Book getBookById(int bookId)
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			List<Book> book = bdao.getBookById(bookId);
			if (book.isEmpty())
			{
				return null;
			}
			else
			{
				return (Book) book.toArray()[0];
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("problem in validate single user");
			
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}

}
