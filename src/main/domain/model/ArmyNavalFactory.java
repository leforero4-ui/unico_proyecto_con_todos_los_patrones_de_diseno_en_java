package main.domain.model;

import java.util.List;

import main.application.driven.port.provider.Drawable;

public class ArmyNavalFactory implements ArmyFactory {

	@Override
	public Soldier createSoldier(final int life, final int attackLevel, final Drawable drawable) {
		return new SoldierNaval(life, attackLevel, drawable);
	}

	@Override
	public Enemy createSquadron(final List<Enemy> squadron, final Drawable drawable) {
		return new SquadronNaval(squadron, drawable);
	}

	@Override
	public Enemy getSupreme(final Drawable drawable) {
		return SupremeNaval.getInstance(drawable);
	}

}
