package main.domain.model;

public class PlayerNaval extends Player {
	
	public PlayerNaval(final PlayerBuilder builder) {
		super(builder);
	}

	@Override
	public String getAvatar() {
		return "AN";
	}
}
