/**
 * 
 */
package com.ss.lms.entity;

import java.util.List;

/**
 * @author seandarsie
 *
 */
public class Author {
	private Integer authorId;
	private String name;
	private List<Book> books;
	/**
	 * 
	 * @param authorId
	 * @param name
	 * @param books
	 */
	public Author(Integer authorId, String name, List<Book> books) {
		super();
		this.authorId = authorId;
		this.name = name;
		this.setBooks(books);
	}

	public Author(Integer authorId, String authorName) {
		this.setAuthorId(authorId);
		this.setName(authorName);
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String authorName) {
		this.name = authorName;
	}

	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + authorId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
