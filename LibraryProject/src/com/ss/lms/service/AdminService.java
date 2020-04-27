/**
 * 
 */
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.doa.AuthorDao;
import com.ss.lms.doa.BookDao;
import com.ss.lms.doa.BookLoanDao;
import com.ss.lms.doa.BorrowerDao;
import com.ss.lms.doa.BranchDao;
import com.ss.lms.doa.GenreDao;
import com.ss.lms.doa.PublisherDao;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookLoan;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Publisher;

/**
 * @author seandarsie
 *
 */
public class AdminService {
public ConnectionUtil connUtil = new ConnectionUtil();
	
	public void saveAuthor(Author author){
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDao adao = new AuthorDao(conn);
			if(author.getAuthorId()!=null && author.getName()!=null){
				adao.updateAuthor(author);
			}else if(author.getAuthorId()!=null){
				adao.deleteAuthor(author);
			}else{
				adao.addAuthor(author);
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void saveBook(Book book){
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDao bdao = new BookDao(conn);
			if(book.getBookId()!=null && book.getName()!=null){
				bdao.updateBook(book);
			}else if(book.getBookId()!=null){
				bdao.deleteBook(book);
			}else{
				bdao.addBook(book);
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void savePublisher(Publisher publisher){
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDao bdao = new PublisherDao(conn);
			if(publisher.getPublisherId()!=null && publisher.getName()!=null){
				bdao.updatePublisher(publisher);
			}else if(publisher.getPublisherId()!=null){
				bdao.deletePublisher(publisher);
			}else{
				bdao.addPublisher(publisher);
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void saveGenre(Genre genre){
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDao bdao = new GenreDao(conn);
			if(genre.getGenreId()!=null && genre.getName()!=null){
				bdao.updateGenre(genre);
			}else if(genre.getGenreId()!=null){
				bdao.deleteGenre(genre);
			}else{
				bdao.addGenre(genre);
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void saveBranch(Branch branch){
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDao bdao = new BranchDao(conn);
			if(branch.getBranchId()!=null && branch.getName()!=null){
				bdao.updateBranch(branch);
			}else if(branch.getBranchId()!=null){
				bdao.deleteBranch(branch);
			}else{
				bdao.addBranch(branch);
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void saveBorrower(Borrower borrower){
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDao bdao = new BorrowerDao(conn);
			if(borrower.getCardNo()!=null && borrower.getName()!=null){
				bdao.updateBorrower(borrower);
			}else if(borrower.getCardNo()!=null){
				bdao.deleteBorrower(borrower);
			}else{
				bdao.addBorrower(borrower);
			}
			conn.commit(); //transaction
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readGenres()
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDao gdao = new GenreDao(conn);
			List<Genre> allGenres = gdao.readAllGenres();
			for (Genre g: allGenres)
			{
				System.out.println("Genre id: "+g.getGenreId()+" Genre: "+g.getName());
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readAuthors()
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDao gdao = new AuthorDao(conn);
			List<Author> allAuthors = gdao.readAllAuthors();
			for (Author g: allAuthors)
			{
				System.out.println("Author id: "+g.getAuthorId()+" Author: "+g.getName());
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readBooks()
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDao gdao = new BookDao(conn);
			List<Book> allBooks = gdao.readAllBooks();
			for (Book g: allBooks)
			{
				System.out.println("Book id: "+g.getBookId()+" Book: "+g.getName());
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readPublishers()
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDao gdao = new PublisherDao(conn);
			List<Publisher> allPublishers = gdao.readAllPublishers();
			for (Publisher p: allPublishers)
			{
				System.out.println("Publisher id: "+p.getPublisherId()+" Publisher: "+p.getName()+" Address"+p.getAddress()+" Phone: "+p.getPhone());
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readBorrowers()
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDao gdao = new BorrowerDao(conn);
			List<Borrower> allBorrowers = gdao.readAllBorrowers();
			for (Borrower b: allBorrowers)
			{
				System.out.println("Borrower cardNo: "+b.getCardNo()+" Borrower: "+b.getName()+" Address: "+b.getAddress()+" Phone: "+b.getPhone());
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readBookLoans()
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoanDao gdao = new BookLoanDao(conn);
			List<BookLoan> allBookLoans = gdao.readAllBookLoanes();
			for (BookLoan loan: allBookLoans)
			{
				System.out.println("card id: "+loan.getCardNo()+" book id: "+loan.getBookId()+" branch id: "+loan.getBranchId()+" due date: "+loan.getDateDue());
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readBranchs()
	{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDao gdao = new BranchDao(conn);
			List<Branch> allBranchs = gdao.readAllBranches();
			for (Branch b: allBranchs)
			{
				System.out.println("Branch id: "+b.getBranchId()+" Branch: "+b.getName() + " address: "+b.getAddress());
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
