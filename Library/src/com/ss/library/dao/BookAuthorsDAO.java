package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.ss.library.entity.BookAuthors;

public class BookAuthorsDAO extends BaseDAO<BookAuthors> {
	
	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public BookAuthorsDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to add an BookAuthor to the book_author table
	 * @param bookAuthors - BookAuthor to add
	 */
	public void addBookAuthors(BookAuthors bookAuthors) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl__book_authors (bookId, authorId) VALUES (?, ?)",
				new Object[] {bookAuthors.getBookId(), bookAuthors.getAuthorId()});
	}
	
	/*
	 * Used to append a BookAuthor to the end of the book_author table
	 * @param bookAuthors - bookAuthors to append
	 */
	public void appendBookAuthors(BookAuthors bookAuthors) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the book_authors table
		save("SET @max_id = (SELECT MAX(bookId) FROM 'tbl_book_authors')", null);
		// pass the previous value into insert and retrieve the info
		save("INSERT INTO tbl_book_authors (bookId, authorId) VALUES (@max_id + 1, ?)",
				new Object[] {bookAuthors.getAuthorId()});
	}

	/*
	 * Used to update the bookAuthors in the book_author table
	 * @param bookAuthors - bookAuthors to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public Integer updateBookAuthors(BookAuthors bookAuthors) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_book_authors SET authorId = ? WHERE bookId = ?",
				new Object[] {bookAuthors.getBookId(), bookAuthors.getAuthorId()});
	}
	
	/*
	 * Used to delete the bookAuthors from the table
	 * @param bookAuthors - bookAuthors to delete
	 */
	public void deleteBookAuthors(BookAuthors bookAuthors) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_authors WHERE bookId = ? and authorId = ?", new Object[] {bookAuthors.getBookId(), bookAuthors.getAuthorId()});
	}	
	
	/*
	 * Return the table of BookAuthors in a list format
	 */
	public List<BookAuthors> readBookAuthors() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_authors", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
	@Override
	protected List<BookAuthors> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookAuthors> baList = new ArrayList<>();
		
		/*
		 * can't generalize between DAO's, each one is specific
		 * getAuthorId retrieves the author id from the bookAuthors class
		 * setAuthorId is called on the getAuthorId from the Author class
		 * the methods are not both coming from bookAuthors
		 */
		while (rs.next()) {
			BookAuthors bookAuthors = new BookAuthors();
			bookAuthors.getBookId().setBookId(rs.getInt("bookId"));
			bookAuthors.getAuthorId().setAuthorId(rs.getInt("authorId"));
			baList.add(bookAuthors);
		}
		return baList;
	}
	
}
