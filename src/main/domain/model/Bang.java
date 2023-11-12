package main.domain.model;

public class Bang implements Skillfull {
	private int remainingUsage;

	public Bang(int remainingUsage) {
		this.remainingUsage = remainingUsage;
	}

	@Override
	public int getEnhancedAttackLevel(int attackLevel) {
		if (this.remainingUsage > 0) {
			--this.remainingUsage;
			attackLevel *= 2;
		}
		return attackLevel;
	}

}
