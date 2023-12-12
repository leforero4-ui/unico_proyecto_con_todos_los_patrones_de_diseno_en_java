package main.application.driver.adapter.usecase;

import java.util.ArrayList;
import java.util.List;

import main.application.driver.adapter.usecase.board.BigBoard;
import main.application.driver.adapter.usecase.expression.ConjunctionExpression;
import main.application.driver.adapter.usecase.expression.Context;
import main.application.driver.adapter.usecase.expression.AlternativeExpression;
import main.application.driver.adapter.usecase.expression.EnemyExpression;
import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.Expression;
import main.application.driver.port.usecase.GameableUseCase;
import main.application.driver.port.usecase.iterator.BoardCollection;
import main.application.driver.port.usecase.iterator.PatternsIterator;
import main.domain.model.Enemy;
import main.domain.model.Healable;
import main.domain.model.Player;
import main.domain.model.Visitor;

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
	public void startGame() {
		final List<Enemy> enemies = enemyMethod.createEnemies();
		this.board = new BigBoard(enemies);
		this.enemyIterator = board.getIterator();
	}

	@Override
	public boolean attackAndCounterAttack(final int row, final int column) {
		final Enemy enemy = board.getEnemy(row, column);
		enemy.receiveAttack(this.player.getAttackLevel());
		if (enemy.getLife() <= 0) {
			this.deleteEnemy(enemy);
			return false;
		} else {
			this.counterAttack(enemy);
			return true;
		}
	}
	
	private void deleteEnemy(final Enemy enemy) {
		while (this.enemyIterator.hasNext()) {
			if(this.enemyIterator.getNext().equals(enemy)) {
				this.enemyIterator.remove();
				break;
			}
        }
		this.enemyIterator.reset();
	}

	@Override
	public String getStringAvatarSquares() {
		final StringBuilder squares = new StringBuilder();
		while (this.enemyIterator.hasNext()) {
            squares.append(this.enemyIterator.getAvatarSquareNext());
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

	private void counterAttack(final Enemy enemy) {
		this.player.receiveAttack(enemy.getAttackLevel());
	}

	@Override
	public void healing() {
		final Visitor heleable = new Healable();
		while (this.enemyIterator.hasNext()) {
			this.enemyIterator.visit(heleable);
        }
		this.enemyIterator.reset();
		this.player.acceptVisit(heleable);
	}

	private List<String> getAvatarSquares() {
		final List<String> avatarSquares = new ArrayList<>();
		while (this.enemyIterator.hasNext()) {
			avatarSquares.add(this.enemyIterator.getAvatarSquareNext());
        }
		this.enemyIterator.reset();
		return avatarSquares;
	}

	@Override
	public String getEnemies(final String stringExpression) {
		final Expression expression = this.parseExpression(this.prepareStringExpression(stringExpression));
		final Context context = new Context(this.getAvatarSquares());
		final StringBuilder avatarSquaresStringBuilder = new StringBuilder();
		List<String> avatarSquares = expression.interpret(context);
		avatarSquares.forEach(avatarSquare -> avatarSquaresStringBuilder.append(avatarSquare + "\r\n"));
		return avatarSquaresStringBuilder.toString();
	}
	
	private String prepareStringExpression(String stringExpression) {
		stringExpression = stringExpression.substring("buscar:".length());
		stringExpression = stringExpression.replaceAll("\\(", " \\( ");
		stringExpression = stringExpression.replaceAll("\\)", " \\) ");
		stringExpression = stringExpression.replaceAll("  ", " ");
		stringExpression = stringExpression.trim();
		return stringExpression;
	}
	
	//recurrencia
	private Expression parseExpression(final String input) {
		final String[] tokens = input.split(" ", 3);
        
        String firstToken = tokens[0];
    	if (firstToken.equalsIgnoreCase("(")) {
        	final String[] seconPartToken = input.split(" ", 2);
        	return this.parseExpression(seconPartToken[1]);
    	}
        
        if (tokens.length != 3) {
        	return new EnemyExpression(firstToken);
        }
        
        final String operator = tokens[1];
        final String secondToken = tokens[2];
        
        return this.expressionByOperator(operator, firstToken, secondToken);
	}

	//recurrencia
	private Expression expressionByOperator(final String operator, final String firstToken, final String secondToken) {
        return switch(operator.toLowerCase()) {
        case "y","&" -> new ConjunctionExpression(new EnemyExpression(firstToken), this.parseExpression(secondToken));
        case "o","|" -> new AlternativeExpression(new EnemyExpression(firstToken), this.parseExpression(secondToken));
        case ")" -> {
        	final String[] seconPartToken = secondToken.split(" ", 2);
        	yield this.expressionByOperator(seconPartToken[0], firstToken, seconPartToken[1]);
        }
        default -> new ConjunctionExpression(new EnemyExpression(firstToken), this.parseExpression(secondToken));
        };
	}

}
