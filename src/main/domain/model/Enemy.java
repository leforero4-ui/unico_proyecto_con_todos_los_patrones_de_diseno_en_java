package main.domain.model;

import main.application.driven.port.provider.Drawable;

public abstract class Enemy {
	protected int life;
	protected int attackLevel;
	protected final Drawable drawable;
	protected final Skillfull skill;
	
	public Enemy(int life, int attackLevel, Drawable drawable, Skillfull skill) {
		this.life = life;
		this.attackLevel = attackLevel;
		this.drawable = drawable;
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
	
	public abstract void draw();
	public abstract void move(int direction);
}
