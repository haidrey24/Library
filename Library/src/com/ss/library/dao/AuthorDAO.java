package com.ss.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Author;

public class AuthorDAO extends BaseDAO<Author> {
	
	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_author (authorId, authorName) VALUES (?, ?)",
				new Object[] {author.getId(), author.getName()});
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_author set authorId = ? AND authorName = ?",
				new Object[] {author.getId(), author.getName()});
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] {author.getId(), author.getName()});
	}	
	
	public List<Author> readAuthor() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_author", null);
	}
	
//	public List<Author> readAuthorByAirportCode(String airportCode) throws ClassNotFoundException, SQLException {
//		return read("SELECT * FROM route WHERE origin_id = ? OR destination_id = ?", 
//						new Object[] { airportCode, airportCode );
//	}

	@Override
	protected List<Author> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Author> authors = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			Author author = new Author();
			author.setId(rs.getInt("authorId"));
			author.setName(rs.getString("authorName"));
			authors.add(author);
			
		}
		return authors;
	}
}
