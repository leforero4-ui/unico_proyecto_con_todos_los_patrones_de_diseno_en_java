package main.domain.model;

public class Fort extends DecoratorEnemy {

	public Fort(final Enemy enemy) {
		super(enemy);
	}

	@Override
	public void receiveAttack(int attack) {
		super.receiveAttack(attack - 2);
	}

	@Override
	public String getAvatar(String prefix) {
		return super.getAvatar(prefix + "F");
	}

}
