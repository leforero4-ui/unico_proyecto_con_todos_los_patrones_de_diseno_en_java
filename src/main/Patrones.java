package main;

import main.infrastructure.controller.Controller;
import main.application.driver.adapter.usecase.GameableBasicUseCase;
import main.application.driver.port.usecase.GameableUseCase;
import main.application.driven.port.provider.Drawable;
import main.infrastructure.driven.adapter.provider.ConsoleDrawable;

public class Patrones {

	public static void main(String[] args) {
		final Drawable drawable = new ConsoleDrawable();
		final GameableUseCase gameableUseCase = new GameableBasicUseCase(drawable);
		
		final Controller controller = new Controller(gameableUseCase);
		controller.startGame();
	}

}
