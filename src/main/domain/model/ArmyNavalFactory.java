package main.domain.model;

import java.util.List;

public class ArmyNavalFactory implements ArmyFactory {

	@Override
	public Player createPlayer(final PlayerBuilder playerBuilder) {
		return new PlayerNaval(playerBuilder);
	}

	@Override
	public Soldier createSoldier(final int life, final int attackLevel, final Skillfull skill) {
		return new SoldierNaval(life, attackLevel, skill);
	}

	@Override
	public Enemy createSquadron(final List<Enemy> squadron, final Skillfull skill) {
		return new SquadronNaval(squadron, skill);
	}

	@Override
	public Supreme getSupreme() {
		return SupremeNaval.getInstance();
	}

}
