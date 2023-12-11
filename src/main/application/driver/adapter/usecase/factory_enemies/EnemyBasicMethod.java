package main.application.driver.adapter.usecase.factory_enemies;

import java.util.ArrayList;
import java.util.List;

import main.application.driver.port.usecase.EnemyMethod;
import main.domain.model.ArmyFactory;
import main.domain.model.Enemy;
import main.domain.model.Poison;
import main.domain.model.Soldier;
import main.domain.model.Supreme;

public class EnemyBasicMethod implements EnemyMethod {
	private final ArmyFactory armyFactory;

	public EnemyBasicMethod(final ArmyFactory armyFactory) {
		this.armyFactory = armyFactory;
	}


	@Override
	public List<Enemy> createEnemies() {
		final List<Enemy> enemies = new ArrayList<Enemy>();
		
        final int lifeSoldier = 25;
        final int attackLevelSoldier = 5;
        final Soldier soldierEnemyBase = armyFactory.createSoldier(lifeSoldier, attackLevelSoldier, new Poison());
		
		// supreme
		Supreme supreme = armyFactory.getSupreme();
		enemies.add(supreme);

		// soldiers
        final int quantitySoldiers = 8;
		for (int created = 1; created <= quantitySoldiers; created++) {
			final Soldier soldierEnemy = soldierEnemyBase.clone();
			enemies.add(soldierEnemy);
			if (created <= Math.round(enemies.size() / 3)){
				supreme.addProtector(soldierEnemy);
			}
		}
		
		return enemies;
	}

}
