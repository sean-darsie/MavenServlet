/**
 * 
 */
package com.smoothstack.training.libraryproject;

/**
 * @author seandarsie
 *
 */
public class Author {
	private int authorId;
	private String name;
	
	public Author(int authorId, String authorName) {
		this.setAuthorId(authorId);
		this.setName(authorName);
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String authorName) {
		this.name = authorName;
	}
	
	public String displayInfo()
	{
		return "Author ID: "+authorId + ", Author Name: "+name;
	}
	
	public String recordInfo()
	{
		return authorId+","+name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
	
}
