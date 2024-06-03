// Order.java
package domain;

import java.util.List;
import java.util.UUID;

public class Order {
	private final String orderId;
	private final Client client;
	private final List<Book> books;
	private double total;

	public Order(Client customer, List<Book> books) {
		this.orderId = UUID.randomUUID().toString();
		this.client = customer;
		this.books = books;
		this.total = books.stream().mapToDouble(Book::getPrice).sum();
	}

	public String getOrderId() {
		return orderId;
	}

	public Client getClient() {
		return client;
	}

	public List<Book> getBooks() {
		return books;
	}

	public double getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return String.format("Order[ID=%s, Client=%s, Total=%.2f]", orderId, client, total);
	}
}
