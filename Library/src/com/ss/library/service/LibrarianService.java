/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.library.dao.BookAuthorsDAO;
import com.ss.library.dao.LibraryBranchDAO;
import com.ss.library.entity.Author;
import com.ss.library.entity.Book;
import com.ss.library.entity.BookAuthors;
import com.ss.library.entity.LibraryBranch;
import com.ss.library.menus.LibraryMenu;

/**
 * @author brucehaidrey
 *
 */
public class LibrarianService {
	private Scanner scan;
//	private LibraryMenu menu;
	
	public LibrarianService(Scanner scan) {
		this.scan = scan;		
	}
	
	public int libService1() {
		// display the menu to enter a branch		
		LibraryMenu.libMenu1();
		int user = 0;
		
		// scan the user input
		while (scan.hasNextLine()) {
			try {
				// retrieve the user input
				user = Integer.parseInt(scan.next());
			} catch (NumberFormatException e) {
				System.out.println("Number Format Exception in libService1");
				e.printStackTrace();
			}
			
			// if user wants to enter a branch, call next method
			if (user == 1) {
				try {
					return libService2();
				} catch (SQLException e) {
					System.out.println("SQL Exception in libService1 user == 1"); 
					e.printStackTrace();
				}
			}
			else if (user == 2) {
//				System.out.println("Called in libService1");
				// return back to main menu
				return 0;
			}
		}
		// return back to main menu
		return 0;
	}
	
	
	public int libService2() throws SQLException {
		// stores the library branches
		List<LibraryBranch> branches = new ArrayList<>();
		
		Connection conn = null;
		try {
			ConnectionUtil connUtil = new ConnectionUtil();
			conn = connUtil.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			// retrieve a list of library branches
			branches = lbdao.readLibraryBranch();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("SQL Exception in libService2");
			conn.rollback();
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found Exception in libService2");
			conn.rollback();
		} finally {
			if (conn != null) {
				// close your connection
				conn.close();
			}
		}
		
		// display the proper menu
		LibraryMenu.libMenu2(branches);
		// used to store user input
		int user = 0;
		
		while (true) {
			
			try {
				if (scan.hasNextLine()) {
					user = Integer.parseInt(scan.next());
				}
				// count = scan.nextInt();
				// retrieve the user selection
				
			} catch (NumberFormatException e) {
				System.out.println("Number Format Exception in libService2");
				continue;
			} 
			
			// handles a user input not listed in the menu
			if (user > branches.size() + 1 || user < 1) {
				System.out.println("User selected number out of range in libService2");
				continue;
			}
			break;
		}
		
		// handles if the user wants to quit to main menu
		if (user == branches.size() + 1) {
			return libService1();
		}
		
		// menu list starts at 1. subtract 1 to retrieve the correct branch in the list
		LibraryBranch branch = branches.get(user - 1);
		
		// go to next menu with the selected library branch
		return libService3(branch);
	}
	
	public int libService3(LibraryBranch lb) throws SQLException {
		// display the correct menu
		LibraryMenu.libMenu3(lb);
		List<LibraryBranch> branches;
		Connection conn = null;
		int user; 
		scan.nextLine();
		
		while (scan.hasNextLine()) {
			
			try {
				// retrieve the user input
				user = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number Format Exception in libService3");
				continue;
			}
			
			// handle different cases of the user input
			if (user == 1) {
				// pass branch into method that handles updates
				libService4(lb);
				
				try {
					ConnectionUtil connUtil = new ConnectionUtil();
					conn = connUtil.getConnection();
					LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
					// retrieve a list of library branches
					branches = lbdao.readLibraryBranch();
					LibraryMenu.libMenu3(branches.get(user - 1));
					conn.commit();
				} catch (SQLException e) {
					System.out.println("SQL Exception in libService3 option 1");
					conn.rollback();
				} catch (ClassNotFoundException e) {
					System.out.println("Class Not Found Exception in libService3 option 1");
					conn.rollback();
				} finally {
					if (conn != null) {
						// close your connection
						conn.close();
					}
				}
			} else if (user == 2) {
				// pass branch into method that handles book copies
				libService5(lb);
				try {
					ConnectionUtil connUtil = new ConnectionUtil();
					conn = connUtil.getConnection();
					LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
					// retrieve a list of library branches
					branches = lbdao.readLibraryBranch();
					LibraryMenu.libMenu3(branches.get(user - 2));
					conn.commit();
				} catch (SQLException e) {
					System.out.println("SQL Exception in libService3 option 2");
					conn.rollback();
				} catch (ClassNotFoundException e) {
					System.out.println("Class Not Found Exception in libService3 option 2");
					conn.rollback();
				} finally {
					if (conn != null) {
						// close your connection
						conn.close();
					}
				}
				
			} else if (user == 3) {
				return libService2();
			}	
		}
		
		return 1;
	}
	
	
	public int libService4(LibraryBranch lb) throws SQLException {
		// create strings to store the user input, branchName, and branchAddress
		String user = "";
		Connection conn = null;
		
		while (true) {

			// display the menu
			LibraryMenu.libMenuUpdateBranch(lb);
			
			// read the user input for the address
			if (scan.hasNextLine()) {
				user = scan.nextLine();
			}
			
			// if user enters quit terminate the program
			if (user.toLowerCase().equals("quit")) {
				return 0;
			}
			
			// if the user enters n/a keep the same branch name
			if (user.toLowerCase().equals("n/a")) {
				// set the branch name to the current branch name
//				branchName = lb.getBranchName();
				lb.setBranchName(lb.getBranchName());
			}
			else {
				// set the branch name to the user input
//				branchName = user;
				lb.setBranchName(user);
			}
			
			// Prompt the user to enter a new address
			System.out.println("Please enter new address or enter N/A for no change: ");
//			LibraryMenu.libMenuUpdateAddress();
			
			// read the user input
			if (scan.hasNextLine()) {
				user = scan.nextLine();
			}
			
			// if user enters quit terminate the program
			if (user.toLowerCase().equals("quit")) {
				return 0;
			}
			
			// if the user enters n/a keep the same branch address
			if (user.toLowerCase().equals("n/a")) {
				// set the branch address to the current branch address
//				branchAddress = lb.getBranchAddress();
				lb.setBranchAddress(lb.getBranchAddress());
			}
			else {
				// set the branch address to the user input
//				branchAddress = user;
				lb.setBranchAddress(user);
			}
			
			// Establish a connection and update the library branch accordingly
			try {
				ConnectionUtil connUtil = new ConnectionUtil();
				conn = connUtil.getConnection();
				LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
				lbdao.updateLibraryBranch(lb);
				conn.commit();
			} catch (SQLException e) {
				System.out.println("SQL Exception in libService4");
				e.printStackTrace();
				conn.rollback();
			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found Exception in libService4");
				conn.rollback();
			} finally {
				if (conn != null) {
					// close your connection
					conn.close();
				}
			}
			
			System.out.println("Library Branch updated sucessfully!");
			
			return libService3(lb);
		}
	}
	
	public int libService5(LibraryBranch lb) throws SQLException {
		String user = "";
		Connection conn = null;
		List<BookAuthors> baList = new ArrayList<>();
		Book book = new Book();
		BookAuthors ba = new BookAuthors();
		
		try {
			ConnectionUtil connUtil = new ConnectionUtil();
			conn = connUtil.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			BookAuthorsDAO bdao = new BookAuthorsDAO(conn);
			// retrieve a list of library branches
			baList = bdao.readBookAuthorsList();
			conn.commit();
		} catch (SQLException e) {
			System.out.println("SQL Exception in libService2");
			conn.rollback();
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found Exception in libService2");
			conn.rollback();
		} finally {
			if (conn != null) {
				// close your connection
				conn.close();
			}
		}
		
		LibraryMenu.libMenuAddCopies(lb, baList);
		
		
		
		return 1;
	}
	
	
}
