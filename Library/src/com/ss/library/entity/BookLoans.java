/**
 * 
 */
package com.ss.library.entity;

import java.time.LocalDateTime;

/**
 * @author brucehaidrey
 *
 */
public class BookLoans {
	
	private Book bookId;
	private LibraryBranch branchId;
	private Borrower cardNo;
	private LocalDateTime dateOut;
	private LocalDateTime dueDate;
	private LocalDateTime dateIn;
	
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
	/**
	 * @return the dateOut
	 */
	public LocalDateTime getDateOut() {
		return dateOut;
	}
	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(LocalDateTime dateOut) {
		this.dateOut = dateOut;
	}
	/**
	 * @return the dueDate
	 */
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the dateIn
	 */
	public LocalDateTime getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(LocalDateTime dateIn) {
		this.dateIn = dateIn;
	}
	
	
	
}
