/**
 * 
 */
package com.ss.lms.doa;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author seandarsie
 *
 */
public abstract class BaseDao<T> {
public Connection conn = null;
	
	public BaseDao(Connection conn){
		this.conn = conn;
	}
	
	public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int index =1;
			for(Object o: vals){
				pstmt.setObject(index, o);
				index++;
			}
		}
		pstmt.executeUpdate();
	}
	
	public List<T> read(String sql, Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null){
			int index =1;
			for(Object o: vals){
				pstmt.setObject(index, o);
				index++;
			}
		}
		return extractData(pstmt.executeQuery());
	}
	
	public abstract List<T> extractData(ResultSet rs) throws SQLException;
}
