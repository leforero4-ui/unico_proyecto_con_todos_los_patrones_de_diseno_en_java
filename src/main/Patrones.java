package main;

import main.infrastructure.driver.adapter.controller.ControllerImpl;
import main.application.driver.port.controller.Controller;

public class Patrones {

	public static void main(String[] args) {		
		final Controller controller = new ControllerImpl();
		controller.startGame();
	}

}
