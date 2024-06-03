package tests;

import domain.*;
import org.junit.Test;
import service.BookStoreLoadData;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestBookStoreReport extends BasicTest {
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
	public void testGetNumberOfClients() {
		assertEquals(6, ((Admin) admin).getBookStoreReport().getNumberOfClients());
	}

	@Test
	public void testGetNumberOfBooksPurchased() throws InterruptedException {
		assertEquals(16, ((Admin) admin).getBookStoreReport().getNumberOfBooksPurchased());
	}

	@Test
	public void testGetTotalSumSpentOnBooks() {
		assertEquals(307.84, ((Admin) admin).getBookStoreReport().getTotalSumSpentOnBooks(), 0.0);
	}

	@Test
	public void testGetClientsSorted() {
		assertEquals(6, ((Admin) admin).getBookStoreReport().getClientsSorted().size());
		assertEquals("Alice Smith", ((Admin) admin).getBookStoreReport().getClientsSorted().first().getName());
		assertEquals("Frank Blue", ((Admin) admin).getBookStoreReport().getClientsSorted().last().getName());
	}

	@Test
	public void testGetBooksSortedByPrice() {
		assertEquals(6, ((Admin) admin).getBookStoreReport().getBooksSortedByPrice().size());
		assertEquals(12.99, ((Admin) admin).getBookStoreReport().getBooksSortedByPrice().first().getPrice(), 0.0);
		assertEquals(29.99, ((Admin) admin).getBookStoreReport().getBooksSortedByPrice().last().getPrice(), 0.0);
	}

	@Test
	public void testGetCustomerBooks() {
		Map<Client, Set<Book>> customerBooks = ((Admin) admin).getBookStoreReport().getCustomerBooks();
		Client aliceSmith = customerBooks.keySet().stream()
				.filter(client -> client.getName().equals("Alice Smith"))
				.findFirst()
				.orElse(null);
		Client dianaWhite = customerBooks.keySet().stream()
				.filter(client -> client.getName().equals("Diana White"))
				.findFirst()
				.orElse(null);;
		Client bobBrown = customerBooks.keySet().stream()
				.filter(client -> client.getName().equals("Bob Brown"))
				.findFirst()
				.orElse(null);;

		assertTrue(customerBooks.containsKey(aliceSmith));
		assertTrue(customerBooks.containsKey(dianaWhite));
		assertTrue(customerBooks.containsKey(bobBrown));

		Set<Book> aliceBooks = customerBooks.get(aliceSmith);
		assertEquals(2, aliceBooks.size());
		assertTrue(aliceBooks.stream().anyMatch(book -> book.getTitle().equals("The Great Gatsby")));
		assertTrue(aliceBooks.stream().anyMatch(book -> book.getTitle().equals("To Kill a Mockingbird")));

		Set<Book> dianaBooks = customerBooks.get(dianaWhite);
		assertEquals(3, dianaBooks.size());
		assertTrue(dianaBooks.stream().anyMatch(book -> book.getTitle().equals("The Great Gatsby")));
		assertTrue(dianaBooks.stream().anyMatch(book -> book.getTitle().equals("To Kill a Mockingbird")));
		assertTrue(dianaBooks.stream().anyMatch(book -> book.getTitle().equals("1984")));

		Set<Book> bobBooks = customerBooks.get(bobBrown);
		assertEquals(2, bobBooks.size());
		assertTrue(bobBooks.stream().anyMatch(book -> book.getTitle().equals("1984")));
		assertTrue(bobBooks.stream().anyMatch(book -> book.getTitle().equals("Pride and Prejudice")));
	}

	@Test
	public void testGetClientsByCity() {
		Map<String, List<Client>> clientsByCity = ((Admin) admin).getBookStoreReport().getClientsByCity();
		assertEquals(4, clientsByCity.size());
		assertEquals(2, clientsByCity.get("New York").size());
		assertEquals(2, clientsByCity.get("Boston").size());
		assertEquals(1, clientsByCity.get("Chicago").size());
		assertEquals(1, clientsByCity.get("Los Angeles").size());
	}
}
