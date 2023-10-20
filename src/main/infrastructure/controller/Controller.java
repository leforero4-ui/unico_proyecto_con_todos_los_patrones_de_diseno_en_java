package main.infrastructure.controller;

import main.application.driver.port.usecase.GameableUseCase;

public class Controller {
	
	private final GameableUseCase gameableUseCase;
	
	public Controller(final GameableUseCase gameableUseCase) {
		this.gameableUseCase = gameableUseCase;
	}
	
	public void startGame() {
		this.gameableUseCase.startGame();
	}

}
