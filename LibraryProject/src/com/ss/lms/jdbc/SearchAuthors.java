/**
 * 
 */
package com.ss.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author seandarsie
 *
 */
public class SearchAuthors {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public static final String user = "root";
	public static final String password  = "WantoCode#15";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter author name to search: ");
		String authorName = scan.nextLine();
		//1. Register driver.
		Class.forName(driver);
		//2. Create a connection
		Connection conn = DriverManager.getConnection(url, user, password);
		//3. Create a statement Object
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tbl_author WHERE authorName = ?");// AND authorId = ?");
		pstmt.setString(1, authorName);
//		pstmt.setInt(2, 44);
		//4. Execute
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){
			System.out.println("Author ID: "+rs.getInt("authorId"));
			System.out.println("Author Name: "+rs.getString("authorName"));
			System.out.println("----------------");
		}
	}

}
