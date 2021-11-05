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

	public LibraryBranchDAO(Connection conn) {
		super(conn);
	}

	public void addLibraryBranch(LibraryBranch lb) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_library_branch (branchId, branchName, branchAddress) VALUES (?, ?, ?)",
				new Object[] {lb.getBranchId(), lb.getBranchName(), lb.getBranchAddress()});
	}
	
	public void appendLibraryBranch(LibraryBranch lb) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the author table
		save("SET @max_id = (SELECT MAX(branchId) FROM 'tbl_library_branch')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_library_branch (branchId, branchName, branchAddress) VALUES (@max_id + 1, ?, ?)",
				new Object[] {lb.getBranchName(), lb.getBranchAddress()});
	}

	public Integer updateLibraryBranch(LibraryBranch lb) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_library_branch SET branchId = ? AND branchName = ? AND branchAddress = ?",
				new Object[] {lb.getBranchId(), lb.getBranchName(), lb.getBranchAddress()});
	}
	
	public void deleteLibraryBranch(LibraryBranch lb) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_library_branch WHERE branchId = ?", 
				new Object[] {lb.getBranchId(), lb.getBranchName(), lb.getBranchAddress()});
	}	
	
	public List<LibraryBranch> readLibraryBranch() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_library_branch", null);
	}

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
