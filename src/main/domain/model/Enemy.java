package main.domain.model;

import main.domain.model.states.Asleep;

public abstract class Enemy implements Protective {
	protected int life;
	protected int attackLevel;
	protected final Skillfull skill;
	protected Status status;
	
	public Enemy(int life, int attackLevel, Skillfull skill) {
		this.life = life;
		this.attackLevel = attackLevel;
		this.skill = skill;
		this.status = new Asleep();
	}

	public int getLife() {
		return this.life;
	}
	
	public int getAttackLevel(boolean isAttacking) {
		return this.skill.getEnhancedAttackLevel(this.attackLevel, isAttacking);
	}
	
	public int getCounterAttackLevel(final int attackLevelReceived) {
		return this.status.getAttackLevel(attackLevelReceived, this);
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}
	
	@Override
	public void protect(final Supreme theProtected) {
		this.receiveAttack(1);
		if (this.getLife() <= 0) {
			theProtected.removeProtector(this);
		}
	}

	public abstract void receiveAttack(final int attack);
	
	public abstract String getAvatar(final String prefix);
	
	public abstract void acceptVisit(Visitor visitor);
}
