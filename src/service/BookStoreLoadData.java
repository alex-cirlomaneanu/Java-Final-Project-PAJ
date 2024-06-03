package service;

import domain.Book;
import domain.BookStore;
import domain.Client;
import domain.Gender;

import java.util.List;

public class BookStoreLoadData {
	public static void loadData(BookStore bookStore) {
		addSampleBooks(bookStore);
		addSampleClients(bookStore);
		addSampleOrders(bookStore);
	}

	private static void addSampleOrders(BookStore bookStore) {
		List<Client> clients = bookStore.getClients().stream().toList();
		List<Book> books = bookStore.getInventory().stream().toList();
		bookStore.placeOrder(clients.get(0), books.subList(0, 2)); //2 books
		bookStore.placeOrder(clients.get(1), books.subList(2, 4)); //2 books
		bookStore.placeOrder(clients.get(2), books.subList(4, 6)); //2 books
		bookStore.placeOrder(clients.get(3), books.subList(0, 3)); //3 books
		bookStore.placeOrder(clients.get(4), books.subList(3, 6)); //3 books
		bookStore.placeOrder(clients.get(5), books.subList(1, 5)); //4 books
	}

	private static void addSampleClients(BookStore bookStore) {
		List<Client> sampleClients = List.of(
				new Client("Alice Smith", Gender.FEMALE, "New York"),
				new Client("Bob Brown", Gender.MALE, "Boston"),
				new Client("Charlie Green", Gender.MALE, "Chicago"),
				new Client("Diana White", Gender.FEMALE, "Los Angeles"),
				new Client("Eve Black", Gender.FEMALE, "New York"),
				new Client("Frank Blue", Gender.MALE, "Boston"));

		for (Client client : sampleClients) {
			bookStore.addCustomer(client);
		}
	}

	private static void addSampleBooks(BookStore bookStore) {
		List<Book> sampleBooks = List.of(
				new Book("1", "The Great Gatsby", "F. Scott Fitzgerald", 24.99, 20),
				new Book("2", "To Kill a Mockingbird", "Harper Lee", 19.99, 15),
				new Book("3", "1984", "George Orwell", 14.99, 10),
				new Book("4", "Pride and Prejudice", "Jane Austen", 17.99, 25),
				new Book("5", "The Catcher in the Rye", "J.D. Salinger", 12.99, 30),
				new Book("6", "The Hobbit", "J.R.R. Tolkien", 29.99, 5),
				new Book("7", "The Lord of the Rings", "J.R.R. Tolkien", 49.99, 0));
		for (Book book : sampleBooks) {
			bookStore.addBook(book);
		}
	}
}
