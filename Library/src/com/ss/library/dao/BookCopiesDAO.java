/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.BookCopies;

/**
 * @author brucehaidrey
 *
 */
public class BookCopiesDAO extends BaseDAO<BookCopies> {
	
	public BookCopiesDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookCopy(BookCopies bc) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?, ?, ?)",
				new Object[] {bc.getBookId(), bc.getBranchId(), bc.getNumCopies()});
	}
	
	public void appendAuthor(BookCopies bc) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the author table
		save("SET @max_id = (SELECT MAX(bookId) FROM 'tbl_book_copies')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (@max_id + 1, ?, ?)",
				new Object[] {bc.getBranchId(), bc.getNumCopies()});
	}

	public Integer updateAuthor(BookCopies bc) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_book_copies SET bookId = ? AND branchId = ? and noOfCopies = ?",
				new Object[] {bc.getBookId(), bc.getBranchId(), bc.getNumCopies()});
	}
	
	public void deleteAuthor(BookCopies bc) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_copies WHERE bookId = ?", new Object[] {bc.getBookId(), bc.getBranchId(), bc.getNumCopies()});
	}	
	
	public List<BookCopies> readAuthor() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_copies", null);
	}

	@Override
	protected List<BookCopies> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookCopies> bcList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			BookCopies bc = new BookCopies();
			bc.getBookId().setBookId(rs.getInt("bookId"));
			bc.getBranchId().setBranchId(rs.getInt("branchId"));
			bc.setNumCopies(rs.getInt("noOfCopies"));
			bcList.add(bc);
		}
		return bcList;
	}
}
