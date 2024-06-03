package tests;

import domain.BookStore;
import org.junit.After;
import org.junit.Before;

public abstract class BasicTest {
	private BookStore bookStore;

	public BasicTest() {
	}

	public BookStore getBookStore() {
		return bookStore;
	}

	public void setBookStore(BookStore bookStore) {
		this.bookStore = bookStore;
	}

	@Before
	public abstract void setUp();

	@After
	public abstract void tearDown();
}
