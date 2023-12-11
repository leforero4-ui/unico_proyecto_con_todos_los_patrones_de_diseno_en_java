package main.application.driver.adapter.usecase.expression;

import java.util.List;

import main.application.driver.port.usecase.Expression;

public class WordExpression implements Expression {
	private final String word;

	public WordExpression(String word) {
		this.word = switch(word.toLowerCase()) {
		case "soldado" -> "S";
		case "aire" -> "A";
		case "naval" -> "N";
		case "fortaleza" -> "F";
		case "escuadron", "escuadrón" -> "E";
		case "supremo", "maestro" -> "M";
		default -> word.toUpperCase();
		};
	}

	@Override
	public List<String> interpret(Context context) {
        return context.getAvatarSquares().stream()
                .filter(avatarSquare -> avatarSquare.contains(this.word))
                .toList();
	}

}
