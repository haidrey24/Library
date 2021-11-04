/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author brucehaidrey
 *
 */
public class Borrower {
	
	private Integer cardNo;
	private String name;
	private String address;
	private String phone;
	private List<BookLoans> blList;
	
	/**
	 * @return the cardNo
	 */
	public Integer getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	
}
