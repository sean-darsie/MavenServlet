/**
 * 
 */
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.ss.lms.doa.AuthorDao;
import com.ss.lms.entity.Author;

/**
 * @author seandarsie
 *
 */
public class AdminService {
public ConnectionUtil connUtil = new ConnectionUtil();
	
	public void saveAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			if(author.getName()!=null){
				adao.updateAuthor(author);
			}else if(author.getAuthorId()!=0){
				adao.deleteAuthor(author);
			}else{
				adao.addAuthor(author);
			}
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
