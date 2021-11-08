/**
 * 
 */
package com.ss.library.entity;

import java.sql.Timestamp;

/**
 * @author brucehaidrey
 *
 */
public class BookLoans {
	
	private Book bookId;
	private LibraryBranch branchId;
	private Borrower cardNo;
	private Timestamp dateOut;
	private Timestamp dueDate;
	private Timestamp dateIn;
	
	/**
	 * @return the bookId
	 */
	public Book getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the branchId
	 */
	public LibraryBranch getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(LibraryBranch branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the cardNo
	 */
	public Borrower getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Borrower cardNo) {
		this.cardNo = cardNo;
	}
	public Timestamp getDateOut() {
		return dateOut;
	}
	public void setDateOut(Timestamp dateOut) {
		this.dateOut = dateOut;
	}
	public Timestamp getDueDate() {
		return dueDate;
	}
	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	public Timestamp getDateIn() {
		return dateIn;
	}
	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}
	
	
}
