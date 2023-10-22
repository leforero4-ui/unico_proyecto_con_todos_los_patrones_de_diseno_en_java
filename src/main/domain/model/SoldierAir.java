package main.domain.model;

import main.application.driven.port.provider.Drawable;

public class SoldierAir implements Soldier {
	private int life;
	private int attackLevel;
	private final Drawable drawable;

	public SoldierAir(final int life, final int attackLevel, final Drawable drawable) {
		this.life = life;
		this.attackLevel = attackLevel;
		this.drawable = drawable;
	}

	@Override
	public SoldierAir clone() {
		return new SoldierAir(this.life, this.attackLevel, this.drawable);
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
		this.drawable.out("SoldierAir [life=" + life + ", attackLevel=" + attackLevel + "]");
	}

	@Override
	public void move(final int direction) {
		// TODO Auto-generated method stub
	}
	
	
}
