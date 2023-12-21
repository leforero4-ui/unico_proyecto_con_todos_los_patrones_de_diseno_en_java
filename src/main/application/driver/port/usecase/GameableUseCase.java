package main.application.driver.port.usecase;

public interface GameableUseCase {
	void startGame();
	Boolean[] attackAndCounterAttack(int row, int column);
	Boolean[] attackWithComboAndCounterAttack(int row, int column);
	void calculateFreezing();
	boolean isFrozen();
	int getTurnsForDefrost();
	void plusTurnFrozen();
	String getStringAvatarSquares();
	String getStringAvatarPlayer();
	void removeDeadEnemies();
	String getEnemies(String stringExpression);
	void healing();
	boolean doOrRestoreBackup(String inputString);
	boolean isGameCompleted();
	boolean verifyAnUpLevel();
}
