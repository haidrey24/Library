/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.BookGenres;

/**
 * @author brucehaidrey
 *
 */
public class BookGenresDAO extends BaseDAO<BookGenres> {

	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public BookGenresDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to append a BookGenre to the end of the book_genre table
	 * @param bg - BookGenre to append
	 */
	public void addBookGenre(BookGenres bg) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_genres (genre_id, bookId) VALUES (?, ?)",
				new Object[] {bg.getGenreId(), bg.getBookId()});
	}
	
	/*
	 * Used to append a BookGenre to the end of the book_genre table
	 * @param bg - BookGenre to append
	 */
	public void appendBookGenre(BookGenres bg) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the book_genres table
		save("SET @max_id = (SELECT MAX(genre_id) FROM 'tbl_book_genres')", null);
		// pass the previous value into insert and retrieve the info
		save("INSERT INTO tbl_book_genres (genre_id, bookId) VALUES (@max_id + 1, ?)",
				new Object[] {bg.getBookId()});
	}

	/*
	 * Used to update the BookGenre in the book_genre table
	 * @param bg - BookGenre to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public Integer updateBookGenre(BookGenres bg) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_book_genres SET bookId = ? WHERE genre_id = ?",
				new Object[] {bg.getBookId(), bg.getGenreId()});
	}
	
	/*
	 * Used to delete the BookGenre from the table
	 * @param bg - BookGenre to delete
	 */
	public void deleteAuthor(BookGenres bg) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_genres WHERE genre_id = ?", new Object[] {bg.getGenreId(), bg.getBookId()});
	}	
	
	/*
	 * Return the table of BookGenres in a list format
	 */
	public List<BookGenres> readAuthor() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_genres", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
	@Override
	protected List<BookGenres> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookGenres> bgList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			BookGenres bg = new BookGenres();
			bg.getGenreId().setGenreId(rs.getInt("genre_id"));
			bg.getBookId().setBookId(rs.getInt("bookId"));
			bgList.add(bg);
		}
		return bgList;
	}
}
