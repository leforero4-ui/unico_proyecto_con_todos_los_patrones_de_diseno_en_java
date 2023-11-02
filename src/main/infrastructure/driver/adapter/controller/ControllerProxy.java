package main.infrastructure.driver.adapter.controller;

import java.util.HashMap;
import java.util.Map;

import main.application.driver.port.controller.Controller;

public class ControllerProxy implements Controller {
	private Controller controller;
	private final Map<Integer, Integer> statistics;
	private final boolean beta;
	
	public ControllerProxy(final Controller controller, final boolean beta) {
		this.statistics = new HashMap<>();
		this.controller = controller;
		this.beta = beta;
	}
	
	private void init() {
		if (this.controller == null) {
			this.controller = new ControllerImpl();
		}
	}

	@Override
	public void startGame() {
		this.init();
		this.controller.startGame();
	}

	@Override
	public void getAchievements() {
		if (beta) {
			return;
		}
		this.init();
		this.controller.getAchievements();
	}

	@Override
	public int getStatistics(int level) {
		this.init();
        if (this.statistics.containsKey(level)) {
            return this.statistics.get(level);
        }

        int resultado = this.controller.getStatistics(level);
        this.statistics.put(level, resultado);
        return resultado;
	}

}
