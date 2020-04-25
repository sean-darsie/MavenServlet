/**
 * 
 */
package com.ss.lms.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Publisher;

/**
 * @publisher seandarsie
 *
 */
public class PublisherDao extends BaseDao<Publisher>{

	public PublisherDao(Connection conn) {
		super(conn);
	}

	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
		save("INSERT INTO tbl_publisher (publisherName,publisherAddress,publisherPhone) VALUES (?,?,?)", new Object[] {publisher.getName(),publisher.getAddress(),publisher.getPhone()});
	}

	public void updatePublisher(Publisher publisher)  throws ClassNotFoundException, SQLException{
		save("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?", new Object[] {publisher.getName(),publisher.getAddress(),publisher.getPhone(), publisher.getPublisherId()});
	}

	public void deletePublisher(Publisher publisher)  throws ClassNotFoundException, SQLException{
		save("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[]{publisher.getPublisherId()});
	}
	
	public List<Publisher> readAllPublishers() throws ClassNotFoundException, SQLException{
		return read("SELECT * FROM tbl_publisher", null);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher publisher = new Publisher(rs.getInt("publisherId"),rs.getString("publisherName"),rs.getString("publisherAddress"),rs.getString("publisherPhone"));
			publishers.add(publisher);
		}
		return publishers;
	}

}
