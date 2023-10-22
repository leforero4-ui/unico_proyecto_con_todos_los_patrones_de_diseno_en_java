package main.domain.model;

import main.application.driven.port.provider.Drawable;

public class SoldierNaval implements Soldier {
	private int life;
	private int attackLevel;
	private final Drawable drawable;

	public SoldierNaval(final int life, final int attackLevel, final Drawable drawable) {
		this.life = life;
		this.attackLevel = attackLevel;
		this.drawable = drawable;
	}

	@Override
	public SoldierNaval clone() {
		return new SoldierNaval(this.life, this.attackLevel, this.drawable);
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public int getAttackLevel() {
		return attackLevel;
	}

	@Override
	public void draw() {
		this.drawable.out("SoldierNaval [life=" + life + ", attackLevel=" + attackLevel + "]");
	}

	@Override
	public void move(final int direction) {
		// TODO Auto-generated method stub
	}
	
	
}
