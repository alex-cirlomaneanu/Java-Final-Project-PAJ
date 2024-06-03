package service;


import domain.Book;
import domain.BookStore;
import domain.Client;

import java.util.*;
import java.util.stream.Collectors;

public class BookStoreReport {

	private final BookStore bookStore;

	public BookStoreReport(BookStore bookstore) {
		this.bookStore = bookstore;
	}

	public BookStore getBookStore() {
		return bookStore;
	}

	public int getNumberOfClients() {
		return bookStore.getClients().size();
	}

	public int getNumberOfBooksPurchased() {
		return bookStore.getClients().stream()
				.mapToInt(client -> client.getBooks().size())
				.sum();
	}

	public SortedSet<Client> getClientsSorted() {
		return bookStore.getClients().stream()
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Client::getName))));
	}

	public double getTotalSumSpentOnBooks() {
		return bookStore.getClients().stream()
				.flatMap(client -> client.getBooks().stream())
				.mapToDouble(Book::getPrice)
				.sum();
	}

	public SortedSet<Book> getBooksSortedByPrice() {
		return bookStore.getClients().stream()
				.flatMap(client -> client.getBooks().stream())
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingDouble(Book::getPrice))));
	}

	public Map<Client, Set<Book>> getCustomerBooks() {
		return bookStore.getClients().stream()
				.collect(Collectors.toMap(client -> client, Client::getBooks));
	}

	public Map<String, List<Client>> getClientsByCity() {
		return bookStore.getClients().stream()
				.collect(Collectors.groupingBy(Client::getCity));
	}
}
