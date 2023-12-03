package main.domain.model;

import java.util.List;

public class SquadronNaval extends Squadron {
	
	public SquadronNaval(final List<Enemy> squadronList, final Skillfull skill) {
		super(squadronList, skill);
	}

	@Override
	public String getAvatar(final String prefix) {
		StringBuilder avatarTemp = new StringBuilder();
		for (Enemy enemy : this.squadronList) {
			avatarTemp.append(enemy.getAvatar(prefix + this.skill.getIdentifier() + "EN"));
		}
		return avatarTemp.toString();
	}

}
