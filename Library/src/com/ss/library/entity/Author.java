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
	
	private int id;
	private String name;
	private List<BookAuthors> ba;
	
	/**
	 * @return the ba
	 */
	public List<BookAuthors> getBa() {
		return ba;
	}

	/**
	 * @param ba the ba to set
	 */
	public void setBa(List<BookAuthors> ba) {
		this.ba = ba;
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
