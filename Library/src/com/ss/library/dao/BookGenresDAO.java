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

	public BookGenresDAO(Connection conn) {
		super(conn);
	}

	public void addBookGenre(BookGenres bg) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_genres (genreId, bookId) VALUES (?, ?)",
				new Object[] {bg.getGenreId(), bg.getBookId()});
	}
	
	public void appendBookGenre(BookGenres bg) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the author table
		save("SET @max_id = (SELECT MAX(genreId) FROM 'tbl_book_genres')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_book_genres (genreId, bookId) VALUES (@max_id + 1, ?)",
				new Object[] {bg.getBookId()});
	}

	public Integer updateBookGenre(BookGenres bg) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_book_genres SET genreId = ? AND bookId = ?",
				new Object[] {bg.getGenreId(), bg.getBookId()});
	}
	
	public void deleteAuthor(BookGenres bg) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_genres WHERE genreId = ?", new Object[] {bg.getGenreId(), bg.getBookId()});
	}	
	
	public List<BookGenres> readAuthor() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_genres", null);
	}

	@Override
	protected List<BookGenres> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookGenres> bgList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			BookGenres bg = new BookGenres();
			bg.getGenreId().setGenreId(rs.getInt("genreId"));
			bg.getBookId().setBookId(rs.getInt("bookId"));
			bgList.add(bg);
		}
		return bgList;
	}
}
