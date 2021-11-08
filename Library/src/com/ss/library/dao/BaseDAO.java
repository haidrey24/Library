/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author brucehaidrey
 *
 */
public abstract class BaseDAO<T> {
	
	protected Connection conn = null;
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/library";
	public static final String username = "root";
	public static final String password = "Blackmamba24";
	
	public BaseDAO(Connection conn) {
		this.conn = conn;

	}
	
	protected void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null)
		{
			int count = 1;
			for (Object o: vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.execute();
	}

	/*
	 * Thread safe way of getting the unique id
	 * If multiple queries are hitting the database at the same time
	 * and generating/executing multiple id's this will always be thread safe
	 * and return the unique id that your connection should generate
	 * Statement.Return
	 * 
	 * this method will give you the primary key after you select the particular record
	 */
	protected Integer saveWithPK(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if (vals != null)
		{
			int count = 1;
			for (Object o: vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		
		while (rs.next())
		{
			return rs.getInt(1); // check if this is 0 or 1
		}
		
		return null;
	}
	
	protected List<T> read(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null)
		{
			int count = 1;
			for (Object o: vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}
	
	abstract protected List<T> extractData(ResultSet rs) throws ClassNotFoundException, SQLException;
}
