/**
 * 
 */
package com.ss.library.entity;

/**
 * @author brucehaidrey
 *
 */
public class BookCopies {

	private Book bookId;
	private LibraryBranch branchId;
	private Integer numCopies;
	
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
	 * @return the numCopies
	 */
	public Integer getNumCopies() {
		return numCopies;
	}
	/**
	 * @param numCopies the numCopies to set
	 */
	public void setNumCopies(Integer numCopies) {
		this.numCopies = numCopies;
	}
	
	
	
}
