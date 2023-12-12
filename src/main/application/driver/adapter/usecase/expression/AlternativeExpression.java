package main.application.driver.adapter.usecase.expression;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import main.application.driver.port.usecase.Expression;

public class AlternativeExpression implements Expression {
	private final Expression leftExpression;
	private final Expression rightExpression;
	
	public AlternativeExpression(final Expression leftExpression, final Expression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}

	@Override
	public List<String> interpret(Context context) {
		List<String> leftAvatarSquares = leftExpression.interpret(context);
		List<String> rightAvatarSquares = rightExpression.interpret(context);
		Set<String> avatarSquares = new LinkedHashSet<>(leftAvatarSquares);
		avatarSquares.addAll(rightAvatarSquares);
        return new ArrayList<>(avatarSquares);
	}

}
