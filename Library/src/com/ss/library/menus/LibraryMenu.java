/**
 * 
 */
package com.ss.library.menus;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.library.dao.LibraryBranchDAO;
import com.ss.library.entity.BookAuthors;
import com.ss.library.entity.LibraryBranch;

/**
 * @author brucehaidrey
 *
 */
public class LibraryMenu {
	
	public static void libMenu1() {
		System.out.println("-----------------------------");
		
		System.out.println("1) Enter Branch you manage");
		System.out.println("2) Quit to previous");
	}
	
	public static void libMenu2(List<LibraryBranch> list) {
		System.out.println("-----------------------------");
		
		int i;
		for (i = 0; i < list.size(); i++) {
			System.out.println((i+1) + ") " + list.get(i).getBranchName() + ", " + list.get(i).getBranchAddress());
		}
		System.out.println((i+1) + ") Quit to Main Menu");
	}
	
	public static void libMenu3(LibraryBranch lb) {
		System.out.println("-----------------------------");
		
		System.out.println("What would you like to do with " + lb.getBranchName());
		
		System.out.println("1) Update the details of the library");
		System.out.println("2) Add copies of the book to the Branch");
		System.out.println("3) Quit to previous");
	}
	
	public static void libMenuUpdateBranch(LibraryBranch lb) {
		System.out.println("-----------------------------");
		
		System.out.println("You have chosen to update the Branch with Branch Id: " + lb.getBranchId()
							+ " and Branch Name: " + lb.getBranchName());
		
		System.out.println("Enter 'quit' at any prompt to cancel operation");
		
		System.out.println("Please enter new branch name or enter N/A for no change: ");
	}
	
	public static void libMenuAddCopies(LibraryBranch lb, List<BookAuthors> ba) {
		System.out.println("-----------------------------");
		
		System.out.println("Pick the book you want to add copies of, to your branch");
		
		int i;
		for (i = 0; i < ba.size(); i++) {
			System.out.println((i+1) + ") " + ba.get(i));
		}
		System.out.println((i+1) + ") Quit to Main Menu");
	}
	
	public static void libMenuUpdateCopies(int copies) {
		System.out.println("Existing number of copies: " + copies);
		System.out.println("Enter new number of copies: ");
	}
}

