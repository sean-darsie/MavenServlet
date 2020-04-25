/**
 * 
 */
package com.ss.lms.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		save("INSERT INTO tbl_branch (branchName, branchAddress) VALUES (?,?)", new Object[] {branch.getName(),branch.getAddress()});
	}

	public void updateBranch(Branch branch)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?", new Object[] {branch.getName(), branch.getAddress(), branch.getBranchId()});
	}

	public void deleteBranch(Branch branch)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_branch WHERE branchId = ?", new Object[]{branch.getBranchId()});
	}
	
	public List<Branch> readAllBranchs() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_branch", null);
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
