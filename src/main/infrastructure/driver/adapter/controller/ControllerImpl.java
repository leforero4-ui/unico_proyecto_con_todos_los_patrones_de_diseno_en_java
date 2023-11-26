package main.infrastructure.driver.adapter.controller;

import main.application.driven.port.provider.Drawable;
import main.application.driver.adapter.usecase.EnemyBasicMethod;
import main.application.driver.adapter.usecase.EnemyHighMethod;
import main.application.driver.adapter.usecase.EnemyMiddleMethod;
import main.application.driver.adapter.usecase.Game;
import main.application.driver.port.controller.Controller;
import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.GameableUseCase;
import main.domain.model.ArmyAirFactory;
import main.domain.model.ArmyFactory;
import main.domain.model.ArmyNavalFactory;
import main.domain.model.Player;
import main.domain.model.PlayerBuilder;
import main.infrastructure.driven.adapter.provider.LanternaDrawable;

public class ControllerImpl implements Controller {

	private GameableUseCase gameableUseCase;
	private final ArmyFactory armyFactory;
	private final Drawable drawable;
	
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

		this.gameableUseCase = new Game(enemyMethod, this.createPlayer());
	}
	
	private Player createPlayer() {
		this.drawable.out("crear jugador");
		final PlayerBuilder playerBuilder = new PlayerBuilder();
		playerBuilder.name(this.drawable.in("nombre:"));
		playerBuilder.typeEye(this.drawable.in("tipo de ojos:"));
		playerBuilder.typeHair(this.drawable.in("tipo de pelo:"));
		playerBuilder.typeShirt(this.drawable.in("tipo de camisa:"));
		playerBuilder.typePant(this.drawable.in("tipo de pantalón:"));
		playerBuilder.typeShoes(this.drawable.in("tipo de zapatos:"));
		
		return armyFactory.createPlayer(playerBuilder);
	}
	
	@Override
	public void startGame() {
		String squares = this.gameableUseCase.startGame() + "\r\n";
		String locationEnemy;
		do {
			locationEnemy = this.drawable.in(squares + "elija fila y columna separado por guión(-) para atacar(99-99 para terminar):");
			if (locationEnemy != null && locationEnemy.contains("-")) {
				final int row = Integer.parseInt(locationEnemy.split("-")[0]);
				final int column = Integer.parseInt(locationEnemy.split("-")[1]);
				squares = this.gameableUseCase.attack(row, column) + "\r\n";
			}
		} while (locationEnemy != "99-99");
	}

	@Override
	public void getAchievements() {
	}

	@Override
	public int getStatistics(final int level) {
		return 0;
	}

}
