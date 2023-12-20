package main.domain.model.skill;

import main.domain.model.Skillfull;

public class Poison implements Skillfull {
	private int substance = 100;

	@Override
	public int getEnhancedAttackLevel(int attackLevel, boolean isAttacking) {
		if (this.substance > 0) {
			if (isAttacking) {
				--this.substance;
			}
			attackLevel += 1;
		}
		return attackLevel;
	}

	@Override
	public Skillfull getClone() {
		return new Poison();
	}

	@Override
	public String getIdentifier() {
		return "V";
	}

}
