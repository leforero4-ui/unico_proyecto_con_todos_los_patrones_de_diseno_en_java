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
		return this.attackLevel;
	}
	
	public void activeSkill() {
		this.skill.activeSkill();
	}
	
	public abstract String getAvatar(final String prefix);
	public abstract void move(int direction);
}
