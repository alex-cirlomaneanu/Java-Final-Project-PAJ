package tests;

import domain.BookStore;
import domain.Client;
import domain.Gender;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test1 {
	private BookStore bookStore;

	@Before
	public void setUp() {
		// Initialize a new BookStore instance before each test
		bookStore = new BookStore();
	}
	@Test
	public void testAddCustomer() {
		Client client = new Client("John Doe", Gender.MALE, "New York");

		bookStore.addCustomer(client);

		assertTrue("Client was not added to the bookstore", bookStore.getClients().contains(client));

		assertEquals("Number of clients is not as expected", 1, bookStore.getClients().size());
	}
}
