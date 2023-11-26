package main.domain.model;

import main.application.driver.port.usecase.GameableUseCase;

public abstract class Enemy {
	protected int life;
	protected int attackLevel;
	protected final Skillfull skill;
	
	public Enemy(int life, int attackLevel, Skillfull skill) {
		this.life = life;
		this.attackLevel = attackLevel;
		this.skill = skill;
	}

	public int getLife() {
		return this.life;
	}
	
	public int getAttackLevel() {
		return this.skill.getEnhancedAttackLevel(this.attackLevel);
	}
	
	public void receiveAttack(final int attack, GameableUseCase game) {
		this.calculateDamage(attack);
		if (this.life <= 0) {
			game.deleteEnemy(this);
		} else {
			game.counterAttack(this);
		}
	};
	
	protected abstract void calculateDamage(final int attack);
	
	public abstract String getAvatar(final String prefix);
}
