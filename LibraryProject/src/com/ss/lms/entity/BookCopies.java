/**
 * 
 */
package com.ss.lms.entity;

/**
 * @author seandarsie
 *
 */
public class BookCopies {
	Integer bookId;
	Integer branchId;
	Integer noOfCopies;
	
	/**
	 * 
	 * @param bookId
	 * @param branchId
	 * @param noOfCopies
	 */
	public BookCopies(Integer bookId, Integer branchId, Integer noOfCopies) {
		super();
		this.bookId = bookId;
		this.branchId = branchId;
		this.noOfCopies = noOfCopies;
	}
	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + bookId;
		result = prime * result + branchId;
		result = prime * result + noOfCopies;
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
		BookCopies other = (BookCopies) obj;
		if (bookId != other.bookId)
			return false;
		if (branchId != other.branchId)
			return false;
		if (noOfCopies != other.noOfCopies)
			return false;
		return true;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public Integer getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
