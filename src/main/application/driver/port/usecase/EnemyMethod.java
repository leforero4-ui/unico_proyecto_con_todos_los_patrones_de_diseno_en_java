package main.application.driver.port.usecase;

import java.util.List;

import main.domain.model.Enemy;
import main.domain.model.FavorableEnvironment;

public interface EnemyMethod {
	List<Enemy> createEnemies();
	FavorableEnvironment createFavorableEnvironments();
}
