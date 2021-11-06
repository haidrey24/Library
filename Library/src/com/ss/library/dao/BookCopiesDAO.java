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
	
	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public BookCopiesDAO(Connection conn) {
		super(conn);
	}
	
	/*
	 * Used to add an BookCopy to the book_copies table
	 * @param bc - BookCopy to add
	 */
	public void addBookCopy(BookCopies bc) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?, ?, ?)",
				new Object[] {bc.getBookId(), bc.getBranchId(), bc.getNumCopies()});
	}
	
	/*
	 * Used to append an BookCopy to the end of the book_copies table
	 * @param bc - BookCopy to append
	 */
	public void appendAuthor(BookCopies bc) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the book_copies table
		save("SET @max_id = (SELECT MAX(bookId) FROM 'tbl_book_copies')", null);
		// pass the previous value into insert and retrieve the info
		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (@max_id + 1, ?, ?)",
				new Object[] {bc.getBranchId(), bc.getNumCopies()});
	}

	/*
	 * Used to update the BookCopy in the book_copies table
	 * @param bc - BookCopy to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public Integer updateAuthor(BookCopies bc) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_book_copies SET branchId = ? and noOfCopies = ? WHERE bookId = ?",
				new Object[] {bc.getBranchId(), bc.getNumCopies(), bc.getBookId()});
	}
	
	/*
	 * Used to delete the BookCopy from the table
	 * @param bc - BookCopy to delete
	 */
	public void deleteAuthor(BookCopies bc) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_copies WHERE bookId = ?", new Object[] {bc.getBookId(), bc.getBranchId(), bc.getNumCopies()});
	}	
	
	/*
	 * Return the table of BookCopies in a list format
	 */
	public List<BookCopies> readAuthor() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_copies", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
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
