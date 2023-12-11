package main.application.driver.adapter.usecase.expression;

import java.util.List;

public class Context {
	private final List<String> avatarSquares;
	
	public Context(final List<String> avatarSquares) {
		this.avatarSquares = avatarSquares;
	}
	
	public List<String> getAvatarSquares() {
		return this.avatarSquares;
	}
}
