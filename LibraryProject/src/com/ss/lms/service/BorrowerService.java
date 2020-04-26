/**
 * 
 */
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.doa.AuthorDao;
import com.ss.lms.doa.BookLoanDao;
import com.ss.lms.doa.BorrowerDao;
import com.ss.lms.doa.BranchDao;
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
	
	
	public List<Branch> ListAllBranches() throws SQLException
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
				conn.close();
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
	
	public void  returnABook(BookLoan loan) throws SQLException
	{
		Connection conn = null;
		
		try {
			conn = connUtil.getConnection();
			BookLoanDao bldao = new BookLoanDao(conn);
			bldao.addBookLoan(loan);
		
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
}
