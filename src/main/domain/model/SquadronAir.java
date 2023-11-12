package main.domain.model;

import java.util.List;

public class SquadronAir extends Enemy {
	
	private final List<Enemy> squadron;
	
	public SquadronAir(final List<Enemy> squadron) {
		super(0, 0, new Bang(4));
		this.squadron = squadron;
		for (Enemy enemy : this.squadron) {
			this.life += enemy.getLife();
		}
		for (Enemy enemy : this.squadron) {
			this.attackLevel += enemy.getAttackLevel();
		}
	}

	@Override
	public String getAvatar(final String prefix) {
		StringBuilder avatarTemp = new StringBuilder();
		for (Enemy enemy : this.squadron) {
			avatarTemp.append(enemy.getAvatar(prefix + "EA"));
		}
		return avatarTemp.toString();
	}

	@Override
	public void move(int direction) {
		// TODO Auto-generated method stub
	}

}
