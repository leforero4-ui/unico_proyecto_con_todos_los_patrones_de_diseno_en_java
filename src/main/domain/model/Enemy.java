package main.domain.model;

import main.domain.model.status.Asleep;

public abstract class Enemy {
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
	
	public int getAttackLevel() {
		return this.skill.getEnhancedAttackLevel(this.attackLevel);
	}
	
	public int getCounterAttackLevel(final int attackLevelReceived) {
		return this.status.getAttackLevel(attackLevelReceived, this);
	}
	
	public void setStatus(final Status status) {
		this.status = status;
	}
	
	public abstract void receiveAttack(final int attack);
	
	public abstract String getAvatar(final String prefix);
	
	public abstract void acceptVisit(Visitor visitor);
}
