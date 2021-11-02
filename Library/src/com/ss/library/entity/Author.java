/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author brucehaidrey
 *
 */
public class Author {
	
	private Integer id;
	private String name;
	private List<BookAuthors> baList;
	
	/**
	 * @return the ba
	 */
	public List<BookAuthors> getBa() {
		return baList;
	}

	/**
	 * @param ba the ba to set
	 */
	public void setBa(List<BookAuthors> baList) {
		this.baList = baList;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	
	
	
	
	

}
