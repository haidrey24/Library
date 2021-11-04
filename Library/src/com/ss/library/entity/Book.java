package com.ss.library.entity;


import java.util.List;

public class Book {
	
	private Integer bookId;
	private String title;
	private Publisher pubId;
	private Author authId;
	private List<BookAuthors> baList;
	private List<BookGenres> bgList;
	
	/**
	 * @return the authorId
	 */
	public Author getAuthId() {
		return authId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthId(Author authId) {
		this.authId = authId;
	}
	/**
	 * @return the bgList
	 */
	public List<BookGenres> getBgList() {
		return bgList;
	}
	/**
	 * @param bgList the bgList to set
	 */
	public void setBgList(List<BookGenres> bgList) {
		this.bgList = bgList;
	}
	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the pubId
	 */
	public Publisher getPubId() {
		return pubId;
	}
	/**
	 * @param pubId the pubId to set
	 */
	public void setPubId(Publisher pubId) {
		this.pubId = pubId;
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

	
	
