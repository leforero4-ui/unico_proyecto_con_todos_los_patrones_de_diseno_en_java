package main.domain.model;

public class Healable implements Visitor {

	@Override
	public void visitSoldier(Soldier soldier) {
		if (soldier.getTypeHair().equalsIgnoreCase("corto")) {
			soldier.life += 1;
		}
	}

	@Override
	public void visitSquadron(Squadron squadron) {
		for (Enemy enemy : squadron.squadronList) {
			enemy.acceptVisit(this);
		}
		squadron.calculateLife();
	}

	@Override
	public void visitSupreme(Supreme supreme) {
		supreme.life += 1;
	}

	@Override
	public void visitPlayer(Player player) {
		player.life += 50;
	}

}
