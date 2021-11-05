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
	
	public GenreDAO(Connection conn) {
		super(conn);
	}

	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_genre (genreId, genreName) VALUES (?, ?)",
				new Object[] {genre.getGenreId(), genre.getGenreName()});
	}
	
	public void appendGenre(Genre genre) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the genre table
		save("SET @max_id = (SELECT MAX(genreId) FROM 'tbl_genre')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_genre (genreId, genreName) VALUES (@max_id + 1, ?)",
				new Object[] {genre.getGenreName()});
	}

	public Integer updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_genre SET genreId = ? AND genreName = ?",
				new Object[] {genre.getGenreId(), genre.getGenreName()});
	}
	
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_genre WHERE genreId = ?", new Object[] {genre.getGenreId(), genre.getGenreName()});
	}	
	
	public List<Genre> readGenre() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_genre", null);
	}

	@Override
	protected List<Genre> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Genre> gList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genreId"));
			genre.setGenreName(rs.getString("genreName"));
			gList.add(genre);
		}
		return gList;
	}
}
