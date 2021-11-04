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
	
	private Integer authorId;
	private String authorName;
	private List<BookAuthors> baList;
	
	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the baList
	 */
	public List<BookAuthors> getBaList() {
		return baList;
	}
	/**
	 * @param baList the baList to set
	 */
	public void setBaList(List<BookAuthors> baList) {
		this.baList = baList;
	}
	


}
