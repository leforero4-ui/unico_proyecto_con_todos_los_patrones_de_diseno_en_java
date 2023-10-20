package main.application.driver.adapter.usecase;

import java.util.ArrayList;
import java.util.List;

import main.application.driven.port.provider.Drawable;
import main.application.driver.port.usecase.EnemyMethod;
import main.domain.model.Enemy;
import main.domain.model.SoldierEnemy;
import main.domain.model.Squadron;
import main.domain.model.SupremeEnemy;

public class EnemyBasicMethod implements EnemyMethod {
	
	private final Drawable drawable;

	public EnemyBasicMethod(Drawable drawable) {
		this.drawable = drawable;
	}


	@Override
	public List<Enemy> createEnemies() {
        final int lifeSoldier = 25;
        final int attackLevelSoldier = 5;
        final SoldierEnemy soldierEnemyBase = new SoldierEnemy(lifeSoldier, attackLevelSoldier, drawable);
        
		final List<Enemy> enemies = new ArrayList<Enemy>();

		// soldiers
        final int quantitySoldiers = 5;
		for (int created = 1; created <= quantitySoldiers; created++) {
			enemies.add(soldierEnemyBase.clone());
		}
		
		// infantry
        final int quantitySquadron = 2;
        final int quantitySoldiersForSquadron = 3;
        final int quantitySoldiersInSquadron = 3;
		final List<Enemy> squadronsAndSoldiers = new ArrayList<Enemy>();
		for (int createdSquadron = 1; createdSquadron <= quantitySquadron; createdSquadron++) {
			final List<Enemy> soldiers = new ArrayList<Enemy>();
			for (int created = 1; created <= quantitySoldiersForSquadron; created++) {
				soldiers.add(soldierEnemyBase.clone());
			}
			final Enemy squadron = new Squadron(soldiers, drawable);
			squadronsAndSoldiers.add(squadron);
		}
		for (int created = 1; created <= quantitySoldiersInSquadron; created++) {
			squadronsAndSoldiers.add(soldierEnemyBase.clone());
		}
		final Enemy infantry = new Squadron(squadronsAndSoldiers, drawable);
		enemies.add(infantry);
		
		// supreme
		enemies.add(SupremeEnemy.getInstance(drawable));
		
		return enemies;
	}

}
