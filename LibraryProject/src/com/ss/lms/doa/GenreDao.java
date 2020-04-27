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
import com.ss.lms.entity.Genre;

/**
 * @genre seandarsie
 *
 */
public class GenreDao extends BaseDao<Genre>{

	public GenreDao(Connection conn) {
		super(conn);
	}

	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] {genre.getName()});
	}

	public void updateGenre(Genre genre)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_genre SET genre_name = ? WHERE genreId = ?", new Object[] {genre.getName(), genre.getGenreId()});
	}

	public void deleteGenre(Genre genre)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_genre WHERE genreId = ?", new Object[]{genre.getGenreId()});
	}
	
	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_genre", null);
	}
	
	public List<Genre> listGenreByBook(Book book) throws ClassNotFoundException, SQLException
	{
		return read("SELECT genre_name FROM tbl_genre\n" + 
				"INNER JOIN tbl_book_genres\n" + 
				"ON tbl_book_genres.genre_id = tbl_genre.genre_id\n" + 
				"INNER JOIN tbl_book\n" + 
				"ON tbl_book.bookId = tbl_book_genres.bookId\n" + 
				"WHERE tbl_book.bookId = 1;",new Object[] {book.getBookId()});
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre genre = new Genre(rs.getInt("genreId"),rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

}
