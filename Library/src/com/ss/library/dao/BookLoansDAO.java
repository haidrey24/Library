/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.BookLoans;

/**
 * @author brucehaidrey
 *
 */
public class BookLoansDAO extends BaseDAO<BookLoans>{
	
	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public BookLoansDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to add a BookLoan to the book_loans table
	 * @param bl - BookLoan to add
	 */
	public void addBookLoan(BookLoans bl) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (?, ?, ?, ?, ?)",
				new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate()});
	}
	
	/*
	 * Used to append a BookLoan to the end of the book_loans table
	 * @param bl - BookLoan to append
	 */
	public void appendBookLoan(BookLoans bl) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the book_loans table
		save("SET @max_id = (SELECT MAX(authorId) FROM 'tbl_book_loans')", null);
		// pass the previous value into insert and retrieve info
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (@max_id + 1, ?, ?, ?, ?)",
				new Object[] {bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate()});
	}

	/*
	 * Used to update the BookLoan in the book_loans table
	 * @param bl - BookLoan to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public void updateBookLoan(BookLoans bl) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_loans SET branchId = ?, cardNo = ?, dateOut = ?, dueDate = ? WHERE bookId = ?",
				new Object[] {bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate(), bl.getBookId()});
	}
	
	/*
	 * Used to delete the BookLoan from the table
	 * @param bl - BookLoan to delete
	 */
	public void deleteBookLoan(BookLoans bl) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_loans WHERE bookId = ?", new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate()});
	}	
	
	/*
	 * Return the table of BookLoans in a list format
	 */
	public List<BookLoans> readBookLoans() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_loans", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
	@Override
	protected List<BookLoans> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookLoans> blList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			BookLoans bl = new BookLoans();
			bl.getBookId().setBookId(rs.getInt("bookId"));
			bl.getBranchId().setBranchId(rs.getInt("branchId"));
			bl.getCardNo().setCardNo(rs.getInt("cardNo"));
			bl.setDateOut(rs.getTimestamp("dateOut"));
			bl.setDueDate(rs.getTimestamp("dueDate"));
			blList.add(bl);
		}
		return blList;
	}
}
