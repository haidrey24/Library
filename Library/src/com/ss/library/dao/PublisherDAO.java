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
	
	public PublisherDAO(Connection conn) {
		super(conn);
	}

	public void addPublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_publisher (pubId, name, address, phone) VALUES (?, ?, ?, ?)",
				new Object[] {pb.getPubId(), pb.getName(), pb.getAddress(), pb.getPhone()});
	}
	
	public void appendPublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		// retrieve the max id from the Publisher table
		save("SET @max_id = (SELECT MAX(pubId) FROM 'tbl_publisher')", null);
		// pass the previous value into insert and retrieve the name
		save("INSERT INTO tbl_publisher (pubId, name, address, phone) VALUES (@max_id + 1, ?, ?, ?)",
				new Object[] {pb.getName(), pb.getAddress(), pb.getPhone()});
	}

	public Integer updatePublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		return saveWithPK("UPDATE tbl_publisher SET pubId = ? AND name = ? AND address = ? AND phone = ?",
				new Object[] {pb.getPubId(), pb.getName(), pb.getAddress(), pb.getPhone()});
	}
	
	public void deletePublisher(Publisher pb) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_publisher WHERE pubId = ?",
				new Object[] {pb.getPubId(), pb.getName(), pb.getAddress(), pb.getPhone()});
	}	
	
	public List<Publisher> readPublisher() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM tbl_publisher", null);
	}

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
