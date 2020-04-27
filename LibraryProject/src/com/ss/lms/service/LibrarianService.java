/**
 * 
 */
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.doa.BookDao;
import com.ss.lms.doa.BranchDao;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Branch;

/**
 * @author seandarsie
 *
 */
public class LibrarianService {
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public List<Branch> ListAllBranches()
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			BranchDao bdao = new BranchDao(conn);
			List<Branch> branches = bdao.readAllBranches();
			if (branches.isEmpty())
			{
				return null;
			}
			else
			{
				return branches;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("problem in list all branches");
			
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
	
	public void changeLibraryDetails(int branch, String name, String address)
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDao bdao = new BranchDao(conn);
			bdao.updateBranch(new Branch(branch, name, address));
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("problem in list all branches");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	}
	
	public void readAllBooks()
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			List<Book> allBooks = bdao.readAllBooks();
			for (Book b: allBooks)
			{
				System.out.println("bookId: " + b.getBookId()+" title: " +b.getName());
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("problem in librarian service readallbooks");
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
	}
	
	public void addBooksToBranch(int branch, int bookId, int bookCount)
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDao bdao = new BranchDao(conn);
			if (bdao.doesBranchContainBook(branch, bookId) == true)
			{
				bdao.addBooksToBranch(branch,bookId,bookCount);
			}
			else
			{
				bdao.addNewBookToBranch(branch, bookId, bookCount);
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("problem in list all branches");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	}
	
}
