package main.domain.model.environment;

import java.util.ArrayList;
import java.util.List;

import main.domain.model.Enemy;

public class Jungle extends BaseFavorableEnvironment {
	private List<Enemy> enemiesCache;
	
	public Jungle(final int probability) {
		super(probability);
		this.enemiesCache = new ArrayList<>();
	}

	@Override
	public boolean canHandleAttack(final Enemy enemy) {
		if (this.enemiesCache.contains(enemy)) {
			this.enemiesCache.remove(this.enemiesCache.size() - 1);
			return true;
		}
		
		this.enemiesCache.add(enemy);
		return false;
	}

	@Override
	protected boolean calculateIfCanAttack() {
		if (this.enemiesCache.isEmpty()) {
			return false;
		}
		return this.calculateIfCanAttackByProbability();
	}

}
