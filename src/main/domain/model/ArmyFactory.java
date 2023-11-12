package main.domain.model;

import java.util.List;

public interface ArmyFactory {
	Player createPlayer(PlayerBuilder playerBuilder);
	Soldier createSoldier(int life, int attackLevel);
	Enemy createSquadron(List<Enemy> squadron);
	Enemy getSupreme();
}
