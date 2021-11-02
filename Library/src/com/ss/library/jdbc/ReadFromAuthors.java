/**
 * 
 */
package com.ss.library.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author brucehaidrey
 * @throws ClassNotFoundException
 * @throws SQLException
 */
public class ReadFromAuthors {

	
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/library";
	public static final String username = "root";
	public static final String password = "Blackmamba24";
	
	/*
	 * Framework
	 * Entities for all different tables
	 * Data Access Objects
	 * Create services they can do
	 * 
	 * Design
	 * How many classes do i need
	 * how many abstract classes do i need
	 * 
	 * 
	 * 
	 */
	
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// 1. Register driver
		Class.forName(driver); // optional - it won't throw error but it's good practice
		// 2. Create connection
		Connection conn = DriverManager.getConnection(url, username, password);
		// 3. Statement object
		/*
		 * Statement executes query and checks for compilation
		 */
//		Statement stmt = conn.createStatement();
		/*
		 * Prepared statement doesn't allow users to alter your tables?
		 */
		PreparedStatement pstmt = conn.prepareStatement("Select * from tbl_author where authorId = ?");
		System.out.println("Enter author id to search: ");
		Scanner scan = new Scanner(System.in);
		int authorId = scan.nextInt();
//		String sql = "Select * from tbl_author where authorId = '" + authorId+"'";
		pstmt.setInt(1, authorId);
		
		// 4. Execute
		/*
		 * very similar to hashmap
		 * key - name in column
		 * value - value in column
		 * never will have duplicates
		 * 
		 */
//		ResultSet rs = stmt.executeQuery(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			System.out.println("AuthorId is: " + rs.getInt("authorId"));
			System.out.println("AuthorName is: " + rs.getString("authorName"));
			System.out.println("---------------------");
			
		}
		
	}
	

}
