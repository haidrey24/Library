/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Book;

/**
 * @author brucehaidrey
 *
 */
public class BookDAO extends BaseDAO<Book> {
	
	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public BookDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to add a book to the book table
	 * @param book - book to add
	 */
	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book (bookId, title, authId, pubId) VALUES (?, ?, ?, ?)",
				new Object[] {book.getBookId(), book.getTitle(), book.getAuthId(), book.getPubId()});
	}
	
	/*
	 * Used to append a book to the end of the book table
	 * @param book - book to append
	 */
	public void appendBook(Book book) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the book table
		save("SET @max_id = (SELECT MAX(bookId) FROM 'tbl_book')", null);
		// pass the previous value into insert and retrieve the info
		save("INSERT INTO tbl_book (bookId, title, authId, pubId) VALUES (@max_id + 1, ?, ?, ?)",
				new Object[] {book.getTitle(), book.getAuthId(), book.getPubId()});
	}

	/*
	 * Used to update the book in the book table
	 * @param book - book to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET title = ?, authId = ?, pubId = ? WHERE bookId = ?",
				new Object[] {book.getTitle(), book.getAuthId(), book.getPubId(), book.getBookId()});
	}
	
	/*
	 * Used to delete the book from the table
	 * @param book - book to delete
	 */
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] {book.getBookId(), book.getTitle(), book.getAuthId(), book.getPubId()});
	}	
	
	/*
	 * Return the table of books in a list format
	 */
	public List<Book> readBook() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
	@Override
	protected List<Book> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Book> bookList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("authorName"));
			book.getAuthId().setAuthorId(rs.getInt("authorId"));
			book.getPubId().setPubId(rs.getInt("pubId"));
			bookList.add(book);
		}
		return bookList;
	}
	
}
