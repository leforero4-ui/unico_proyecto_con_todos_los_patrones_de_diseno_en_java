package main.domain.model;

import java.util.List;

import main.application.driven.port.provider.Drawable;

public class SquadronNaval extends Enemy {
	
	private final List<Enemy> squadron;
	
	public SquadronNaval(final List<Enemy> squadron, final Drawable drawable) {
		super(0, 0, drawable, new Bang(4));
		this.squadron = squadron;
		for (Enemy enemy : this.squadron) {
			this.life += enemy.getLife();
		}
		for (Enemy enemy : this.squadron) {
			this.attackLevel += enemy.getAttackLevel();
		}
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
