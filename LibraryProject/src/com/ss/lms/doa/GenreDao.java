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
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;

/**
 * @genre seandarsie
 *
 */
public class GenreDao extends BaseDao<Genre>{

	public GenreDao(Connection conn) {
		super(conn);
	}

	/**
	 * 
	 * @param genre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] {genre.getName()});
	}

	/**
	 * 
	 * @param genre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void updateGenre(Genre genre)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?", new Object[] {genre.getName(), genre.getGenreId()});
	}

	/**
	 * 
	 * @param genre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteGenre(Genre genre)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[]{genre.getGenreId()});
	}
	
	/**
	 * 
	 * @param bookId
	 * @param genreId
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void removeRelationBetweenBookAndGenre(int bookId, int genreId) throws ClassNotFoundException, SQLException
	{
		save("DELETE FROM tbl_book_genres WHERE bookId = ? AND genre_id = ?", new Object[] {bookId,genreId});
	}
	
	/**
	 * 
	 * @param bookId
	 * @param genreId
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void addRelationBetweenBookAndGenre(int bookId, int genreId) throws ClassNotFoundException, SQLException
	{
		save("INSERT INTO tbl_book_genres(bookId,genre_id) VALUES(?,?);", new Object[] {bookId, genreId});
	}
	
	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_genre", null);
	}
	
	/**
	 * 
	 * @param genre_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Genre> getGenreById(int genre_id) throws ClassNotFoundException, SQLException
	{
		return read("SELECT * FROM tbl_genre WHERE genre_id = ?", new Object[] {genre_id});
	}
	
	/**
	 * 
	 * @param bookId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Genre> getGenresByBookId(int bookId) throws ClassNotFoundException, SQLException
	{
		return read("SELECT tbl_genre.genre_id, genre_name FROM tbl_genre\n"+
					"INNER JOIN tbl_book_genres\n"+
					"ON tbl_book_genres.bookId = tbl_book.bookId\n"+
					"WHERE bookId = ?;", new Object[] {bookId});
	}

	/**
	 * 
	 * @param book
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
			Genre genre = new Genre(rs.getInt("genre_id"),rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

}
