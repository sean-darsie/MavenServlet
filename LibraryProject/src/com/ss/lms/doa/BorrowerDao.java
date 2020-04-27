/**
 * 
 */
package com.ss.lms.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;

/**
 * @borrower seandarsie
 *
 */
public class BorrowerDao extends BaseDao<Borrower>{

	public BorrowerDao(Connection conn) {
		super(conn);
	}

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_borrower (name,address,phone) VALUES (?,?,?)", new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone()});
	}

	public void updateBorrower(Borrower borrower)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_borrower SET name = ?,address = ?, phone = ? WHERE cardNo = ?", new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
	}

	public void deleteBorrower(Borrower borrower)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_borrower WHERE cardnNo = ?", new Object[]{borrower.getCardNo()});
	}
	
	public List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_borrower", null);
	}
	
	public List<Borrower> readSingleBorrower(int cardNo) throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{cardNo});
	}
	
	public List<Borrower> getBorrowerById(int cardNo) throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_borrower WHERE borrowerId = ?", new Object[] {cardNo});
	}
	

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower borrower = new Borrower(rs.getInt("cardNo"),rs.getString("name"),rs.getString("address"),rs.getString("phone"));
			borrowers.add(borrower);
		}
		return borrowers;
	}

}
