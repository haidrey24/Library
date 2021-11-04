package com.ss.library.dao;

import java.sql.Connection;
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
				new Object[] {author.getAuthorId(), author.getAuthorName()});
	}
	
	public void appendAuthor(Author author) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the author table
		save("SET @max_id = (SELECT MAX(authorId) FROM 'tbl_author')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_author (authorId, authorName) VALUES (@max_id + 1, ?)",
				new Object[] {author.getAuthorName()});
	}

	public Integer updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_author SET authorId = ? AND authorName = ?",
				new Object[] {author.getAuthorId(), author.getAuthorName()});
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] {author.getAuthorId(), author.getAuthorName()});
	}	
	
	public List<Author> readAuthor() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_author", null);
	}

	@Override
	protected List<Author> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Author> authors = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}
}
