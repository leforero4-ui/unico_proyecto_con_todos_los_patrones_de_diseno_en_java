package main.domain.model.status;

public abstract class StateChangeCondition {
	protected int timesAttacked = 0;

	protected boolean up(final int attackLevelReceived) {
		if (timesAttacked >= 3) {
			return false;
		}
		return attackLevelReceived > 20;
	}

	protected boolean down(final int attackLevelReceived) {
		if (timesAttacked >= 2) {
			return false;
		}
		return attackLevelReceived <= 20;
	}

}
