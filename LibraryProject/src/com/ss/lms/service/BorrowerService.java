/**
 * 
 */
package com.ss.lms.service;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.ss.lms.doa.AuthorDao;
import com.ss.lms.doa.BookDao;
import com.ss.lms.doa.BookLoanDao;
import com.ss.lms.doa.BorrowerDao;
import com.ss.lms.doa.BranchDao;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;

/**
 * @author seandarsie
 *
 */
public class BorrowerService {

	public ConnectionUtil connUtil = new ConnectionUtil();

	public boolean validateUser(Integer cardNo) throws SQLException
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			BorrowerDao bdao = new BorrowerDao(conn);
			List<Borrower> borrowers = bdao.readSingleBorrower(cardNo);
			if (borrowers.isEmpty())
			{
				return false;
			}
			else
			{
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("problem in validate single user");
			
		} finally {
			if(conn!=null){
				conn.close();
			}
		}
		return false;
	}
	
	
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
	
	public void createBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		
		try {
			conn = connUtil.getConnection();
			BorrowerDao bdao = new BorrowerDao(conn);
			bdao.addBorrower(borrower);
		
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void  returnABook(BookLoan loan)
	{
		Connection conn = null;
		
		try {
			conn = connUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			bldao.returnBook(loan);
		
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
	
	public void showAvailableBooks(int branchId)
	{
		Connection conn = null;
		
		try {
			conn = connUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			
			List<Book> availableBooks = bdao.getAvailableBooksFromBranch(branchId);
			for (Book b: availableBooks)
			{
				System.out.println(b.getBookId()+") "+b.getName());
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("problem in showAvailableBooks");
			e.printStackTrace();
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
	
	/**
	 * Add a book loan to the book loan table. reduce the number of books available in the library branch by 1
	 * @param bookId
	 * @param branchId
	 * @throws SQLException 
	 */
	public void checkOutABook(int bookId, int branchId, int cardNo)
	{
		Connection conn = null;
//		Date today = Date.from(Instant.now());
//		LocalDateTime dayOut = LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault());
//		LocalDateTime dueDate = dayOut.plusDays(7);
//		ZonedDateTime zdt = dueDate.atZone(ZoneId.systemDefault());
//		Date output = Date.from(zdt.toInstant());
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime output = today.plusDays(7);
		
		BookLoan loan = new BookLoan(branchId, bookId, cardNo, today, output, null);
		
		try {
			conn = connUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			bldao.addBookLoan(loan);
			
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("problem in showAvailableBooks");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
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
	
	public BookLoan[] getLoansByUser(int cardNo)
	{
		Connection conn = null;

		try {
			conn = connUtil.getConnection();
			BookLoanDao bdao = new BookLoanDao(conn);
			List<BookLoan> loans = bdao.getLoansByUserId(cardNo);
			if (loans.isEmpty())
			{
				return null;
			}
			else
			{
				BookLoan[] loanArray = new BookLoan[loans.size()];
				for (int i = 0; i < loanArray.length; i++)
					loanArray[i] = loans.get(i);
				return loanArray;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("problem in get loans by user");
			
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
