// BookStore.java
package domain;

import exceptions.AlreadyExistingClientException;
import exceptions.BookStockException;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookStore {
	private final List<Book> inventory = new ArrayList<>();
	private final Set<Client> clients = new TreeSet<>(Comparator.comparing(Client::getName));
	private final List<Order> orders = new CopyOnWriteArrayList<>();
	private final ExecutorService executorService = Executors.newFixedThreadPool(10);

	public void addBook(Book book) {
		inventory.add(book);
	}

	public void addCustomer(Client client) {
		if (!clients.add(client)) {
			throw new AlreadyExistingClientException(client.getName());
		}
	}

	public void placeOrder(Client client, List<Book> books) {
		for (Book book : books) {
			if (book.getStock() <= 0) {
				throw new BookStockException(book.getTitle());
			}
		}

		executorService.submit(() -> {
			Order order = new Order(client, books);
			books.forEach(book -> book.setStock(book.getStock() - 1));
			orders.add(order);
			client.getBooks().addAll(books);
			System.out.println("Order no." + orders.size() +" placed: " + order);
		});
	}

	public List<Book> searchBooks(Predicate<Book> criteria) {
		return inventory.stream().filter(criteria).collect(Collectors.toList());
	}

	public void shutdown() {
		executorService.shutdown();
	}

	public List<Book> getInventory() {
		return inventory;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void removeBook(Book book) {
		inventory.remove(book);
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}
}
