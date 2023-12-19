package main.domain.model.environment;

import main.domain.model.Enemy;

public class Rainy extends BaseFavorableEnvironment {
	
	public Rainy() {
		super(50);
	}

	@Override
	public boolean canHandleAttack(final Enemy enemy) {
		if (enemy.getLife() > 30) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean calculateIfCanAttack() {
		return !this.calculateIfCanAttackByProbability();
	}

}
