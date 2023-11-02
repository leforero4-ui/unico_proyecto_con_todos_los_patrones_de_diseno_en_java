package main;

import main.infrastructure.driver.adapter.controller.ControllerProxy;
import main.application.driver.port.controller.Controller;

public class Patrones {

	public static void main(String[] args) {
		final boolean beta = false;
		final Controller controller = new ControllerProxy(null, beta);
		controller.startGame();
	}

}
