package main.domain.model;

import java.util.List;

public class ArmyNavalFactory implements ArmyFactory {

	@Override
	public Player createPlayer(final PlayerBuilder playerBuilder) {
		return new PlayerNaval(playerBuilder);
	}

	@Override
	public Soldier createSoldier(final int life, final int attackLevel) {
		return new SoldierNaval(life, attackLevel);
	}

	@Override
	public Enemy createSquadron(final List<Enemy> squadron) {
		return new SquadronNaval(squadron);
	}

	@Override
	public Enemy getSupreme() {
		return SupremeNaval.getInstance();
	}

}
