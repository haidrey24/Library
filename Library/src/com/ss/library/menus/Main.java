/**
 * 
 */
package com.ss.library.menus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.ss.library.service.LibrarianService;

/**
 * @author brucehaidrey
 *
 */
public class Main {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/library";
	public static final String username = "root";
	public static final String password = "Blackmamba24";
	
	/*
	 * Displays the main menu
	 */
	public static void mainMenu() {
		System.out.println("------------------------------");
		
		System.out.println("Welcome to the SS Library Management System. Which category of a user are you");
		
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
	}
	
	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		String str;
		int count = 0;
		
		mainMenu(); 
		
		// iterate while there is a next input available
		while (scan.hasNextLine())
		{
			str = scan.nextLine();
			if (str.equals("1"))
			{
				// handles library menu
				
				count = new LibrarianService(scan).libService1();
			}
//			else if (str.equals("2"))
//			{
//				// handles admin menu
//				System.out.println("FIX LATER");
//			}
//			else if (str.equals("3"))
//			{
//				// handles borrower menu
//				count = new BorrowerService(scan).borService1();
//			}
			else if (str.toLowerCase().equals("quit"))
			{
				// allows user to terminate program
				scan.close();
				return;
			}
//			
			// used to return back to main menu
			if (count == 0) {
				mainMenu();
				continue;
			}
		}
	}
	
	
	
}
