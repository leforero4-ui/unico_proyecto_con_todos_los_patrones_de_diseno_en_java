package main.application.driver.port.usecase;

public interface GameableUseCase {
	void startGame();
	Boolean[] attackAndCounterAttack(int row, int column);
	String getStringAvatarSquares();
	void removeDeadEnemies();
	String getEnemies(String stringExpression);
	void healing();
}
