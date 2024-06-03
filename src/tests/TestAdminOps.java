package tests;

import domain.Admin;
import domain.Book;
import domain.BookStore;
import domain.User;
import org.junit.Test;
import service.BookStoreLoadData;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestAdminOps extends BasicTest {
	private User admin = null ;
	@Override
	public void setUp() {
		this.setBookStore(new BookStore());
		BookStoreLoadData.loadData(this.getBookStore());
		admin = new Admin("admin1", "Alice Admin", "alice@admin.com", this.getBookStore());
		try {
			this.getBookStore().shutdown();
			this.getBookStore().getExecutorService().awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {
	}

	@Test
	public void testAdmin() {
		assertNotNull(admin);
		assertEquals("Admin", admin.getRole());
		assertNotNull(((Admin) admin).getBookStoreReport());
	}

	@Test
	public void testGetBookStoreReport() {
		assertNotNull(((Admin) admin).getBookStoreReport());
	}

	@Test
	public void testAddBook() {
		int initialSize = this.getBookStore().getInventory().size();
		Book newBook = new Book("8", "New Book", "New Author", 15.99, 10);
		((Admin) admin).addBook(newBook);
		int newSize = this.getBookStore().getInventory().size();
		assertEquals("The book inventory size should increase by 1", initialSize + 1, newSize);
		assertTrue("The new book should be in the inventory", this.getBookStore().getInventory().contains(newBook));
	}

	@Test
	public void testRemoveBook() {
		Book bookToRemove = this.getBookStore().getInventory().get(0);
		int initialSize = this.getBookStore().getInventory().size();
		((Admin) admin).removeBook(bookToRemove);
		int newSize = this.getBookStore().getInventory().size();
		assertEquals("The book inventory size should decrease by 1", initialSize - 1, newSize);
		assertFalse("The removed book should not be in the inventory", this.getBookStore().getInventory().contains(bookToRemove));
	}
}
