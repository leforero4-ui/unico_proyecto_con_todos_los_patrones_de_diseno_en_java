package main.infrastructure.driver.adapter.controller;

import main.application.driven.port.provider.Drawable;
import main.application.driver.adapter.usecase.EnemyBasicMethod;
import main.application.driver.adapter.usecase.EnemyMiddleMethod;
import main.application.driver.adapter.usecase.Game;
import main.application.driver.port.controller.Controller;
import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.GameableUseCase;
import main.domain.model.ArmyAirFactory;
import main.domain.model.ArmyFactory;
import main.domain.model.ArmyNavalFactory;
import main.infrastructure.driven.adapter.provider.LanternaDrawable;

public class ControllerImpl implements Controller {

	private GameableUseCase gameableUseCase;
	
	public ControllerImpl() {
		this.setGameSetting();
	}
	
	private void setGameSetting() {
		final Drawable drawable = new LanternaDrawable();
		
		final ArmyFactory armyFactory;
		if(drawable.in("naval oprima: 1\r\nair oprima: cualquier tecla") == "1") {
			drawable.out("naval:");
			armyFactory = new ArmyNavalFactory();
		} else {
			drawable.out("air:");
			armyFactory = new ArmyAirFactory();
		}

		final EnemyMethod enemyMethod;
		if(drawable.in("nivel básico: 1\r\nnivel medio oprima: cualquier tecla") == "1") {
			drawable.out("básico:");
			enemyMethod = new EnemyBasicMethod(armyFactory, drawable);
		} else {
			drawable.out("medio:");
			enemyMethod = new EnemyMiddleMethod(armyFactory, drawable);
		}

		this.gameableUseCase = new Game(enemyMethod, drawable);
	}
	
	@Override
	public void startGame() {
		this.gameableUseCase.startGame();
	}

	@Override
	public void getAchievements() {
	}

	@Override
	public void getstatistics() {
	}

}
