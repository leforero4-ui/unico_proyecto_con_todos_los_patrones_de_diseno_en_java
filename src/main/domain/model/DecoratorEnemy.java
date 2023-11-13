package main.domain.model;

public abstract class DecoratorEnemy extends Enemy {
	private Enemy enemy;
	
	public DecoratorEnemy(final Enemy enemy) {
		super(enemy.life, enemy.attackLevel, enemy.skill);
		this.enemy = enemy;
	}

	@Override
	public String getAvatar(String prefix) {
		return this.enemy.getAvatar(prefix);
	}

	@Override
	public void receiveAttack(int attack) {
		this.enemy.receiveAttack(attack);
	}
}
