/**
 * 
 */
package com.ss.lms.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.BookLoan;

/**
 * @bookLoan seandarsie
 *
 */
public class BookLoanDao extends BaseDao<BookLoan>{

	public BookLoanDao(Connection conn) {
		super(conn);
	}

	public void addBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo,dateOut, dueDate, dateIn) VALUES (?,?,?,?,?,?)", new Object[] {bookLoan.getBookId(),bookLoan.getBranchId(), bookLoan.getCardNo(),bookLoan.getDateOut(),bookLoan.getDateDue(),null});
	}

	public void updateBookLoan(BookLoan bookLoan)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_book_loans SET dateIn = ? WHERE bookId = ? AND branchId = ?, AND cardNo = ? AND dateIn = ?", new Object[] {bookLoan.getDateIn(), bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo()});
	}

	public void deleteBookLoan(BookLoan bookLoan)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_book_loans WHERE bookId = ? AND cardNo = ?, and branchId = ? AND dateIn = ?", new Object[]{bookLoan.getBookId(),bookLoan.getCardNo(),bookLoan.getBranchId(),bookLoan.getDateOut()});
	}
	
	public List<BookLoan> readAllBookLoanes() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_book_loans", null);
	}
	
	public void returnBook(BookLoan bookLoan) throws ClassNotFoundException, SQLException
	{
		save("UPDATE tbl_book_copies SET noOfCopies = noOfCopies + 1\n" + 
				"WHERE bookId = ? AND branchId = ?;", new Object[] {bookLoan.getBookId(),bookLoan.getBranchId()});
	}
	
	public void takeOutABook(BookLoan bookLoan) throws ClassNotFoundException, SQLException
	{
		save("UPDATE tbl_book_copies SET noOfCopies = noOfCopies - 1\n" + 
				"WHERE noOfCopies > 0 AND bookId = ? AND branchId = ?;", new Object[] {bookLoan.getBookId(),bookLoan.getBranchId()});
	}
	
	public void giveLoanExtension(BookLoan loan) throws ClassNotFoundException, SQLException
	{
		save("UPDATE tbl_book_loans\n" + 
				"INNER JOIN tbl_library_branch\n" + 
				"ON tbl_library_branch.branchId = tbl_book_loans.branchId\n" + 
				"INNER JOIN tbl_borrower\n" + 
				"ON tbl_borrower.cardNo = tbl_book_loans.cardNo\n" + 
				"SET dueDate = ?\n" + 
				"WHERE tbl_borrower.cardNo = ? AND tbl_library_branch.branchId = ? AND dateIn = ?; ", new Object[] {loan.getDateDue(),loan.getCardNo(),loan.getBranchId(),loan.getDateOut()});
	}
	
	public List<BookLoan> getLoansByUserId(int cardNo) throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_book_loans WHERE cardNo = ? AND dateIn IS NULL;",new Object[] {cardNo});
	}

	@Override
	public List<BookLoan> extractData(ResultSet rs) throws SQLException {
		List<BookLoan> bookLoans = new ArrayList<>();
		while(rs.next()){
			BookLoan bookLoan = new BookLoan(rs.getInt("branchId"),
												rs.getInt("bookId"),
												rs.getInt("cardNo"),
												rs.getObject("dateOut", LocalDateTime.class),
												rs.getObject("dueDate", LocalDateTime.class),
												rs.getObject("dateIn", LocalDateTime.class));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

}
