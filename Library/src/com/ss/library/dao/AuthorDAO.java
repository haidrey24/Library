package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Author;

public class AuthorDAO extends BaseDAO<Author> {
	
	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public AuthorDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to add an author to the author table
	 * @param author - author to add
	 */
	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_author (authorId, authorName) VALUES (?, ?)",
				new Object[] {author.getAuthorId(), author.getAuthorName()});
	}
	
	/*
	 * Used to append an author to the end of the author table
	 * @param author - author to append
	 */
	public void appendAuthor(Author author) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the author table
		save("SET @max_id = (SELECT MAX(authorId) FROM 'tbl_author')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_author (authorId, authorName) VALUES (@max_id + 1, ?)",
				new Object[] {author.getAuthorName()});
	}

	/*
	 * Used to update the author in the author table
	 * @param author - author to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public Integer updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_author SET authorName = ? WHERE authorId = ?",
				new Object[] {author.getAuthorId(), author.getAuthorName()});
	}
	
	/*
	 * Used to delete the author from the table
	 * @param author - author to delete
	 */
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] {author.getAuthorId(), author.getAuthorName()});
	}	
	
	/*
	 * Return the table of authors in a list format
	 */
	public List<Author> readAuthor() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_author", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
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
