package main.domain.model;

import java.util.List;

import main.application.driven.port.provider.Drawable;

public class SquadronNaval implements Enemy {
	
	private final List<Enemy> squadron;
	private final Drawable drawable;
	
	public SquadronNaval(final List<Enemy> squadron, final Drawable drawable) {
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
		this.drawable.out("Init squadron naval:");
		for (Enemy enemy : this.squadron) {
			enemy.draw();
		}
		this.drawable.out("End squadron naval.");
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
	}

}
