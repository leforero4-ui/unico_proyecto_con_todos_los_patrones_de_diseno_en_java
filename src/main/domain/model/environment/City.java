package main.domain.model.environment;

import main.domain.model.Enemy;
import main.domain.model.Squadron;

public class City extends BaseFavorableEnvironment {
	
	public City(final int probability) {
		super(probability);
	}

	@Override
	public boolean canHandleAttack(final Enemy enemy) {
		if (enemy instanceof Squadron) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean calculateIfCanAttack() {
		if (this.probability > 75) {
			return true;
		}
		return this.calculateIfCanAttackByProbability();
	}

}
