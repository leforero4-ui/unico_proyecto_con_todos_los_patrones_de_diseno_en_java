package main.domain.model;

public class Fort extends DecoratorEnemy {

	public Fort(final Enemy enemy) {
		super(enemy);
	}

	@Override
	public void calculateDamage(int attack) {
		super.calculateDamage(attack - 2);
	}

	@Override
	public String getAvatar(String prefix) {
		return super.getAvatar(prefix + "F");
	}

}
