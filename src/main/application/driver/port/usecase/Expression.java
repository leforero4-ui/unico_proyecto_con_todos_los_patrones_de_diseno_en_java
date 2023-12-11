package main.application.driver.port.usecase;

import java.util.List;

import main.application.driver.adapter.usecase.expression.Context;

public interface Expression {
	List<String> interpret(Context context);
}
