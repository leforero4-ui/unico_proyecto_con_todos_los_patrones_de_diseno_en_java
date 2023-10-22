package main.domain.model;

import java.util.List;

import main.application.driven.port.provider.Drawable;

public class SquadronAir implements Enemy {
	
	private final List<Enemy> squadron;
	private final Drawable drawable;
	
	public SquadronAir(final List<Enemy> squadron, final Drawable drawable) {
		this.squadron = squadron;
		this.drawable = drawable;
	}

	@Override
	public int getLife() {
		int life = 0;
		for (Enemy enemy : this.squadron) {
			life += enemy.getLife();
		}
		return life;
	}

	@Override
	public int getAttackLevel() {
		int attackLevel = 0;
		for (Enemy enemy : this.squadron) {
			attackLevel += enemy.getAttackLevel();
		}
		return attackLevel;
	}

	@Override
	public void draw() {
		this.drawable.out("Init squadron air:");
		for (Enemy enemy : this.squadron) {
			enemy.draw();
		}
		this.drawable.out("End squadron air.");
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
	}

}
