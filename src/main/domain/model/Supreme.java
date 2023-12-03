package main.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Supreme extends Enemy {
	
	private final List<Enemy> protectors;
	
	protected Supreme(int life, int attackLevel, Skillfull skill) {
		super(life, attackLevel, skill);
		this.protectors = new ArrayList<Enemy>();
	}

	@Override
	public void receiveAttack(int attack) {
		this.life -= this.notifyAttackToProtectors(attack);
	}
	
	public void addProtector(Enemy enemy) {
		this.protectors.add(enemy);
	}
	
	private int notifyAttackToProtectors(int attack) {
		final List<Enemy> enemyDeletes = new ArrayList<>();
		final AtomicInteger remnantAttack = new AtomicInteger(attack);
		protectors.forEach(enemy -> {
			if (enemy.getLife() > 0) {
				enemy.receiveAttack(1);
				remnantAttack.set(remnantAttack.get() - 1);
			}
			if (enemy.getLife() <= 0) {
				enemyDeletes.add(enemy);
			}
		});
		protectors.removeAll(enemyDeletes);
		return remnantAttack.get();
	}

}
