package domain;

public enum Gender {
	MALE("Mr."), FEMALE("Ms.");

	private String greeting;

	Gender(String greeting) {
		this.greeting = greeting;
	}

	public String getGreeting() {
		return greeting;
	}
}
