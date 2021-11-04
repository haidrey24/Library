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
	
	public BookDAO(Connection conn) {
		super(conn);
	}

	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book (bookId, title, authId, pubId) VALUES (?, ?, ?, ?)",
				new Object[] {book.getBookId(), book.getTitle(), book.getAuthId(), book.getPubId()});
	}
	
	public void appendBook(Book book) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the author table
		save("SET @max_id = (SELECT MAX(bookId) FROM 'tbl_book')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_book (bookId, title, authId, pubId) VALUES (@max_id + 1, ?, ?, ?)",
				new Object[] {book.getTitle(), book.getAuthId(), book.getPubId()});
	}

	public Integer updateBook(Book book) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_book SET bookId = ? AND title = ? AND authId = ? AND pubId = ?",
				new Object[] {book.getBookId(), book.getTitle(), book.getAuthId(), book.getPubId()});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] {book.getBookId(), book.getTitle(), book.getAuthId(), book.getPubId()});
	}	
	
	public List<Book> readBook() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book", null);
	}

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
