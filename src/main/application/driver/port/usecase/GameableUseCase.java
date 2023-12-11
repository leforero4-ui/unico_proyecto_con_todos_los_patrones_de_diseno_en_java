package main.application.driver.port.usecase;

public interface GameableUseCase {
	void startGame();
	boolean attackAndCounterAttack(int row, int column);
	String getStringAvatarSquares();
	String getEnemies(String stringExpression);
	void healing();
}
