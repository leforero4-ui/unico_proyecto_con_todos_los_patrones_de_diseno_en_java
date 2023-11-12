package main.domain.model;

public class Poison implements Skillfull {
	private int substance = 100;

	@Override
	public int getEnhancedAttackLevel(int attackLevel) {
		if (this.substance > 0) {
			--this.substance;
			attackLevel += 1;
		}
		return attackLevel;
	}

}
