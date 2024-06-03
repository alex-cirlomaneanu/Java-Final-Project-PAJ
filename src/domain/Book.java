// Book.java
package domain;

public class Book {
	private final String isbn;
	private String title;
	private String author;
	private double price;
	private int stock;

	public Book(String isbn, String title, String author, double price, int stock) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock = stock;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return String.format("Book[ISBN=%s, Title=%s, Author=%s, Price=%.2f, Stock=%d]", isbn, title, author, price, stock);
	}
}
