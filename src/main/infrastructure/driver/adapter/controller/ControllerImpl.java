package main.infrastructure.driver.adapter.controller;

import main.application.driven.port.provider.Drawable;
import main.application.driver.adapter.usecase.Game;
import main.application.driver.adapter.usecase.factory_enemies.EnemyBasicMethod;
import main.application.driver.adapter.usecase.factory_enemies.EnemyHighMethod;
import main.application.driver.adapter.usecase.factory_enemies.EnemyMiddleMethod;
import main.application.driver.port.controller.Controller;
import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.GameableUseCase;
import main.domain.model.ArmyFactory;
import main.domain.model.Player;
import main.domain.model.PlayerBuilder;
import main.domain.model.factory.ArmyAirFactory;
import main.domain.model.factory.ArmyNavalFactory;
import main.infrastructure.driven.adapter.provider.LanternaDrawable;

public class ControllerImpl implements Controller {
	private GameableUseCase gameableUseCase;
	private final ArmyFactory armyFactory;
	private final Drawable drawable;
	private Player player;
	
	public ControllerImpl() {
		this.drawable = new LanternaDrawable();
		if("1".equalsIgnoreCase(this.drawable.in("naval oprima: 1\r\nair oprima: cualquier tecla"))) {
			this.drawable.out("naval:");
			this.armyFactory = new ArmyNavalFactory();
		} else {
			this.drawable.out("air:");
			this.armyFactory = new ArmyAirFactory();
		}
		this.setGameSetting();
	}
	
	private void setGameSetting() {
		final EnemyMethod enemyMethod = switch (this.drawable.in("nivel básico: 1\r\nnivel medio: 2\r\nnivel alto oprima: cualquier tecla")) {
			case "1" -> {
				this.drawable.out("básico:");
				yield new EnemyBasicMethod(armyFactory);
			}
			case "2" -> {
				this.drawable.out("medio:");
				yield new EnemyMiddleMethod(armyFactory);
			}
			default -> {
				this.drawable.out("alto:");
				yield new EnemyHighMethod(armyFactory);
			}
		};
		this.createPlayer();
		this.gameableUseCase = new Game(enemyMethod, this.player);
	}
	
	private void createPlayer() {
		this.drawable.out("crear jugador");
		final PlayerBuilder playerBuilder = new PlayerBuilder();
		playerBuilder.name(this.drawable.in("nombre:"));
		playerBuilder.typeEye(this.drawable.in("tipo de ojos:"));
		playerBuilder.typeHair(this.drawable.in("tipo de pelo:"));
		playerBuilder.typeShirt(this.drawable.in("tipo de camisa:"));
		playerBuilder.typePant(this.drawable.in("tipo de pantalón:"));
		playerBuilder.typeShoes(this.drawable.in("tipo de zapatos:"));
		this.player = this.armyFactory.createPlayer(playerBuilder);
	}
	
	@Override
	public void startGame() {
		this.gameableUseCase.startGame();
		
		String inputString;
		do {
			this.gameableUseCase.removeDeadEnemies();
			
			inputString = this.drawable.in(this.gameableUseCase.getStringAvatarSquares() + "\r\n"
					+ "elija fila y columna y escribalos separados por guión(-) para atacar\r\n"
					+ "puede agregarle un guión(-) y un código como recuperación o combo\r\n"
					+ "\r\n"
					+ "escriba 'buscar:' seguido de los tipos de enemigos a buscar, ejemplo: buscar: soldado y escuadron y (aire o naval)\r\n"
					+ "\r\n"
					+ "escriba 'backup:' para realizar/restaurar copias de seguridad, ejemplo: backup: realizar punto1\r\n"
					+ "y puede restaurar copias de seguridad, ejemplo: backup: restaurar punto1\r\n"
					+ "\r\n"
					+ "99-99 para terminar el juego");
			if (inputString != null) {
				if (inputString.contains("-") && !inputString.equalsIgnoreCase("99-99")) {
					boolean isFrozen = this.gameableUseCase.isFrozen();
					if (isFrozen) {
						this.gameableUseCase.plusTurnFrozen();
						isFrozen = this.gameableUseCase.isFrozen();
					} else {
						this.gameableUseCase.calculateFreezing();
						isFrozen = this.gameableUseCase.isFrozen();
						if (isFrozen) {
							this.drawable.out("Ha pisado una mina congeladora, sus ataque y sanaciones no se reflejaran hasta dentro de 5 turnos\r\n");	
						}
					}
					
					final String[] locationEnemySplit = inputString.split("-");
					final int row = Integer.parseInt(locationEnemySplit[0]);
					final int column = Integer.parseInt(locationEnemySplit[1]);
					boolean withCombo = false;
					if (locationEnemySplit.length > 2) {
						final String secretCode = inputString.split("-")[2];
						if (secretCode.equalsIgnoreCase("recuperación")) {
							this.gameableUseCase.healing();
							this.drawable.out(this.gameableUseCase.getStringAvatarSquares() + "\r\nSe ha sanado\r\ncontinuara el ataque");
						} else if (secretCode.equalsIgnoreCase("combo")) {
							withCombo = true;
						}
					}
					
					final Boolean[] isSuccessfulAttackAndIsEnemyEliminated;
					if (withCombo) {
						isSuccessfulAttackAndIsEnemyEliminated = this.gameableUseCase.attackWithComboAndCounterAttack(row, column);
					} else {
						isSuccessfulAttackAndIsEnemyEliminated = this.gameableUseCase.attackAndCounterAttack(row, column);
					}
					
					if (isFrozen) {
						this.drawable.out("Estas congelado por " + this.gameableUseCase.getTurnsForDefrost() + " turnos\r\n");
					} else {
						final boolean isSuccessfulAttack = isSuccessfulAttackAndIsEnemyEliminated[0];
						this.drawable.out(isSuccessfulAttack ? "Enemigo ha recibido el ataque\r\n" : "Enemigo ha esquivado el ataque\r\n");
					}
					
					final boolean isEnemyEliminated = isSuccessfulAttackAndIsEnemyEliminated[1];
					this.drawable.out(isEnemyEliminated ? "Enemigo eliminado\r\n" : "Se ha lanzado contraataque\r\n");
				} else if (inputString.startsWith("backup:")) {
					final String textSuccessfulBackup = this.gameableUseCase.doOrRestoreBackup(inputString) ? "satisfactorio" : "fallido";
					this.drawable.out(this.gameableUseCase.getStringAvatarPlayer() + "\r\nBackup " + textSuccessfulBackup);
				} else if (inputString.startsWith("buscar:")) {
					this.drawable.out(this.gameableUseCase.getEnemies(inputString));
				}
			}
		} while (inputString != null && !inputString.equalsIgnoreCase("99-99") && this.player.getLife() > 0);
		
		this.drawable.out("fin del juego");
	}

	@Override
	public void getAchievements() {
	}

	@Override
	public int getStatistics(final int level) {
		return 0;
	}

}
