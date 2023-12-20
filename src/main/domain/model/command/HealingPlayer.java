package main.domain.model.command;

import main.domain.model.Command;
import main.domain.model.Healable;
import main.domain.model.Player;
import main.domain.model.Visitor;

public class HealingPlayer implements Command {
	private final Player player;
	private final Visitor healable;

	public HealingPlayer(Player player) {
		this.player = player;
		this.healable = new Healable();
	}

	@Override
	public void execute() {
		this.player.acceptVisit(this.healable);
	}

}
