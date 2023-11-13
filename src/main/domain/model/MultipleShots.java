package main.domain.model;

public class MultipleShots implements Skillfull {
	private int numberOfSimultaneousShots;

	public MultipleShots(int numberOfSimultaneousShots) {
		this.numberOfSimultaneousShots = numberOfSimultaneousShots;
	}

	@Override
	public int getEnhancedAttackLevel(int attackLevel) {
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
