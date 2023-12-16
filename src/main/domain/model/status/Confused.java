package main.domain.model.status;

import main.domain.model.Enemy;
import main.domain.model.Status;

public class Confused extends StateChangeCondition implements Status {

	@Override
	public int getAttackLevel(final int attackLevelReceived, final Enemy enemy) {
		++this.timesAttacked;
		if (this.up(attackLevelReceived)) {
			enemy.setStatus(new Enraged());
		} else if (this.down(attackLevelReceived)) {
			enemy.setStatus(new Asleep());
		}
		return enemy.getAttackLevel() / 2;
	}

}
