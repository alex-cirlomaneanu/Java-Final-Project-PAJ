// BookStore.java
package domain;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookStore {
	private final List<Book> inventory = new ArrayList<>();
	private final List<Client> clients = new ArrayList<>();
	private final List<Order> orders = new ArrayList<>();
	private final ExecutorService executorService = Executors.newFixedThreadPool(10);

	public void addBook(Book book) {
		inventory.add(book);
	}

	public void addCustomer(Client client) {
		clients.add(client);
	}

	public void placeOrder(Client client, List<Book> books) {
		executorService.submit(() -> {
			Order order = new Order(client, books);
			orders.add(order);
			client.getBooks().addAll(books);
			books.forEach(book -> book.setStock(book.getStock() - 1));
			System.out.println("Order placed: " + order);
		});
	}

	public List<Book> searchBooks(Predicate<Book> criteria) {
		return inventory.stream().filter(criteria).collect(Collectors.toList());
	}

	public void shutdown() {
		executorService.shutdown();
	}

	// Getters for unit tests
	public List<Book> getInventory() {
		return inventory;
	}

	public List<Client> getClients() {
		return clients;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void removeBook(Book book) {
		inventory.remove(book);
	}
}
