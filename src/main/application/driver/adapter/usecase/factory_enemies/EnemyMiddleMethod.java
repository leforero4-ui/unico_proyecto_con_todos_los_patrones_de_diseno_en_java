package main.application.driver.adapter.usecase.factory_enemies;

import java.util.ArrayList;
import java.util.List;

import main.application.driver.port.usecase.EnemyMethod;
import main.domain.model.ArmyFactory;
import main.domain.model.Enemy;
import main.domain.model.FavorableEnvironment;
import main.domain.model.Soldier;
import main.domain.model.Supreme;
import main.domain.model.environment.City;
import main.domain.model.environment.Cold;
import main.domain.model.environment.Heat;
import main.domain.model.environment.Jungle;
import main.domain.model.skill.Bang;
import main.domain.model.skill.MultipleShots;
import main.domain.model.skill.Poison;

public class EnemyMiddleMethod implements EnemyMethod {
	private final ArmyFactory armyFactory;

	public EnemyMiddleMethod(final ArmyFactory armyFactory) {
		this.armyFactory = armyFactory;
	}


	@Override
	public List<Enemy> createEnemies() {
		final List<Enemy> enemies = new ArrayList<>();
		
        final int lifeSoldier = 25;
        final int attackLevelSoldier = 5;
        final Soldier soldierEnemyBase = armyFactory.createSoldier(lifeSoldier, attackLevelSoldier, new Bang(2));
		
		// supreme
		Supreme supreme = armyFactory.getSupreme();
		enemies.add(supreme);

		// soldiers
        final int quantitySoldiers = 6;
		for (int created = 1; created <= quantitySoldiers; created++) {
			final Soldier soldierEnemy = soldierEnemyBase.clone();
			enemies.add(soldierEnemy);
			if (created <= Math.round(enemies.size() / 2)){
				supreme.addProtector(soldierEnemy);
			}
		}
		
		// infantry
        final int quantitySquadron = 2;
        final int quantitySoldiersForSquadron = 3;
		final List<Enemy> squadronsAndSoldiers = new ArrayList<>();
		for (int createdSquadron = 1; createdSquadron <= quantitySquadron; createdSquadron++) {
			final List<Enemy> soldiers = new ArrayList<>();
			for (int created = 1; created <= quantitySoldiersForSquadron; created++) {
				soldiers.add(soldierEnemyBase.clone());
			}
			final Enemy squadron = armyFactory.createSquadron(soldiers, new Poison());
			squadronsAndSoldiers.add(squadron);
		}
        final int quantitySoldiersInSquadron = 3;
		for (int created = 1; created <= quantitySoldiersInSquadron; created++) {
			squadronsAndSoldiers.add(soldierEnemyBase.clone());
		}
		final Enemy infantry = armyFactory.createSquadron(squadronsAndSoldiers, new MultipleShots(2));
		enemies.add(infantry);
		
		return enemies;
	}


	@Override
	public FavorableEnvironment createFavorableEnvironments() {
		final FavorableEnvironment favorableEnvironmentFirst = new Cold();
		final FavorableEnvironment favorableEnvironmentSecond = new Jungle(12);
		final FavorableEnvironment favorableEnvironmentThird = new City(83);
		final FavorableEnvironment favorableEnvironmentFourth = new Heat();

		favorableEnvironmentFirst.setNextFavorableEnvironment(favorableEnvironmentSecond);
		favorableEnvironmentSecond.setNextFavorableEnvironment(favorableEnvironmentThird);
		favorableEnvironmentThird.setNextFavorableEnvironment(favorableEnvironmentFourth);
		
		return favorableEnvironmentFirst;
	}

}
