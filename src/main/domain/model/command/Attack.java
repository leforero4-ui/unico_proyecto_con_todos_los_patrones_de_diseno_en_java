package main.domain.model.command;

import main.domain.model.Command;
import main.domain.model.Enemy;
import main.domain.model.FavorableEnvironment;

public class Attack implements Command {
	private final FavorableEnvironment favorableEnvironments;
	private final int playerAttackLevel;
	private final Enemy enemy;

	//sus propiedades deben ser inmutables ya que se podría ejecutar en cualquier momento y no deben haber cambiado
	//por lo tanto tener cuidado con los objetos pasados por parámetros y sus propiedades que no cambien hasta ser ejecutado el comando
	public Attack(final FavorableEnvironment favorableEnvironments, final int playerAttackLevel, final Enemy enemy) {
		this.favorableEnvironments = favorableEnvironments;
		this.playerAttackLevel = playerAttackLevel;
		this.enemy = enemy;
	}

	@Override
	public void execute() {
		if (this.enemy.getLife() > 0) { //esta propiedad como excepción a la regla se permite ser mutable ya que cuando se ejecute el comando el enemigo ya podría estar muerto
			final boolean isSuccessfulAttack = this.favorableEnvironments.canAttack(this.enemy);
			if (isSuccessfulAttack) {
				this.enemy.receiveAttack(this.playerAttackLevel);
			}
		}
	}

}
