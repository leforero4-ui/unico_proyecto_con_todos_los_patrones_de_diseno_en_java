package main.domain.model;

import java.util.List;

public abstract class Squadron extends Enemy {
	
	protected final List<Enemy> squadronList;
	
	public Squadron(final List<Enemy> squadronList, final Skillfull skill) {
		super(0, 0, skill);
		this.squadronList = squadronList;
	}

	@Override
	public void acceptVisit(Visitor visitor) {
		visitor.visitSquadron(this);
	}
	
	protected void calculateLife() {
		this.life = 0;
		for (Enemy enemy : this.squadronList) {
			this.life += enemy.getLife();
		}
	}

	@Override
	public int getLife() {
		this.calculateLife();
		return super.getLife();
	}

	@Override
	public int getAttackLevel() {
		this.attackLevel = 0;
		for (Enemy enemy : this.squadronList) {
			this.attackLevel += enemy.getAttackLevel();
		}
		return super.getAttackLevel();
	}

	@Override
	public void receiveAttack(int attack) {
		if (this.squadronList.size() > 0) {
			this.squadronList.get(0).receiveAttack(attack);
			this.calculateLife();
		}
	}

}
