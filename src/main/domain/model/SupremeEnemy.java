package main.domain.model;

import main.application.driven.port.provider.Drawable;
import main.domain.exception.CreationSupremeEnemyException;

public class SupremeEnemy implements Enemy {
	private int life;
	private int attackLevel;
	private final Drawable drawable;
	
	private static SupremeEnemy instance;
	
	private SupremeEnemy(final Drawable drawable) {
		this.life = 200;
		this.attackLevel = 15;
		this.drawable = drawable;
	}
	
	public static SupremeEnemy getInstance(final Drawable drawable) {
		if(instance == null) {
			instance = new SupremeEnemy(drawable);
		} else if (instance.drawable != drawable) {
			throw new CreationSupremeEnemyException();
		}
		
		return instance;
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
		this.drawable.out("SupremeEnemy [life=" + life + ", attackLevel=" + attackLevel + "]");
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
	}

}
