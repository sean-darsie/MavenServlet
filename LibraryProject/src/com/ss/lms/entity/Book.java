/**
 * 
 */
package com.ss.lms.entity;

import java.util.List;

/**
 * @author seandarsie
 *
 */
public class Book {

	private Integer bookId;
	private String name;
	private Integer pubId;
	private List<Author> authors;
	

	public Integer getPubId() {
		return pubId;
	}

	public void setPubId(Integer pubId) {
		this.pubId = pubId;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Book(Integer bookId, String name, Integer pubId, List<Author> authors) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.pubId = pubId;
		this.authors = authors;
	}

	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + bookId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pubId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookId != other.bookId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pubId != other.pubId)
			return false;
		return true;
	}

}
