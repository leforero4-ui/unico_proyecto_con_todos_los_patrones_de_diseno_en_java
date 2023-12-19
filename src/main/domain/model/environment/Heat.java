package main.domain.model.environment;

import main.domain.model.Enemy;
import main.domain.model.Soldier;

public class Heat extends BaseFavorableEnvironment {
	
	public Heat() {
		super(75);
	}

	@Override
	public boolean canHandleAttack(final Enemy enemy) {
		if (enemy instanceof Soldier) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean calculateIfCanAttack() {
		return this.calculateIfCanAttackByProbability();
	}

}
