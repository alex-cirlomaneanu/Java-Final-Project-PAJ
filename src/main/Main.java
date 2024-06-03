package main;

import domain.*;
import service.BookStoreReport;

import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		BookStore bookStore = new BookStore();

		// Create books
		Book book1 = new Book("123", "Effective Java", "Joshua Bloch", 45.0, 10);
		Book book2 = new Book("456", "Clean Code", "Robert C. Martin", 50.0, 5);
		Book book3 = new Book("789", "Design Patterns", "Erich Gamma", 40.0, 7);

		Client client1 = new Client("John Doe", Gender.MALE, "New York");
		Client client2 = new Client("Jane Smith", Gender.FEMALE, "Boston");

		bookStore.addBook(book1);
		bookStore.addBook(book2);
		bookStore.addBook(book3);

		bookStore.addCustomer(client1);
		bookStore.addCustomer(client2);

		Admin admin = new Admin("admin1", "Alice Admin", "admin@example.com", bookStore);
		admin.viewAllBooks();
		admin.viewAllClients();

		bookStore.placeOrder(client1, Arrays.asList(book1, book2));
		bookStore.placeOrder(client2, Arrays.asList(book2, book3));

		BookStoreReport report = admin.getBookStoreReport();
		System.out.println("Number of clients: " + report.getNumberOfClients());
		System.out.println("Total books purchased: " + report.getNumberOfBooksPurchased());
		System.out.println("Total sum spent on books: " + report.getTotalSumSpentOnBooks());
		System.out.println("Clients sorted: " + report.getClientsSorted());
		System.out.println("Books sorted by price: " + report.getBooksSortedByPrice());
		System.out.println("Customer books: " + report.getCustomerBooks());
		System.out.println("Clients by city: " + report.getClientsByCity());

		bookStore.shutdown();
	}
}
