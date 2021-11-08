/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Publisher;

/**
 * @Publisher brucehaidrey
 *
 */
public class PublisherDAO extends BaseDAO<Publisher>{

	/*
	 * Implement the constructor from the BaseDAO class
	 * @param conn - connection to establish 
	 */
	public PublisherDAO(Connection conn) {
		super(conn);
	}

	/*
	 * Used to add an publisher to the publisher table
	 * @param pb - publisher to add
	 */
	public void addPublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_publisher (publisherId, publisherName, publisherAddress, publisherPhone) VALUES (?, ?, ?, ?)",
				new Object[] {pb.getPubId(), pb.getName(), pb.getAddress(), pb.getPhone()});
	}
	
	/*
	 * Used to append an publisher to the end of the publisher table
	 * @param pb - publisher to append
	 */
	public void appendPublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the Publisher table
		save("SET @max_id = (SELECT MAX(pubId) FROM 'tbl_publisher')", null);
		// pass the previous value into insert and retrieve the info
		save("INSERT INTO tbl_publisher (publisherId, publisherName, publisherAddress, publisherPhone) VALUES (@max_id + 1, ?, ?, ?)",
				new Object[] {pb.getName(), pb.getAddress(), pb.getPhone()});
	}

	/*
	 * Used to update the publisher in the publisher table
	 * @param pb - publisher to update in the table
	 * @return 1 - first column from table if updating is successful
	 */
	public void updatePublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?",
				new Object[] {pb.getName(), pb.getAddress(), pb.getPhone(), pb.getPubId()});
	}
	
	/*
	 * Used to delete the publisher from the table
	 * @param pb - publisher to delete
	 */
	public void deletePublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_publisher WHERE publisherId = ?",
				new Object[] {pb.getPubId(), pb.getName(), pb.getAddress(), pb.getPhone()});
	}	
	
	/*
	 * Return the table of publisher in a list format
	 */
	public List<Publisher> readPublisher() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_publisher", null);
	}

	/*
	 * Used to extract the data from the table
	 * @return List of the extracted data
	 */
	@Override
	protected List<Publisher> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Publisher> pbList = new ArrayList<>();
		/*
		 * can't generalize between DAO's, each one is specific
		 */
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPubId(rs.getInt("pubId"));
			publisher.setName(rs.getString("name"));
			publisher.setAddress(rs.getString("address"));
			publisher.setPhone(rs.getString("phone"));
			pbList.add(publisher);
		}
		return pbList;
	}
}
