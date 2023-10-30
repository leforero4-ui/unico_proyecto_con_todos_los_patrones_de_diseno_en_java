package main.domain.model;

import java.util.List;

import main.application.driven.port.provider.Drawable;

public interface ArmyFactory {
	Player createPlayer(PlayerBuilder playerBuilder);
	Soldier createSoldier(int life, int attackLevel, Drawable drawable);
	Enemy createSquadron(List<Enemy> squadron, Drawable drawable);
	Enemy getSupreme(Drawable drawable);
}
