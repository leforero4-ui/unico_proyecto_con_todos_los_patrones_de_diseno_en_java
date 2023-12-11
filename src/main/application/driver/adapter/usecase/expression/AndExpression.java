package main.application.driver.adapter.usecase.expression;

import java.util.ArrayList;
import java.util.List;

import main.application.driver.port.usecase.Expression;

public class AndExpression implements Expression {
	private final Expression leftExpression;
	private final Expression rightExpression;
	
	public AndExpression(final Expression leftExpression, final Expression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}

	@Override
	public List<String> interpret(Context context) {
		List<String> leftAvatarSquares = leftExpression.interpret(context);
		List<String> rightAvatarSquares = rightExpression.interpret(context);
		List<String> avatarSquares = new ArrayList<>(leftAvatarSquares);
		avatarSquares.retainAll(rightAvatarSquares);
        return avatarSquares;
	}

}
