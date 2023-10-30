package main.application.driver.adapter.usecase;

import java.util.List;

import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.GameableUseCase;
import main.domain.model.Enemy;
import main.domain.model.Player;

public class Game implements GameableUseCase {

	private final EnemyMethod enemyMethod;
	private final Player player;
	
	public Game(final EnemyMethod enemyMethod, final Player player) {
		this.enemyMethod = enemyMethod;
		this.player = player;
	}

	@Override
	public void startGame() {
		
		this.player.draw();
		
		final List<Enemy> enemies = enemyMethod.createEnemies();
		for (final Enemy enemy : enemies) {
			enemy.draw();
		}
	}

}
