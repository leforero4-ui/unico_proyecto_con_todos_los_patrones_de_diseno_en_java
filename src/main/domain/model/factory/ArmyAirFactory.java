package main.domain.model.factory;

import java.util.List;

import main.domain.model.ArmyFactory;
import main.domain.model.Enemy;
import main.domain.model.Player;
import main.domain.model.PlayerAir;
import main.domain.model.PlayerBuilder;
import main.domain.model.Skillfull;
import main.domain.model.Soldier;
import main.domain.model.SoldierAir;
import main.domain.model.SquadronAir;
import main.domain.model.Supreme;
import main.domain.model.SupremeAir;

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
	public Supreme getSupreme() {
		return SupremeAir.getInstance();
	}

}
