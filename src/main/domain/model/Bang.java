package main.domain.model;

public class Bang implements Skillfull {
	private int remainingUsage;

	public Bang(int remainingUsage) {
		this.remainingUsage = remainingUsage;
	}

	@Override
	public void activeSkill() {
		if (this.remainingUsage > 0) {
			--this.remainingUsage;
		}
	}

}
