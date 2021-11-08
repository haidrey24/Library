/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.ss.library.dao.AuthorDAO;
import com.ss.library.dao.BookDAO;
import com.ss.library.dao.BorrowerDAO;
import com.ss.library.dao.GenreDAO;
import com.ss.library.dao.LibraryBranchDAO;
import com.ss.library.dao.PublisherDAO;
import com.ss.library.entity.Author;
import com.ss.library.entity.Book;
import com.ss.library.entity.Borrower;
import com.ss.library.entity.Genre;
import com.ss.library.entity.LibraryBranch;
import com.ss.library.entity.Publisher;

/**
 * @author brucehaidrey
 *
 */
public class AdminService {
	
	// instance of util, not a connection object
	ConnectionUtil connUtil = new ConnectionUtil();
	
	/*
	 * do i need to pass genre type
	 * 
	 * Method adds a Book to the table
	 * 
	 * @param bookId - used to set the bookId
	 * @param title - used to set the title
	 * @param author - used to set the author
	 * @param pub - used to set the publisher
	 * 
	 * @return - string that confirms whether or not the book was added
	 */
	public String addBook(Integer bookId, String title, Author author, Publisher pub) throws SQLException {
		Connection conn = null;
		// using try-catch-finally block is handling the transactions
		// by using .commit .rollback and .close
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			PublisherDAO pdao = new PublisherDAO(conn);
			BookDAO bdao = new BookDAO(conn);
//			GenreDAO gdao = new GenreDAO(conn);
			Book book = new Book();
			
			// add authors, genre, and publishers to respective tables
			adao.addAuthor(author);
			pdao.addPublisher(pub);
//			gdao.addGenre(genre);
			
			// set the variables of your new book
			book.setAuthId(author);
			book.setPubId(pub);
			book.setBookId(bookId);
			book.setTitle(title);
			
			// add the new book to the table and commit it
			bdao.addBook(book);
			conn.commit(); // don't forget this line, won't see changes in database
			
			return "Book added successfully";
		} catch(Exception e) {
			// if there's an exception rollback
			e.printStackTrace();
			conn.rollback();
			return "Book addition failed";
		} finally {
			if (conn != null) {
				// close your connection
				conn.close();
			}
		}
	}
	
	/*
	 * Method adds an Author to the table
	 * 
	 * @param authorId - used to set the authorId
	 * @param authorName - used to set the authorName
	 * 
	 * @return - string that confirms whether or not the Author was added
	 */
	public String addAuthor(Integer authorId, String authorName) throws SQLException {
		Connection conn = null;
		// using try-catch-finally block is handling the transactions
		// by using .commit .rollback and .close
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			Author author = new Author();
			
			// set the variables of your new author
			author.setAuthorId(authorId);
			author.setAuthorName(authorName);
			
			// add the new author to the table and commit it
			adao.addAuthor(author);
			conn.commit(); // don't forget this line, won't see changes in database
			
			return "Author added successfully";
		} catch(Exception e) {
			// if there's an exception rollback
			e.printStackTrace();
			conn.rollback();
			return "Author addition failed";
		} finally {
			if (conn != null) {
				// close your connection
				conn.close();
			}
		}
	}
	
	/*
	 * Method adds an Genre to the table
	 * 
	 * @param genreId - used to set the genreId
	 * @param genreName - used to set the genreName
	 * 
	 * @return - string that confirms whether or not the Genre was added
	 */
	public String addGenre(Integer genreId, String genreName) throws SQLException {
		Connection conn = null;
		// using try-catch-finally block is handling the transactions
		// by using .commit .rollback and .close
		try {
			conn = connUtil.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			Genre genre = new Genre();
			
			// set the variables of your new genre
			genre.setGenreId(genreId);
			genre.setGenreName(genreName);
			
			// add the new genre to the table and commit it
			gdao.addGenre(genre);
			conn.commit(); // don't forget this line, won't see changes in database
			
			return "Genre added successfully";
		} catch(Exception e) {
			// if there's an exception rollback
			e.printStackTrace();
			conn.rollback();
			return "Genre addition failed";
		} finally {
			if (conn != null) {
				// close your connection
				conn.close();
			}
		}
	}
	
	/*
	 * Method adds an Publisher to the table
	 * 
	 * @param pubId - used to set the publisherId
	 * @param pubName - used to set the publisherName
	 * @param pubAddy - used to set the publisherAddress
	 * @param pubPhone - used to set the publisherPhone
	 * 
	 * @return - string that confirms whether or not the Publisher was added
	 */
	public String addPublisher(Integer pubId, String pubName, String pubAddy, String pubPhone) throws SQLException {
		Connection conn = null;
		// using try-catch-finally block is handling the transactions
		// by using .commit .rollback and .close
		try {
			conn = connUtil.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			Publisher publisher = new Publisher();
			
			// set the variables of your new publisher
			publisher.setPubId(pubId);
			publisher.setName(pubName);
			publisher.setAddress(pubAddy);
			publisher.setPhone(pubPhone);
			
			// add the new publisher, to the table and commit it
			pdao.addPublisher(publisher);
			conn.commit(); // don't forget this line, won't see changes in database
			
			return "Publisher added successfully";
		} catch(Exception e) {
			// if there's an exception rollback
			e.printStackTrace();
			conn.rollback();
			return "Publisher addition failed";
		} finally {
			if (conn != null) {
				// close your connection
				conn.close();
			}
		}
	}
	
	/*
	 * Method adds an Library Branch to the table
	 * 
	 * @param branchId - used to set the branchId
	 * @param branchName - used to set the branchName
	 * @param branchAddress - used to set the branchAddress
	 * 
	 * @return - string that confirms whether or not the Library Branch was added
	 */
	public String addLibraryBranch(Integer branchId, String branchName, String branchAddress) throws SQLException {
		Connection conn = null;
		// using try-catch-finally block is handling the transactions
		// by using .commit .rollback and .close
		try {
			conn = connUtil.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			LibraryBranch lb = new LibraryBranch();
			
			// set the variables of your new publisher
			lb.setBranchId(branchId);
			lb.setBranchName(branchName);
			lb.setBranchAddress(branchAddress);
			
			// add the new publisher, to the table and commit it
			lbdao.addLibraryBranch(lb);
			conn.commit(); // don't forget this line, won't see changes in database
			
			return "Library Branch added successfully";
		} catch(Exception e) {
			// if there's an exception rollback
			e.printStackTrace();
			conn.rollback();
			return "Library Branch addition failed";
		} finally {
			if (conn != null) {
				// close your connection
				conn.close();
			}
		}
	}
	
	/*
	 * Method adds an Borrower to the table
	 * 
	 * @param branchId - used to set the branchId
	 * @param branchName - used to set the branchName
	 * @param branchAddress - used to set the branchAddress
	 * 
	 * @return - string that confirms whether or not the Borrower was added
	 */
	public String addBorrower(Integer cardNo, String borrowerName, String borrowerAddress, String borrowerPhone) throws SQLException {
		Connection conn = null;
		// using try-catch-finally block is handling the transactions
		// by using .commit .rollback and .close
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			Borrower borrower = new Borrower();
			
			// set the variables of your new borrower
			borrower.setCardNo(cardNo);
			borrower.setName(borrowerName);
			borrower.setAddress(borrowerAddress);
			borrower.setPhone(borrowerPhone);
			
			// add the new borrower, to the table and commit it
			bdao.addBorrower(borrower);
			conn.commit(); // don't forget this line, won't see changes in database
			
			return "Borrower added successfully";
		} catch(Exception e) {
			// if there's an exception rollback
			e.printStackTrace();
			conn.rollback();
			return "Borrower addition failed";
		} finally {
			if (conn != null) {
				// close your connection
				conn.close();
			}
		}
	}
	
	
}
