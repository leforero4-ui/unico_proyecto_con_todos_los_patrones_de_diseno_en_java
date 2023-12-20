package main.domain.model.skill;

import main.domain.model.Skillfull;

public class MultipleShots implements Skillfull {
	private int numberOfSimultaneousShots;

	public MultipleShots(int numberOfSimultaneousShots) {
		this.numberOfSimultaneousShots = numberOfSimultaneousShots;
	}

	@Override
	public int getEnhancedAttackLevel(int attackLevel, boolean isAttacking) {
		return attackLevel + numberOfSimultaneousShots;
	}

	@Override
	public Skillfull getClone() {
		return new MultipleShots(this.numberOfSimultaneousShots);
	}

	@Override
	public String getIdentifier() {
		return "M";
	}

}
