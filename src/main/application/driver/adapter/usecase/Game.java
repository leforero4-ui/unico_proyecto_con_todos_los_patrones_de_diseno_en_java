package main.application.driver.adapter.usecase;

import java.util.List;

import main.application.driver.adapter.usecase.board.BigBoard;
import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.GameableUseCase;
import main.application.driver.port.usecase.iterator.Iterator;
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
	public String startGame() {
		final List<Enemy> enemies = enemyMethod.createEnemies();
		BigBoard bigBoard = new BigBoard(enemies);
		Iterator<Enemy> enemyIterator = bigBoard.getIterator();
		final StringBuilder squares = new StringBuilder();
		while (enemyIterator.hasNext()) {
            squares.append(enemyIterator.getAvatarNext());
        }
		enemyIterator.reset();
        return "board: " + squares.toString() + "\n" + this.player.getAvatar();
	}

}
