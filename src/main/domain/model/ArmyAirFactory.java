package main.domain.model;

import java.util.List;

public class ArmyAirFactory implements ArmyFactory {

	@Override
	public Player createPlayer(final PlayerBuilder playerBuilder) {
		return new PlayerAir(playerBuilder);
	}

	@Override
	public Soldier createSoldier(final int life, final int attackLevel, final Skillfull skill) {
		return new SoldierAir(life, attackLevel, skill);
	}

	@Override
	public Enemy createSquadron(final List<Enemy> squadron, final Skillfull skill) {
		return new SquadronAir(squadron, skill);
	}

	@Override
	public Enemy getSupreme() {
		return SupremeAir.getInstance();
	}

}
