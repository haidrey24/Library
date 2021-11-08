/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Genre;

/**
 * @genre brucehaidrey
 *
 */
public class GenreDAO extends BaseDAO<Genre> {
	
	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public GenreDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to add an genre to the genre table
	 * @param genre - genre to add
	 */
	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_genre (genre_id, genre_name) VALUES (?, ?)",
				new Object[] {genre.getGenreId(), genre.getGenreName()});
	}
	
	/*
	 * Used to append an genre to the end of the genre table
	 * @param genre - genre to append
	 */
	public void appendGenre(Genre genre) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the genre table
		save("SET @max_id = (SELECT MAX(genre_id) FROM 'tbl_genre')", null);
		// pass the previous value into insert and retrieve the info
		save("INSERT INTO tbl_genre (genre_id, genre_name) VALUES (@max_id + 1, ?)",
				new Object[] {genre.getGenreName()});
	}

	/*
	 * Used to update the genre in the genre table
	 * @param genre - genre to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?",
				new Object[] {genre.getGenreName(), genre.getGenreId()});
	}

	/*
	 * Used to delete the genre from the table
	 * @param genre - genre to delete
	 */
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] {genre.getGenreId(), genre.getGenreName()});
	}	
	
	/*
	 * Return the table of genre in a list format
	 */
	public List<Genre> readGenre() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_genre", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
	@Override
	protected List<Genre> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Genre> gList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			gList.add(genre);
		}
		return gList;
	}
}
