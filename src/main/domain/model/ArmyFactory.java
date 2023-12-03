package main.domain.model;

import java.util.List;

public interface ArmyFactory {
	Player createPlayer(PlayerBuilder playerBuilder);
	Soldier createSoldier(int life, int attackLevel, Skillfull skill);
	Enemy createSquadron(List<Enemy> squadron, Skillfull skill);
	Supreme getSupreme();
}
