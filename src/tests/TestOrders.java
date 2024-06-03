package tests;

import domain.Book;
import domain.BookStore;
import domain.Client;
import exceptions.BookStockException;
import org.junit.Test;
import service.BookStoreLoadData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestOrders extends BasicTest {
	public TestOrders() {
		super();
	}

	@Override
	public void setUp() {
		this.setBookStore(new BookStore());
		BookStoreLoadData.loadData(this.getBookStore());
	}

	@Override
	public void tearDown() {
	}

	@Test
	public void testPlaceOrder() throws InterruptedException {
		List<Client> clients = new ArrayList<>(this.getBookStore().getClients());
		Client client = clients.get(0);
		List<Book> books = this.getBookStore().searchBooks(book -> book.getStock() > 0);
		this.getBookStore().placeOrder(client, books.subList(0, 2));

		this.getBookStore().shutdown();
		this.getBookStore().getExecutorService().awaitTermination(5, TimeUnit.SECONDS);


		assertEquals("Orders list length is not as expected",
				7,
				this.getBookStore().getOrders().size());
	}

	@Test
	public void testPlaceOrderWithInsufficientStock() throws InterruptedException {
		List<Book> books = this.getBookStore().searchBooks(book -> book.getStock() == 0);
		System.out.println(books);
		Client client = this.getBookStore().getClients().stream().toList().get(0);
		this.getBookStore().shutdown();
		this.getBookStore().getExecutorService().awaitTermination(5, TimeUnit.SECONDS);
		assertThrows("Order was placed with insufficient stock",
				BookStockException.class,
				() -> this.getBookStore().placeOrder(client, books));
	}
}
