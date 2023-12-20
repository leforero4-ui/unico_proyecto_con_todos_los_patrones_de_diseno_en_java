package main.domain.model.factory;

import java.util.List;

import main.domain.model.ArmyFactory;
import main.domain.model.Enemy;
import main.domain.model.Player;
import main.domain.model.PlayerBuilder;
import main.domain.model.PlayerNaval;
import main.domain.model.Skillfull;
import main.domain.model.Soldier;
import main.domain.model.SoldierNaval;
import main.domain.model.SquadronNaval;
import main.domain.model.Supreme;
import main.domain.model.SupremeNaval;

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
