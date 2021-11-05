/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Borrower;

/**
 * @borrower brucehaidrey
 *
 */
public class BorrowerDAO extends BaseDAO<Borrower> {
	
	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_borrower (cardNo, name, address, phone) VALUES (?, ?, ?, ?)",
				new Object[] {borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}
	
	public void appendBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the borrower table
		save("SET @max_id = (SELECT MAX(cardNo) FROM 'tbl_borrower')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_borrower (cardNo, name, address, phone) VALUES (@max_id + 1, ?, ?, ?)",
				new Object[] {borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}

	public Integer updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_borrower SET cardNo = ? AND name = ? AND address = ? AND phone = ?",
				new Object[] {borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_borrower WHERE cardNo = ?", 
				new Object[] {borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}	
	
	public List<Borrower> readBorrower() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_borrower", null);
	}

	@Override
	protected List<Borrower> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Borrower> bList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setAddress(rs.getString("address"));
			borrower.setPhone(rs.getString("phone"));
			bList.add(borrower);
		}
		return bList;
	}
}
