/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author brucehaidrey
 *
 */
public class Publisher {
	
	private Integer pubId;
	private String name;
	private String address;
	private String phone;
	private List<Book> book;
	
	/**
	 * @return the pubId
	 */
	public Integer getPubId() {
		return pubId;
	}
	/**
	 * @param pubId the pubId to set
	 */
	public void setPubId(Integer pubId) {
		this.pubId = pubId;
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
	 * @return the book
	 */
	public List<Book> getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(List<Book> book) {
		this.book = book;
	}
	
	
	
}
