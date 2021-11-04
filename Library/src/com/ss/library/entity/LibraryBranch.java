/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author brucehaidrey
 *
 */
public class LibraryBranch {
	
	private Integer branchId;
	private String branchName;
	private String branchAddress;
	private List<BookCopies> bcList;
	private List<BookLoans> blList;
	
	/**
	 * @return the bcList
	 */
	public List<BookCopies> getBcList() {
		return bcList;
	}
	/**
	 * @param bcList the bcList to set
	 */
	public void setBcList(List<BookCopies> bcList) {
		this.bcList = bcList;
	}
	/**
	 * @return the blList
	 */
	public List<BookLoans> getBlList() {
		return blList;
	}
	/**
	 * @param blList the blList to set
	 */
	public void setBlList(List<BookLoans> blList) {
		this.blList = blList;
	}
	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}
	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	
	
	
}
