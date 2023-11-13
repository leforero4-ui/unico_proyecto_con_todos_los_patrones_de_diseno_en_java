package main.domain.model;

import java.util.List;

public class SquadronNaval extends Enemy {
	
	private final List<Enemy> squadron;
	
	public SquadronNaval(final List<Enemy> squadron, final Skillfull skill) {
		super(0, 0, skill);
		this.squadron = squadron;
	}

	@Override
	public int getLife() {
		this.life = 1;
		for (Enemy enemy : this.squadron) {
			this.life += enemy.getLife();
		}
		return super.getLife();
	}

	@Override
	public int getAttackLevel() {
		this.attackLevel = 1;
		for (Enemy enemy : this.squadron) {
			this.attackLevel += enemy.getAttackLevel();
		}
		return super.getAttackLevel();
	}

	@Override
	public void receiveAttack(int attack) {
		if (this.squadron.size() > 0) {
			this.squadron.get(this.squadron.size() - 1).receiveAttack(attack);
		}
	}

	@Override
	public String getAvatar(final String prefix) {
		StringBuilder avatarTemp = new StringBuilder();
		for (Enemy enemy : this.squadron) {
			avatarTemp.append(enemy.getAvatar(prefix + this.skill.getIdentifier() + "EN"));
		}
		return avatarTemp.toString();
	}

}
