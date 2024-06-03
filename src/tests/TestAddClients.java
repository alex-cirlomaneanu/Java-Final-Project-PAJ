package tests;

import domain.BookStore;
import domain.Client;
import domain.Gender;
import exceptions.AlreadyExistingClientException;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAddClients extends BasicTest {
	public TestAddClients() {
		super();
	}

	@Override
	public void setUp() {
		setBookStore(new BookStore());
	}

	@Override
	public void tearDown() {
		this.getBookStore().shutdown();
	}

	@Test
	public void testAddCustomer() {
		Client client = new Client("John Doe", Gender.MALE, "New York");
		Client client2 = new Client("Jane Smith", Gender.FEMALE, "Boston");

		this.getBookStore().addCustomer(client);

		assertTrue("Client was not added to the bookstore",
				this.getBookStore().getClients().contains(client));

		assertEquals("Number of clients is not as expected",
				1,
				this.getBookStore().getClients().size());

		this.getBookStore().addCustomer(client2);
		assertTrue("Client was not added to the bookstore",
				this.getBookStore().getClients().contains(client2));
		assertEquals("Number of clients is not as expected",
				2,
				this.getBookStore().getClients().size());
	}

	@Test
	public void testAddExistingCustomer() {
		Client client = new Client("John Doe", Gender.MALE, "New York");

		this.getBookStore().addCustomer(client);

		assertTrue("Client was not added to the bookstore",
				this.getBookStore().getClients().contains(client));

		assertEquals("Number of clients is not as expected",
				1,
				this.getBookStore().getClients().size());

		assertThrows("Client was added twice",
				AlreadyExistingClientException.class,
				() -> this.getBookStore().addCustomer(client));
	}
}
