/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.LibraryBranch;

/**
 * @author brucehaidrey
 *
 */
public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {

	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public LibraryBranchDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to add an LibraryBranch to the library_branch table
	 * @param lb - LibraryBranch to add
	 */
	public void addLibraryBranch(LibraryBranch lb) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_library_branch (branchId, branchName, branchAddress) VALUES (?, ?, ?)",
				new Object[] {lb.getBranchId(), lb.getBranchName(), lb.getBranchAddress()});
	}
	
	/*
	 * Used to append an LibraryBranch to the end of the library_branch table
	 * @param lb - LibraryBranch to append
	 */
	public void appendLibraryBranch(LibraryBranch lb) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the library_branch table
		save("SET @max_id = (SELECT MAX(branchId) FROM 'tbl_library_branch')", null);
		// pass the previous value into insert and retrieve the info
		save("INSERT INTO tbl_library_branch (branchId, branchName, branchAddress) VALUES (@max_id + 1, ?, ?)",
				new Object[] {lb.getBranchName(), lb.getBranchAddress()});
	}

	/*
	 * Used to update the LibraryBranch in the library_branch table
	 * @param lb - LibraryBranch to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public Integer updateLibraryBranch(LibraryBranch lb) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_library_branch SET branchName = ? AND branchAddress = ? WHERE branchId = ?",
				new Object[] {lb.getBranchName(), lb.getBranchAddress(), lb.getBranchId()});
	}
	
	/*
	 * Used to delete the LibraryBranch from the table
	 * @param lb - LibraryBranch to delete
	 */
	public void deleteLibraryBranch(LibraryBranch lb) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", 
				new Object[] {lb.getBranchId(), lb.getBranchName(), lb.getBranchAddress()});
	}	
	
	/*
	 * Return the table of LibraryBranch in a list format
	 */
	public List<LibraryBranch> readLibraryBranch() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_library_branch", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
	@Override
	protected List<LibraryBranch> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<LibraryBranch> lbList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			LibraryBranch lb = new LibraryBranch();
			lb.setBranchId(rs.getInt("branchId"));
			lb.setBranchName(rs.getString("branchName"));
			lb.setBranchAddress(rs.getString("branchAddress"));
			lbList.add(lb);
		}
		return lbList;
	}
}
