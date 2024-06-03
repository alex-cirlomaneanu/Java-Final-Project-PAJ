package exceptions;

public class BookStockException extends RuntimeException {
	public BookStockException(String title) {
		super("Book stock exception: " + title);
	}
}
