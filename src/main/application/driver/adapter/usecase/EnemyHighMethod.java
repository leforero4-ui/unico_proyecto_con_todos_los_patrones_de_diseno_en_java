package main.application.driver.adapter.usecase;

import java.util.ArrayList;
import java.util.List;

import main.application.driven.port.provider.Drawable;
import main.application.driver.port.usecase.EnemyMethod;
import main.domain.model.ArmyFactory;
import main.domain.model.Enemy;
import main.domain.model.Fort;
import main.domain.model.Soldier;

public class EnemyHighMethod implements EnemyMethod {
	
	private final Drawable drawable;
	private final ArmyFactory armyFactory;

	public EnemyHighMethod(final ArmyFactory armyFactory, Drawable drawable) {
		this.drawable = drawable;
		this.armyFactory = armyFactory;
	}


	@Override
	public List<Enemy> createEnemies() {
        final int lifeSoldier = 25;
        final int attackLevelSoldier = 5;
        final Soldier soldierEnemyBase = armyFactory.createSoldier(lifeSoldier, attackLevelSoldier, drawable);
        
		final List<Enemy> enemies = new ArrayList<>();

		// soldiers
        final int quantitySoldiers = 5;
		for (int created = 1; created <= quantitySoldiers; created++) {
			enemies.add(new Fort(soldierEnemyBase.clone()));
		}
		
		// infantry
        final int quantitySquadron = 2;
        final int quantitySoldiersForSquadron = 3;
        final int quantitySoldiersInSquadron = 3;
		final List<Enemy> squadronsAndSoldiers = new ArrayList<>();
		for (int createdSquadron = 1; createdSquadron <= quantitySquadron; createdSquadron++) {
			final List<Enemy> soldiers = new ArrayList<>();
			for (int created = 1; created <= quantitySoldiersForSquadron; created++) {
				soldiers.add(soldierEnemyBase.clone());
			}
			final Enemy squadron = armyFactory.createSquadron(soldiers, drawable);
			squadronsAndSoldiers.add(squadron);
		}
		for (int created = 1; created <= quantitySoldiersInSquadron; created++) {
			squadronsAndSoldiers.add(soldierEnemyBase.clone());
		}
		final Enemy infantry = armyFactory.createSquadron(squadronsAndSoldiers, drawable);
		enemies.add(infantry);
		
		// supreme
		enemies.add(armyFactory.getSupreme(drawable));
		
		return enemies;
	}

}
