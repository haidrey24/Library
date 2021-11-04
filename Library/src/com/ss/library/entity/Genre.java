/**
 * 
 */
package com.ss.library.entity;

import java.util.List;

/**
 * @author brucehaidrey
 *
 */
public class Genre {
	
	private Integer genreId;
	private String genreName;
	private List<BookGenres> bgList;
	
	/**
	 * @return the genreId
	 */
	public Integer getGenreId() {
		return genreId;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}
	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
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
	
	
	
}
