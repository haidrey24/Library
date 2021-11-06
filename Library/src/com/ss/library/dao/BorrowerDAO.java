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
	
	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to add a borrower to the borrower table
	 * @param borrower - borrower to add
	 */
	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_borrower (cardNo, name, address, phone) VALUES (?, ?, ?, ?)",
				new Object[] {borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}
	
	/*
	 * Used to append an borrower to the end of the borrower table
	 * @param borrower - borrower to append
	 */
	public void appendBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the borrower table
		save("SET @max_id = (SELECT MAX(cardNo) FROM 'tbl_borrower')", null);
		// pass the previous value into insert and retrieve the info
		save("INSERT INTO tbl_borrower (cardNo, name, address, phone) VALUES (@max_id + 1, ?, ?, ?)",
				new Object[] {borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}

	/*
	 * Used to update the borrower in the borrower table
	 * @param borrower - borrower to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public Integer updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_borrower SET name = ? AND address = ? AND phone = ? WHERE cardNo = ?",
				new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
	}
	
	/*
	 * Used to delete the borrower from the table
	 * @param borrower - borrower to delete
	 */
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_borrower WHERE cardNo = ?", 
				new Object[] {borrower.getCardNo(), borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}	
	
	/*
	 * Return the table of borrower in a list format
	 */
	public List<Borrower> readBorrower() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_borrower", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
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
