package main.domain.model;

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
	
	public abstract void receiveAttack(final int attack);
	
	public abstract String getAvatar(final String prefix);
	
	public abstract void acceptVisit(Visitor visitor);
}
