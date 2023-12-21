package main.application.driver.adapter.usecase;

import java.util.ArrayList;
import java.util.List;

import main.application.driver.adapter.usecase.board.BigBoard;
import main.application.driver.adapter.usecase.expression.ConjunctionExpression;
import main.application.driver.adapter.usecase.expression.Context;
import main.application.driver.adapter.usecase.expression.AlternativeExpression;
import main.application.driver.adapter.usecase.expression.EnemyExpression;
import main.application.driver.adapter.usecase.factory_enemies.EnemyBasicMethod;
import main.application.driver.adapter.usecase.factory_enemies.EnemyHighMethod;
import main.application.driver.adapter.usecase.factory_enemies.EnemyMiddleMethod;
import main.application.driver.adapter.usecase.mission.BasicMission;
import main.application.driver.adapter.usecase.mission.HighMission;
import main.application.driver.adapter.usecase.mission.MiddleMission;
import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.Expression;
import main.application.driver.port.usecase.GameableUseCase;
import main.application.driver.port.usecase.iterator.BoardCollection;
import main.application.driver.port.usecase.iterator.PatternsIterator;
import main.domain.model.ArmyFactory;
import main.domain.model.CaretakerPlayer;
import main.domain.model.Command;
import main.domain.model.Enemy;
import main.domain.model.FavorableEnvironment;
import main.domain.model.Healable;
import main.domain.model.Mission;
import main.domain.model.Player;
import main.domain.model.Player.MementoPlayer;
import main.domain.model.command.Attack;
import main.domain.model.command.HealingPlayer;
import main.domain.model.Visitor;

public class Game implements GameableUseCase {
	private final ArmyFactory armyFactory;
	private EnemyMethod enemyMethod;
	private final Player player;
	private BoardCollection<Enemy> board;
	private PatternsIterator<Enemy> enemyIterator;
	private FavorableEnvironment favorableEnvironments;
	private final Frostbite frostbite;
	private final CaretakerPlayer caretakerPlayer;
	private Mission mission;
	private int level;
	
	public Game(final ArmyFactory armyFactory, final Player player) {
		this.armyFactory = armyFactory;
		this.player = player;
		this.frostbite = new Frostbite();
		this.caretakerPlayer = new CaretakerPlayer();
	}

	@Override
	public void startGame() {
		this.enemyMethod = new EnemyBasicMethod(armyFactory);
		this.board = new BigBoard(this.enemyMethod.createEnemies());
		this.enemyIterator = this.board.getIterator();
		this.mission = new BasicMission(this.board);
		this.favorableEnvironments = this.enemyMethod.createFavorableEnvironments();
		this.level = 1;
	}

	@Override
	public boolean verifyAnUpLevel() {
		if (this.mission.isMissionComplete()) {
			if (this.level == 1) {
				this.enemyMethod = new EnemyMiddleMethod(armyFactory);
				this.board = new BigBoard(this.enemyMethod.createEnemies());
				this.enemyIterator = this.board.getIterator();
				this.mission = new MiddleMission(this.board);
				this.favorableEnvironments = this.enemyMethod.createFavorableEnvironments();
			} else {
				this.enemyMethod = new EnemyHighMethod(armyFactory);
				this.board = new BigBoard(this.enemyMethod.createEnemies());
				this.enemyIterator = this.board.getIterator();
				this.mission = new HighMission(this.board);
				this.favorableEnvironments = this.enemyMethod.createFavorableEnvironments();
			}
			++this.level;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isGameCompleted() {
		return this.level > 3;
	}

	@Override
	public Boolean[] attackAndCounterAttack(final int row, final int column) {
		final Enemy enemy = board.getEnemy(row, column);
		return new Boolean[] {this.attack(enemy), this.eliminedEnemyFallBackCounterAttack(enemy)};
	}

	@Override
	public Boolean[] attackWithComboAndCounterAttack(final int row, final int column) {
		final Enemy enemy = board.getEnemy(row, column);
		return new Boolean[] {this.combo(enemy), this.eliminedEnemyFallBackCounterAttack(enemy)};
	}
	
	private boolean eliminedEnemyFallBackCounterAttack(final Enemy enemy) {
		final boolean isEnemyEliminated = enemy.getLife() <= 0;
		if (isEnemyEliminated) {
			this.deleteEnemy(enemy);
		} else {
			this.counterAttack(enemy);
		}
		
		return isEnemyEliminated;
	}
	
	private void counterAttack(final Enemy enemy) {
		this.player.receiveAttack(enemy.getCounterAttackLevel(this.player.getAttackLevel()));
	}

	private boolean combo(final Enemy enemy) {
		final int lifeBeforeAttack = enemy.getLife();
		
		final List<Command> comboCommands = new ArrayList<>();
		
		comboCommands.add(new Attack(this.favorableEnvironments, this.player.getAttackLevel(), enemy));
		comboCommands.add(new HealingPlayer(this.player));
		comboCommands.add(new Attack(this.favorableEnvironments, this.player.getAttackLevel(), enemy));
		comboCommands.add(new HealingPlayer(this.player));
		comboCommands.add(new Attack(this.favorableEnvironments, this.player.getAttackLevel(), enemy));
		
		if (this.frostbite.isFrozen()) {
			this.frostbite.addCommands(comboCommands);
			return false;
		}

		comboCommands.forEach(Command::execute);
		return lifeBeforeAttack != enemy.getLife();
	}
	
	private boolean attack(final Enemy enemy) {
		final int lifeBeforeAttack = enemy.getLife();
		
		final Command attackCommand = new Attack(this.favorableEnvironments, this.player.getAttackLevel(), enemy);
		if (this.frostbite.isFrozen()) {
			this.frostbite.addCommand(attackCommand);
			return false;
		}

		attackCommand.execute();
		return lifeBeforeAttack != enemy.getLife();
	}

	@Override
	public void calculateFreezing() {
		this.frostbite.calculateFreezing();
	}
	
	@Override
	public boolean isFrozen() {
		return this.frostbite.isFrozen();
	}

	@Override
	public int getTurnsForDefrost() {
		return this.frostbite.getTurnsForDefrost();
	}

	@Override
	public void plusTurnFrozen() {
		this.frostbite.plusTurnFrozen();
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
		
        return "B=bomba,M=multiples disparos,F=fortaleza,V=veneno,A=aire,N=naval,S=soldado,E=escuadrón,J=Jefe\r\n"
        		+ "\r\n"
        		+ "tablero:{fila-columna:avatar:vida:ataque}\r\n"
        		+ "\r\n"
        		+ "enemigos: " + squares.toString() + "\r\n"
                + "\r\n"
        		+ "jugador: {X-X:" + this.player.getAvatar() + ":" + this.player.getLife() + ":" + this.player.getAttackLevel() + "}\r\n";
	}

	@Override
	public String getStringAvatarPlayer() {
		return "jugador: {X-X:" + this.player.getAvatar() + ":" + this.player.getLife() + ":" + this.player.getAttackLevel() + "}\r\n";
	}

	@Override
	public void removeDeadEnemies() {
		while (this.enemyIterator.hasNext()) {
			final Enemy enemy = this.enemyIterator.getNext();
			if (enemy.getLife() <= 0) {
	            this.enemyIterator.remove();
			}
        }
		this.enemyIterator.reset();
	}

	@Override
	public void healing() {
		final Visitor healable = new Healable();
		
		while (this.enemyIterator.hasNext()) {
			final Enemy enemy = this.enemyIterator.getNext();
			enemy.acceptVisit(healable);
        }
		this.enemyIterator.reset();
		
		final Command healingCommand = new HealingPlayer(this.player);
		if (this.frostbite.isFrozen()) {
			this.frostbite.addCommand(healingCommand);
		} else {
			healingCommand.execute();
		}
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

	private List<String> getAvatarSquares() {
		final List<String> avatarSquares = new ArrayList<>();
		while (this.enemyIterator.hasNext()) {
			avatarSquares.add(this.enemyIterator.getAvatarSquareNext());
        }
		this.enemyIterator.reset();
		return avatarSquares;
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

	@Override
	public boolean doOrRestoreBackup(String inputString) {
		inputString = inputString.substring("backup:".length());
		inputString = inputString.replaceAll("  ", " ");
		inputString = inputString.trim();
    	final String[] inputStringArray = inputString.split(" ");
    	if (inputStringArray.length >= 2) {
    		final String operation = inputStringArray[0];
    		final String key = inputStringArray[1];
    		
    		return switch(operation.toLowerCase()) {
	    		case "realizar" -> {
	    			final MementoPlayer mementoPlayer = this.player.doBackup();
	    			this.caretakerPlayer.addMementoPlayerByKey(key, mementoPlayer);
	    			yield true;
	    		}
	    		case "restaurar" -> {
	    			final MementoPlayer mementoPlayer = this.caretakerPlayer.getMementoPlayerByKey(key);
	    			if (mementoPlayer != null) {
		    			this.player.restoreMemento(mementoPlayer);
		    			yield true;
	    			}
	    			yield false;
	    		}
	    		default -> false;
    		};
    	}
    	
    	return false;
	}

}
