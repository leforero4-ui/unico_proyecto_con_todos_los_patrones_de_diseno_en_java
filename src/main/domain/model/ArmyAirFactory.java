package main.domain.model;

import java.util.List;

public class ArmyAirFactory implements ArmyFactory {

	@Override
	public Player createPlayer(final PlayerBuilder playerBuilder) {
		return new PlayerAir(playerBuilder);
	}

	@Override
	public Soldier createSoldier(final int life, final int attackLevel) {
		return new SoldierAir(life, attackLevel);
	}

	@Override
	public Enemy createSquadron(final List<Enemy> squadron) {
		return new SquadronAir(squadron);
	}

	@Override
	public Enemy getSupreme() {
		return SupremeAir.getInstance();
	}

}
