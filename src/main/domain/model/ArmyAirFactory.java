package main.domain.model;

import java.util.List;

import main.application.driven.port.provider.Drawable;

public class ArmyAirFactory implements ArmyFactory {

	@Override
	public Player createPlayer(final PlayerBuilder playerBuilder) {
		return new PlayerAir(playerBuilder);
	}

	@Override
	public Soldier createSoldier(final int life, final int attackLevel, final Drawable drawable) {
		return new SoldierAir(life, attackLevel, drawable);
	}

	@Override
	public Enemy createSquadron(final List<Enemy> squadron, final Drawable drawable) {
		return new SquadronAir(squadron, drawable);
	}

	@Override
	public Enemy getSupreme(final Drawable drawable) {
		return SupremeAir.getInstance(drawable);
	}

}
