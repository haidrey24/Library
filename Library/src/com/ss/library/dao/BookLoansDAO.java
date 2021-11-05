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
	
	public BookLoansDAO(Connection conn) {
		super(conn);
	}

	public void addBookLoan(BookLoans bl) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (?, ?, ?, ?, ?)",
				new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate()});
	}
	
	public void appendBookLoan(BookLoans bl) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the author table
		save("SET @max_id = (SELECT MAX(authorId) FROM 'tbl_book_loans')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (@max_id + 1, ?, ?, ?, ?)",
				new Object[] {bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate()});
	}

	public Integer updateBookLoan(BookLoans bl) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_book_loans SET bookId = ? AND branchId = ? AND cardNo = ? AND dateOut = ? AND dueDate = ?",
				new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate()});
	}
	
	public void deleteBookLoan(BookLoans bl) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book_loans WHERE bookId = ?", new Object[] {bl.getBookId(), bl.getBranchId(), bl.getCardNo(), bl.getDateOut(), bl.getDueDate()});
	}	
	
	public List<BookLoans> readBookLoans() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_book_loans", null);
	}

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
			// need to add dateout and due date
			
			blList.add(bl);
		}
		return blList;
	}
}
