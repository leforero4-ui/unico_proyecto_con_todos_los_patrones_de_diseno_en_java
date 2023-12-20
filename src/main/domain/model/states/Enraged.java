package main.domain.model.states;

import main.domain.model.Enemy;
import main.domain.model.Status;

public class Enraged extends StateChangeCondition implements Status {

	@Override
	public int getAttackLevel(final int attackLevelReceived, final Enemy enemy) {
		++this.timesAttacked;
		if (this.down(attackLevelReceived)) {
			enemy.setStatus(new Enraged());
		}
		return enemy.getAttackLevel(true);
	}

}
