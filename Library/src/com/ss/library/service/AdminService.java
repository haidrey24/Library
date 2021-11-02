/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;

import com.ss.library.dao.AuthorDAO;

/**
 * @author brucehaidrey
 *
 */
public class AdminService {
	
	// instance of util, not a connection object
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public String addBook(Author author) {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
//			BookDAO bdao = new BookDao(conn);
			adao.addAuthor(author);
			conn.commit(); // don't forget this line, won't see changes in database
			return "Book added successfully";
		} catch(Exception e) {
			if (conn != null) {
				conn.rollback();
			}
			return "Book addition failed";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
