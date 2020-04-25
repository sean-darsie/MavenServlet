/**
 * 
 */
package com.ss.lms.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Author;

/**
 * @author seandarsie
 *
 */
public class AuthorDao extends BaseDao<Author>{

	public AuthorDao(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] {author.getName()});
	}

	public void updateAuthor(Author author)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?", new Object[] {author.getName(), author.getAuthorId()});
	}

	public void deleteAuthor(Author author)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[]{author.getAuthorId()});
	}
	
	public List<Author> readAllAuthors() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_author", null);
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author author = new Author(rs.getInt("authorId"),rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}

}
