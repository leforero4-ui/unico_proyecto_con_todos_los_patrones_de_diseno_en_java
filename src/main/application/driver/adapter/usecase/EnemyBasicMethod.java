package main.application.driver.adapter.usecase;

import java.util.ArrayList;
import java.util.List;

import main.application.driven.port.provider.Drawable;
import main.application.driver.port.usecase.EnemyMethod;
import main.domain.model.ArmyFactory;
import main.domain.model.Enemy;
import main.domain.model.Soldier;

public class EnemyBasicMethod implements EnemyMethod {
	
	private final Drawable drawable;
	private final ArmyFactory armyFactory;

	public EnemyBasicMethod(final ArmyFactory armyFactory, Drawable drawable) {
		this.drawable = drawable;
		this.armyFactory = armyFactory;
	}


	@Override
	public List<Enemy> createEnemies() {
        final int lifeSoldier = 25;
        final int attackLevelSoldier = 5;
        final Soldier soldierEnemyBase = armyFactory.createSoldier(lifeSoldier, attackLevelSoldier, drawable);
        
		final List<Enemy> enemies = new ArrayList<Enemy>();

		// soldiers
        final int quantitySoldiers = 5;
		for (int created = 1; created <= quantitySoldiers; created++) {
			enemies.add(soldierEnemyBase.clone());
		}

		// supreme
		enemies.add(armyFactory.getSupreme(drawable));
		
		return enemies;
	}

}