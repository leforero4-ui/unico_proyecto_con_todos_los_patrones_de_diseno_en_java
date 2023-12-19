package main.domain.model.environment;

import java.util.Random;

import main.domain.model.Enemy;
import main.domain.model.FavorableEnvironment;

public abstract class BaseFavorableEnvironment implements FavorableEnvironment {
	private FavorableEnvironment nextFavorableEnvironment;
	protected final int probability; 

	public BaseFavorableEnvironment(int probability) {
		if (probability <= 0) {
			this.probability = 0;
		} else if (probability >= 100) {
			this.probability = 100;
		} else {
			this.probability = probability;
		}
	}

	@Override
	public void setNextFavorableEnvironment(FavorableEnvironment favorableEnvironment) {
		this.nextFavorableEnvironment = favorableEnvironment;
	}

	@Override
	public boolean canAttack(Enemy enemy) {
		if (this.canHandleAttack(enemy)) {
			return this.calculateIfCanAttack();
		} else if (this.nextFavorableEnvironment != null) {
			return this.nextFavorableEnvironment.canAttack(enemy);
		} else {
			return true;
		}
	}
	
	protected abstract boolean calculateIfCanAttack();
	
	protected boolean calculateIfCanAttackByProbability() {
		if (this.probability == 0) {
			return false;
		}
		if (this.probability == 100) {
			return true;
		}
		
		final int numberRandom = new Random().nextInt(100) + 1;
		return numberRandom <= this.probability;
	}

}
