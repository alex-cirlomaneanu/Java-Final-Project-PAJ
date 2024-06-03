package exceptions;

public class AlreadyExistingClientException extends RuntimeException {
	public AlreadyExistingClientException(String name) {
		super("Client with name " + name + " already exists!");
	}
}
