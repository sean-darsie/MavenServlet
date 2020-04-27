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
 * @branch seandarsie
 *
 */
public class BranchDao extends BaseDao<Branch>{

	public BranchDao(Connection conn) {
		super(conn);
	}

	public void addBranch(Branch branch) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?,?)", new Object[] {branch.getName(),branch.getAddress()});
	}

	public void updateBranch(Branch branch)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?", new Object[] {branch.getName(), branch.getAddress(), branch.getBranchId()});
	}

	public void deleteBranch(Branch branch)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[]{branch.getBranchId()});
	}
	
	public void addBooksToBranch(int branch, int bookId, int noOfBooks) throws ClassNotFoundException, SQLException
	{
		save("UPDATE tbl_book_copies\n" + 
				"INNER JOIN tbl_book\n" + 
				"ON tbl_book.bookId = tbl_book_copies.bookId\n" + 
				"INNER JOIN tbl_library_branch\n" + 
				"ON tbl_library_branch.branchId = tbl_book_copies.branchId\n" + 
				"SET noOfCopies = noOfCopies + 5\n" + 
				"WHERE tbl_library_branch.branchId = ? AND tbl_book.bookId = ?; ", new Object[] {noOfBooks,branch,bookId});
	}
	
	public void addNewBookToBranch(int branch, int bookId, int noOfBooks) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_book_copies (bookId,branchId,noOfCopies) VALUES(?,?,?);", new Object[] {bookId,branch,noOfBooks});
	}
	
	public List<Branch> readAllBranches() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_library_branch", null);
	}
	
	public List<Branch> allBranchesThatHaveBooks() throws ClassNotFoundException, SQLException
	{
		return read("SELECT tbl_library_branch.branchId,branchName,branchAddress FROM tbl_library_branch\n" + 
				"INNER JOIN tbl_book_copies\n" + 
				"ON tbl_book_copies.branchId = tbl_library_branch.branchId\n" + 
				"WHERE tbl_book_copies.noOfCopies > 0;", null);
	}
	
	public boolean doesBranchContainBook(int branchId, int bookId) throws ClassNotFoundException, SQLException
	{
		List<Branch> booksOwnedByBranch = read("SELECT tbl_library_branch.branchId, branchName, branchAddress, noOfCopies FROM tbl_library_branch\n" + 
				"INNER JOIN tbl_book_copies\n" + 
				"ON tbl_library_branch.branchId = tbl_book_copies.branchId\n" + 
				"INNER JOIN tbl_book\n" + 
				"ON tbl_book.bookId = tbl_book_copies.bookId\n" + 
				"WHERE tbl_book.bookId = ? AND tbl_library_branch.branchId = ?;", new Object[] {bookId,branchId});
		
		if (booksOwnedByBranch.isEmpty() == true)
			return false;
		return true;
	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
		List<Branch> branchs = new ArrayList<>();
		while(rs.next()){
			Branch branch = new Branch(rs.getInt("branchId"),rs.getString("branchName"),rs.getString("branchAddress"));
			branchs.add(branch);
		}
		return branchs;
	}

}
