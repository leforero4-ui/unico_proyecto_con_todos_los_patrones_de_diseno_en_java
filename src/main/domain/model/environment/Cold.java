package main.domain.model.environment;

import main.domain.model.Enemy;
import main.domain.model.Fort;

public class Cold extends BaseFavorableEnvironment {
	
	public Cold() {
		super(25);
	}

	@Override
	public boolean canHandleAttack(final Enemy enemy) {
		if (enemy instanceof Fort) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean calculateIfCanAttack() {
		return this.calculateIfCanAttackByProbability();
	}

}
