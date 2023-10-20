package main.infrastructure.driven.adapter.provider;

import java.util.Scanner;

import main.application.driven.port.provider.Drawable;

public class ConsoleDrawable implements Drawable {
	
	private final Scanner scanner;
	
	public ConsoleDrawable() {
		this.scanner = new Scanner(System.in);
	}

	@Override
	public String in(final String text) {
		System.out.println(text);
		if (scanner.hasNextLine()) {
			return scanner.nextLine();
		}
		
		return "";
	}

	@Override
	public void out(final String text) {
		System.out.println(text);
	}

}
