package main.application.driver.adapter.usecase;

import java.util.List;

import main.application.driver.adapter.usecase.board.BigBoard;
import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.GameableUseCase;
import main.application.driver.port.usecase.iterator.BoardCollection;
import main.application.driver.port.usecase.iterator.PatternsIterator;
import main.domain.model.Enemy;
import main.domain.model.Player;

public class Game implements GameableUseCase {
	private final EnemyMethod enemyMethod;
	private final Player player;
	private BoardCollection<Enemy> board;
	private PatternsIterator<Enemy> enemyIterator;
	
	public Game(final EnemyMethod enemyMethod, final Player player) {
		this.enemyMethod = enemyMethod;
		this.player = player;
	}

	@Override
	public String startGame() {
		final List<Enemy> enemies = enemyMethod.createEnemies();
		this.board = new BigBoard(enemies);
		this.enemyIterator = board.getIterator();
		return this.squars();
	}

	@Override
	public String attack(final int row, final int column) {
		final Enemy enemy = board.getEnemy(row, column);
		enemy.receiveAttack(player.getAttackLevel());
		if (enemy.getLife() <= 0) {
			this.deleteEnemy(enemy);
		} else {
			this.counterAttack(enemy);
		}
		return this.squars();
	}
	
	@Override
	public void deleteEnemy(final Enemy enemy) {
		while (this.enemyIterator.hasNext()) {
			if(this.enemyIterator.getNext().equals(enemy)) {
				this.enemyIterator.remove();
				break;
			}
        }
		this.enemyIterator.reset();
	}
	
	private String squars() {
		final StringBuilder squares = new StringBuilder();
		while (this.enemyIterator.hasNext()) {
            squares.append(this.enemyIterator.getAvatarNext());
        }
		this.enemyIterator.reset();
        return "B=bomba,M=multiples disparos,F=fortaleza,V=veneno,A=aire,N=naval,S=soldado,E=escuadrón,M=maestro\r\n"
        		+ "\r\n"
        		+ "tablero:{fila-columna:avatar:vida:ataque}\r\n"
        		+ "\r\n"
        		+ "enemigos: " + squares.toString() + "\r\n"
                + "\r\n"
        		+ "jugador: {X-X:" + this.player.getAvatar() + ":" + this.player.getLife() + ":" + this.player.getAttackLevel() + "}\r\n";
	}

	@Override
	public void counterAttack(final Enemy enemy) {
		this.player.receiveAttack(enemy.getAttackLevel());
	}

}
