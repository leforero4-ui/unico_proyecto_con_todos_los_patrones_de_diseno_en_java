package main.application.driver.port.usecase;

import main.domain.model.Enemy;

public interface GameableUseCase {
	String startGame();
	String attack(int row, int column);
	void deleteEnemy(Enemy enemy);
	void counterAttack(Enemy enemy);
}
