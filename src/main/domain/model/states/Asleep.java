package main.domain.model.states;

import main.domain.model.Enemy;
import main.domain.model.Status;

public class Asleep implements Status {

	@Override
	public int getAttackLevel(final int attackLevelReceived, final Enemy enemy) {
		if (attackLevelReceived != 0) {
			enemy.setStatus(new Confused());
		}
		return 0;
	}

}
