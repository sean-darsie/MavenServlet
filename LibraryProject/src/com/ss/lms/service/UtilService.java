/**
 * 
 */
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.doa.AuthorDao;
import com.ss.lms.doa.BookDao;
import com.ss.lms.doa.BorrowerDao;
import com.ss.lms.doa.BranchDao;
import com.ss.lms.doa.GenreDao;
import com.ss.lms.doa.PublisherDao;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Publisher;

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
	
	public Author getAuthorById(int authorId)
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			List<Author> author = adao.getAuthorById(authorId);
			if (author.isEmpty())
			{
				return null;
			}
			else
			{
				return (Author) author.toArray()[0];
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
	
	public Publisher getPublisherById(int publisherId)
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			PublisherDao adao = new PublisherDao(conn);
			List<Publisher> publisher = adao.getPublisherById(publisherId);
			if (publisher.isEmpty())
			{
				return null;
			}
			else
			{
				return (Publisher) publisher.toArray()[0];
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
	
	public Branch getBranchById(int branchId)
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			BranchDao adao = new BranchDao(conn);
			List<Branch> branch = adao.getBranchById(branchId);
			if (branch.isEmpty())
			{
				return null;
			}
			else
			{
				return (Branch) branch.toArray()[0];
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
	
	public Genre getGenreById(int genreId)
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			GenreDao adao = new GenreDao(conn);
			List<Genre> genre = adao.getGenreById(genreId);
			if (genre.isEmpty())
			{
				return null;
			}
			else
			{
				return (Genre) genre.toArray()[0];
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
	
	public Borrower getBorrowerById(int borrowerId)
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			BorrowerDao adao = new BorrowerDao(conn);
			List<Borrower> borrower = adao.getBorrowerById(borrowerId);
			if (borrower.isEmpty())
			{
				return null;
			}
			else
			{
				return (Borrower) borrower.toArray()[0];
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
