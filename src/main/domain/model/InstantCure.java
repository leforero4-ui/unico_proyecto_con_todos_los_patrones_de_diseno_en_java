package main.domain.model;

public class InstantCure implements Skillfull {
	private int cureAvailable = 1000;

	@Override
	public void activeSkill() {
		if (this.cureAvailable > 0) {
			--this.cureAvailable;
		}
	}

}
