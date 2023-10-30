package main.domain.model;

public class Fort extends Enemy {
	private Enemy enemy;

	public Fort(final Enemy enemy) {
		super(enemy.life, enemy.attackLevel, enemy.drawable, enemy.skill);
		this.enemy = enemy;
	}

	@Override
	public void draw() {
		this.drawable.out("enemigo con un fuerte:");
		enemy.draw();
	}

	@Override
	public void move(int direction) {
	}

}
