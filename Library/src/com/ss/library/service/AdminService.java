/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.ss.library.dao.AuthorDAO;
import com.ss.library.entity.Author;

/**
 * @author brucehaidrey
 *
 */
public class AdminService {
	
	// instance of util, not a connection object
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public String addBook(Author author) throws SQLException {
		Connection conn = null;
		// using try-catch-finally block is handling the transactions
		// by using .commit .rollback and .close
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
//			BookDAO bdao = new BookDao(conn);
			adao.addAuthor(author);
			conn.commit(); // don't forget this line, won't see changes in database
			return "Book added successfully";
		} catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book addition failed";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
