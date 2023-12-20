package main.domain.model.command;

import main.domain.model.Command;
import main.domain.model.Enemy;
import main.domain.model.FavorableEnvironment;
import main.domain.model.Player;

public class Attack implements Command {
	private final FavorableEnvironment favorableEnvironments;
	private final Player player;
	private final Enemy enemy;
	
	public Attack(final FavorableEnvironment favorableEnvironments, final Player player, final Enemy enemy) {
		this.favorableEnvironments = favorableEnvironments;
		this.player = player;
		this.enemy = enemy;
	}

	@Override
	public void execute() {
		if (this.enemy.getLife() > 0) {
			final boolean isSuccessfulAttack = this.favorableEnvironments.canAttack(this.enemy);
			if (isSuccessfulAttack) {
				this.enemy.receiveAttack(this.player.getAttackLevel());
			}
		}
	}

}
