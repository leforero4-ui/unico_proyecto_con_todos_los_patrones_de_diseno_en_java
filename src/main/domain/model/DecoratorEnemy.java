package main.domain.model;

public abstract class DecoratorEnemy extends Enemy {
	private Enemy enemy;
	
	public DecoratorEnemy(final Enemy enemy) {
		super(enemy.life, enemy.attackLevel, enemy.skill);
		this.enemy = enemy;
	}

	@Override
	public int getLife() {
		return this.enemy.getLife();
	}

	@Override
	public int getAttackLevel(boolean isAttacking) {
		return this.enemy.getAttackLevel(isAttacking);
	}

	@Override
	public int getCounterAttackLevel(final int attackLevelReceived) {
		return this.enemy.getCounterAttackLevel(attackLevelReceived);
	}

	@Override
	public void setStatus(final Status status) {
		this.enemy.setStatus(status);
	}
	
	@Override
	public void protect(final Supreme theProtected) {
		this.enemy.protect(theProtected);
	}

	@Override
	public String getAvatar(String prefix) {
		return this.enemy.getAvatar(prefix);
	}

	@Override
	public void receiveAttack(int attack) {
		this.enemy.receiveAttack(attack);
	}

	@Override
	public void acceptVisit(Visitor visitor) {
		this.enemy.acceptVisit(visitor);
	}
}
