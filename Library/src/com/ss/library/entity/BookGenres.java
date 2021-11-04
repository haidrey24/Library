/**
 * 
 */
package com.ss.library.entity;

/**
 * @author brucehaidrey
 *
 */
public class BookGenres {
	
	private Genre genreId;
	private Book bookId;
	
	/**
	 * @return the genreId
	 */
	public Genre getGenreId() {
		return genreId;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(Genre genreId) {
		this.genreId = genreId;
	}
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
}
