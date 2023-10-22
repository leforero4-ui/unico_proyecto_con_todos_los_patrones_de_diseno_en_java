package main;

import main.application.driver.adapter.usecase.GameableBasicUseCase;
import main.application.driven.port.provider.Drawable;
import main.application.driver.port.usecase.GameableUseCase;
import main.domain.model.ArmyAirFactory;
import main.domain.model.ArmyFactory;
import main.domain.model.ArmyNavalFactory;
import main.infrastructure.controller.Controller;
import main.infrastructure.driven.adapter.provider.ConsoleDrawable;

public class Patrones {

	public static void main(String[] args) {
		final Drawable drawable = new ConsoleDrawable();

		final ArmyFactory armyFactory;
		if(drawable.in("naval oprima: 1\r\nair oprima: cualquier tecla") == "1") {
			drawable.out("naval:");
			armyFactory = new ArmyNavalFactory();
		} else {
			drawable.out("air:");
			armyFactory = new ArmyAirFactory();
		}
		
		final GameableUseCase gameableUseCase = new GameableBasicUseCase(armyFactory, drawable);
		
		final Controller controller = new Controller(gameableUseCase);
		controller.startGame();
	}

}
