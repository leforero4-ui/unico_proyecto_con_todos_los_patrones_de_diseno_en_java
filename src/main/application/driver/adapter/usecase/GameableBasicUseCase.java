package main.application.driver.adapter.usecase;

import java.util.List;

import main.application.driven.port.provider.Drawable;
import main.application.driver.port.usecase.EnemyMethod;
import main.application.driver.port.usecase.GameableUseCase;
import main.domain.model.ArmyFactory;
import main.domain.model.Enemy;
import main.domain.model.Player;
import main.domain.model.PlayerBuilder;

public class GameableBasicUseCase implements GameableUseCase {
	
	private final Drawable drawable;
	private final ArmyFactory armyFactory;
	private final EnemyMethod enemyMethod;
	
	public GameableBasicUseCase(final ArmyFactory armyFactory, final Drawable drawable) {
		this.drawable = drawable;
		this.armyFactory = armyFactory;
		this.enemyMethod = new EnemyBasicMethod(this.armyFactory, this.drawable);
	}

	@Override
	public void startGame() {
		this.drawable.out("crear jugador");
		final PlayerBuilder playerBuilder = new PlayerBuilder(this.drawable);
		playerBuilder.name(this.drawable.in("nombre:"));
		playerBuilder.typeEye(this.drawable.in("tipo de ojos:"));
		playerBuilder.typeHair(this.drawable.in("tipo de pelo:"));
		playerBuilder.typeShirt(this.drawable.in("tipo de camisa:"));
		playerBuilder.typePant(this.drawable.in("tipo de pantalón:"));
		playerBuilder.typeShoes(this.drawable.in("tipo de zapatos:"));
		final Player player = playerBuilder.build();
		player.draw();
		
		final List<Enemy> enemies = enemyMethod.createEnemies();
		for (final Enemy enemy : enemies) {
			enemy.draw();
		}
	}

}
