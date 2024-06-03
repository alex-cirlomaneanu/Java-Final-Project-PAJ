package domain;

import service.BookStoreReport;

public class Admin extends User {
	private final BookStore bookStore;
	private final BookStoreReport bookStoreReport;

	public Admin(String id, String name, String email, BookStore bookStore) {
		super(id, name, email);
		this.bookStore = bookStore;
		this.bookStoreReport = new BookStoreReport(bookStore);
	}

	@Override
	public String getRole() {
		return "Admin";
	}

	public void addBook(Book book) {
		bookStore.addBook(book);
	}

	public void removeBook(Book book) {
		bookStore.removeBook(book);
	}


	public void viewAllBooks() {
		bookStore.getInventory().forEach(System.out::println);
	}

	public void viewAllClients() {
		bookStore.getClients().forEach(System.out::println);
	}

	public BookStoreReport getBookStoreReport() {
		return bookStoreReport;
	}
}
